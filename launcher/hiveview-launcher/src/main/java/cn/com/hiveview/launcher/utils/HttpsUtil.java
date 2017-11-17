package cn.com.hiveview.launcher.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chimeilong on 16/4/28.
 */
public class HttpsUtil {

    static Log logger = LogFactory.getLog(HttpsUtil.class);
    /**
     * 忽视证书HostName
     */
    private static HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier() {
        public boolean verify(String s, SSLSession sslsession) {
            return true;
        }
    };
    /**
     * Ignore Certification
     */
    private static TrustManager ignoreCertificationTrustManger = new X509TrustManager() {

        private X509Certificate[] certificates;

        @Override
        public void checkClientTrusted(X509Certificate certificates[],
                                       String authType) throws CertificateException {
            if (this.certificates == null) {
                this.certificates = certificates;
            }
        }

        @Override
        public void checkServerTrusted(X509Certificate[] ax509certificate,
                                       String s) throws CertificateException {
            if (this.certificates == null) {
                this.certificates = ax509certificate;
            }

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            // TODO Auto-generated method stub
            return null;
        }
    };

    /**
     * 调用https api 获取报文
     *
     * @param url
     * @return
     */
    public static String doGet(String url) {
        StringBuffer response = new StringBuffer();
        try {
            HttpClient client = new HttpClient();
            GetMethod method = new GetMethod(url);
            method.addRequestHeader("Content-Type", "application/json;charset="
                    + "UTF-8");

            int status = client.executeMethod(method);

            if (status == HttpStatus.SC_OK) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(method.getResponseBodyAsStream(),
                                "UTF-8"));
                String line;
                while ((line = reader.readLine()) != null) {

                    response.append(line);
                }
                reader.close();
            }
        } catch (Exception ex) {
            logger.error(null, ex);
        } finally {
        }
        return response.toString();
    }

    /**
     * 执行一个HTTP POST请求，返回请求响应的数据
     *
     * @param url     请求的URL地址
     * @param params  请求的查询参数,可以为null
     * @param charset 字符集
     * @param pretty  是否美化
     * @return 返回请求响应的HTML
     */
    public static String doPost(String url, Map<String, String> params,
                                String charset, boolean pretty) {
        StringBuffer response = new StringBuffer();
        HttpClient client = new HttpClient();
        HttpMethod method = new PostMethod(url);
        // 设置Http Post数据
        if (params != null) {
            HttpMethodParams p = new HttpMethodParams();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                System.err.println("key: " + entry.getKey() + " val: "
                        + entry.getValue());
                p.setParameter(entry.getKey(), entry.getValue());
            }
            method.setParams(p);

        }
        try {
            client.executeMethod(method);
            if (method.getStatusCode() == HttpStatus.SC_OK) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(method.getResponseBodyAsStream(),
                                charset));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (pretty)
                        response.append(line).append(
                                System.getProperty("line.separator"));
                    else
                        response.append(line);
                }
                reader.close();
            }
        } catch (IOException e) {
            logger.error("执行HTTP Post请求" + url + "时，发生异常！", e);
        } finally {
            method.releaseConnection();
        }
        return response.toString();
    }

    /**
     * post请求
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String doPost(String url, String params, String charSet) {
        // Post请求的url
        try {
            URL postUrl = new URL(url);
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) postUrl
                    .openConnection();
            // 设置是否向connection输出，因为这个是post请求，参数要放在
            // http正文内，因此需要设为true
            connection.setDoOutput(true);
            // Read from the connection. Default is true.
            connection.setDoInput(true);
            // Set the post method. Default is GET
            connection.setRequestMethod("POST");
            // Post 请求不能使用缓存
            connection.setUseCaches(false);
            // URLConnection.setInstanceFollowRedirects是成员函数，仅作用于当前函数
            connection.setInstanceFollowRedirects(true);
            // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
            // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
            // 进行编码
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            // String content = URLEncoder.encode(params, "utf-8");
            // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
            // 要注意的是connection.getOutputStream会隐含的进行connect。
            connection.connect();
            DataOutputStream out = new DataOutputStream(
                    connection.getOutputStream());
            // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写道流里面
            out.write(params.getBytes());
            // out.writeUTF(params);
            out.flush();
            out.close(); // flush and close
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), charSet));// 设置编码,否则中文乱码
            String line = "";
            StringBuffer result = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
            connection.disconnect();
            return result.toString();
        } catch (Exception e) {
            logger.error(null, e);
        }
        return null;
    }

    /**
     * post请求
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String doPost(String url, String params) {
        StringBuffer response = new StringBuffer();
        try {

            HttpClient client = new HttpClient();
            PostMethod post = new PostMethod(url);
            post.setRequestEntity(new StringRequestEntity(params,
                    "application/json", "UTF-8"));

            int status = client.executeMethod(post);

            if (status == HttpStatus.SC_OK) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(post.getResponseBodyAsStream(),
                                "UTF-8"));
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);

        } finally {
        }
        return response.toString();
    }

    /* 发送Post请求 */
    public static String doPost(String url, Object body) {
        // post请求
        String par = JSON.toJSONString(body);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("url  >> ");
        stringBuilder.append(url);
        stringBuilder.append(":par >> ");
        stringBuilder.append(par);
        logger.info(stringBuilder.toString());
        String resp = doPost(url,par);
        stringBuilder.delete(0,stringBuilder.length());
        stringBuilder.append(":repo >> ");
        stringBuilder.append(resp);
        logger.info(stringBuilder.toString());
        return resp;
    }

    /* 发送Post请求 */
    public static String doPost(String url, Object body,
                                Map<String, Object> params) {
        // post请求
        String par = JSON.toJSONString(params);
        if (params == null) {
            return doPost(url, par);
        }
        url = getCombiUrl(url, params);

        return doPost(url, par);
    }

    /* 发送Get请求 */
    public static String doGet(String url, Map<String, Object> params) {
        if (params == null) {
            return doGet(url);
        }

        return doGet(getCombiUrl(url, params));
    }

    // 组合地址
    private static String getCombiUrl(String url, Map<String, Object> params) {
        // post请求
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();

            String value = entry.getValue().toString();

            if (index == 0) {
                sb.append(key + "=" + value);
            } else {
                sb.append("&" + key + "=" + value);
            }
            index++;
        }
        return url + "?" + sb.toString();
    }

    public static void main(String[] args) {
        /*
         * String urlString =
		 * "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxf4ad0013d7d8471e&secret=7269f79ef4fc3fbb6abd2c3543ece260"
		 * ; String output = new String(HttpsUtil.doGet(urlString));
		 * System.out.println(output);
		 */
        String url = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN";
        Map<String, String> map = new HashMap<String, String>();
        map.put("openid", "openid");
        map.put("to_groupid", "to_groupid");

        // String rest = doPost(url, "", SystemConfig.CHARSET_UTF_8);
        // System.err.println("rest: " + rest);
    }
}
