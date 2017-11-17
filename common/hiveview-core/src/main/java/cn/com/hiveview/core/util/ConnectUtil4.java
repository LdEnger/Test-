package cn.com.hiveview.core.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;
import org.testng.collections.Lists;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

/**
 * Http Client 4.0 版本实现
 * Created by yansheng on 2014/6/16.
 */
@Slf4j
public class ConnectUtil4 {
    /**
     * 最大连接数
     */
    private final static int MAX_TOTAL_CONNECTIONS = 100;
    /**
     * 每个路由最大连接数
     */
    private final static int MAX_ROUTE_CONNECTIONS = 100;
    /**
     * 通讯超时时间
     */
    private static final int SOCKET_TIMEOUT = 30000;
    /**
     * 连接超时时间
     */
    private final static int CONNECT_TIMEOUT = 30000;

    /**
     * 客户端重试次数
     */
    private final static int RETRY_TIMER = 3;

    private RequestConfig requestConfig;
    private CloseableHttpClient httpclient;

    private ConnectUtil4() {
        setRequestConfig();
        setHttpClient();
    }

    private static class SingletonHolder {
        static ConnectUtil4 instance = new ConnectUtil4();
    }

    public static ConnectUtil4 getInstance() {
        return SingletonHolder.instance;
    }

    private void setRequestConfig() {
        requestConfig = RequestConfig.custom()
                .setSocketTimeout(SOCKET_TIMEOUT) // socket超时
                .setConnectTimeout(CONNECT_TIMEOUT) // 连接超时
                .build();
    }

    private void setHttpClient() {
        HttpRequestRetryHandler retryHandler = (exception, executionCount, context) -> {
            // 重试3次后返回失败
            if (executionCount >= RETRY_TIMER) {
                return false;
                //服务器丢掉了连接,重试
            } else if (exception instanceof NoHttpResponseException) {
                return true;
                // 超时
            } else if (exception instanceof InterruptedIOException) {
                return false;
                // 未知主机
            } else if (exception instanceof UnknownHostException) {
                return false;
                // 连接重置
            } else if (exception instanceof ConnectTimeoutException) {
                return false;
            }
            HttpClientContext clientContext = HttpClientContext.adapt(context);
            HttpRequest request = clientContext.getRequest();
            boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
            // 是否幂等
            if (idempotent) {
                return true;
            }
            return false;
        };

        /*
         * SSL
          */
        SSLConnectionSocketFactory sslSocketFactory = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustSelfSignedStrategy() {
                //信任所有
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            sslSocketFactory = new SSLConnectionSocketFactory(sslContext);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        PoolingHttpClientConnectionManager clientConnectionManager = new PoolingHttpClientConnectionManager();
        clientConnectionManager.setMaxTotal(MAX_TOTAL_CONNECTIONS);
        clientConnectionManager.setDefaultMaxPerRoute(MAX_ROUTE_CONNECTIONS);

        HttpClientBuilder httpClientBuilder = HttpClients.custom().setRetryHandler(retryHandler).
                setConnectionManager(clientConnectionManager);
        if (sslSocketFactory != null) {
            httpclient = httpClientBuilder.setSSLSocketFactory(sslSocketFactory).build();
        } else {
            httpclient = httpClientBuilder.build();
        }
    }

    /**
     * rest风格的post请求
     *
     * @param uri
     * @param headerList
     * @param params     <code>JSON</code> 字符串
     * @return
     */
    public String post(final String uri, final List<Header> headerList, final String params) {
        HttpPost httpPost = new HttpPost(uri);
        httpPost.setConfig(requestConfig);
        ContentType contentType = ContentType.create("application/json", Charset.forName("UTF-8"));
        httpPost.setEntity(new StringEntity(params, contentType));
        headerList.add(new BasicHeader("Accept", "application/json, text/javascript, */*; q=0.01"));
        headerList.forEach(httpPost::setHeader);
        return reponseHandle(uri, httpPost);
    }

    /**
     * post请求
     *
     * @param uri
     * @param headerList
     * @param paramList
     * @return
     */
    public String post(final String uri, final List<Header> headerList, final List<NameValuePair> paramList) {
        HttpPost httpPost = new HttpPost(uri);
        httpPost.setConfig(requestConfig);
        httpPost.setEntity(new UrlEncodedFormEntity(paramList, Consts.UTF_8));
        headerList.forEach(httpPost::setHeader);
        return reponseHandle(uri, httpPost);
    }

    /**
     * Get请求
     *
     * @param uri
     * @param headerList
     * @return
     */
    public String get(String uri, List<Header> headerList) {
        HttpGet httpGet = new HttpGet(uri);
        httpGet.setConfig(requestConfig);
        headerList.forEach(httpGet::setHeader);
        return reponseHandle(uri, httpGet);
    }


    private String reponseHandle(String uri, HttpRequestBase httpRequest) {
        String reponseString = "";
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpRequest);
            StatusLine statusCode = response.getStatusLine();
            if (statusCode.getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    Header header = response.getFirstHeader("Content-Encoding");
                    if (header != null && header.getValue().contains("gzip")) {
                        BufferedHttpEntity bufferedEntity = new BufferedHttpEntity(entity);
                        GzipDecompressingEntity gzipEntity = new GzipDecompressingEntity(bufferedEntity);
                        reponseString = EntityUtils.toString(gzipEntity);
                    } else {
                        BufferedHttpEntity bufferedEntity = new BufferedHttpEntity(entity);
                        reponseString = EntityUtils.toString(bufferedEntity, Consts.UTF_8);
                    }
                }
            } else { // 任务失败
                log.error("The Response status code error " + statusCode.getStatusCode());
            }
        } catch (IOException ex) {
            log.error("Get URI has error, the URI is " + uri);
            log.error(ExceptionUtils.getMessage(ex));
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException ex) {
                    log.error("Get URI has error, the URI is " + uri);
                    log.error(ExceptionUtils.getMessage(ex));
                }
            }
            return reponseString;
        }
    }


    /**
     * GET请求的重载方法，添加指定字符集参数
     *
     * @param uri             请求地址
     * @param headerList      请求头部信息设置
     * @param responseCharset 返回数据的解析字符集
     * @return
     */
    public String get(String uri, List<Header> headerList, String responseCharset) {
        HttpGet httpGet = new HttpGet(uri);
        httpGet.setConfig(requestConfig);
        headerList.forEach(httpGet::setHeader);
        return reponseHandle(uri, httpGet, responseCharset);
    }

    /**
     * 获取返回页面代码，添加返回数据解析字符集支持
     *
     * @param uri             请求地址
     * @param httpRequest     头部信息
     * @param responseCharset 返回数据解析字符集 为空时默认使用utf-8
     * @return
     */
    private String reponseHandle(String uri, HttpRequestBase httpRequest, String responseCharset) {
        String reponseString = "";
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpRequest);
            StatusLine statusCode = response.getStatusLine();
            if (statusCode.getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    Header header = response.getFirstHeader("Content-Encoding");
                    if (header != null && header.getValue().contains("gzip")) {
                        BufferedHttpEntity bufferedEntity = new BufferedHttpEntity(entity);
                        GzipDecompressingEntity gzipEntity = new GzipDecompressingEntity(bufferedEntity);
                        reponseString = EntityUtils.toString(gzipEntity);
                    } else {
                        BufferedHttpEntity bufferedEntity = new BufferedHttpEntity(entity);
                        if (responseCharset == null || responseCharset.trim().equals("")) {
                            reponseString = EntityUtils.toString(bufferedEntity, Consts.UTF_8);
                        } else {
                            reponseString = EntityUtils.toString(bufferedEntity, responseCharset);
                        }

                    }
                }
            } else { // 任务失败
                log.error("The Response status code error " + statusCode.getStatusCode());
            }
        } catch (IOException ex) {
            log.error("Get URI has error, the URI is " + uri);
            log.error(ExceptionUtils.getMessage(ex));
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException ex) {
                    log.error("Get URI has error, the URI is " + uri);
                    log.error(ExceptionUtils.getMessage(ex));
                }
            }
            return reponseString;
        }
    }


    /**
     * 文件列表
     *
     * @param fileList 上传路径url
     * @param url
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public String uploadFile(List<File> fileList, String url, String relationId, String relationType, String status) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            // 把一个普通参数和文件上传给下面这个地址 是一个servlet
            HttpPost httpPost = new HttpPost(url);

            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            // 以浏览器兼容模式运行，防止文件名乱码。
            multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            multipartEntityBuilder.setCharset(CharsetUtils.get("UTF-8"));
            // 添加上传内容
            if (fileList == null || fileList.size() == 0) {
                return "";
            }
            multipartEntityBuilder.addTextBody("relationId", relationId);
            multipartEntityBuilder.addTextBody("relationType", relationType);
            multipartEntityBuilder.addTextBody("status", status);
            for (File file : fileList) {
                // 把文件转换成流对象FileBody
                FileBody fileBody = new FileBody(file);
                multipartEntityBuilder.addPart(file.getName(), fileBody);
            }
            HttpEntity entity = multipartEntityBuilder.build();
            httpPost.setEntity(entity);

            log.info("发起请求的页面地址 " + httpPost.getRequestLine());
            // 发起请求 并返回请求的响应
            CloseableHttpResponse response = httpClient.execute(httpPost);
            try {
                // 打印响应状态
                log.info("response.getStatusLine()==" + response.getStatusLine());
                // 获取响应对象
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    // 打印响应长度
                    log.info("Response content length: "
                            + resEntity.getContentLength());
                    // 打印响应内容
                    String result = EntityUtils.toString(resEntity, Charset.forName("UTF-8"));
                    log.info("result==" + result);
                    return result;
                }
                // 销毁
                EntityUtils.consume(resEntity);
            } finally {
                response.close();
            }
        } catch (IOException ex) {
            log.error(ExceptionUtils.getMessage(ex));
        }
        return "";
    }

    /**
     * 工具测试
     *
     * @param args
     */
//    public static final void main(String... args) throws UnsupportedEncodingException {
//        ConnectUtil4 connectUtil4 = ConnectUtil4.getInstance();
//        String URL = "http://www.qingguo.com/usercenter/GetSchoolList?t=1467109087477_8599&callback=jQuery17207750947153580052_1467109070425&pcode=4401&qingguo_return_type=json&_=1467109087477";
//        List<Header> headerList = Lists.newArrayList();
//        headerList.add(new BasicHeader("Accept-Encoding", "gzip, deflate"));
//        String str = connectUtil4.get(URL, headerList);
//        str = URLDecoder.decode(str, Consts.UTF_8.name());
//        str = StringEscapeUtils.unescapeJava(str);
//        log.info(str);
//    }
    public static void main(String[] args) {
        List list = Lists.newArrayList();
//        File file1 = new File("D:\\test\\1.doc");
//        File file2 = new File("D:\\test\\1.docx");
        File file3 = new File("D:\\test\\课程拆分---数学2\\配套资源\\七年级数学上册 1.1《正数和负数》学案（无答案） （新版）新人教版.doc");
//        list.add(file1);
//        list.add(file2);
        list.add(file3);
        String str = ConnectUtil4.getInstance().uploadFile(list, "http://101.201.52.153:9898/opt/fastdfsfs", "14", "3", "");
//        String str = ConnectUtil4.getInstance().uploadFile(list,"http://127.0.0.1:9898/opt/fastdfs","12","4");
        log.info(str);
    }
}
