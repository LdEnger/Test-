package cn.com.hiveview.launcherapi.module.portal.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

/**
 * Created by user on 2017/7/19.
 */
public class HttpUtil {
//    /**
//     * POST请求数据，无参数提交JSON
//     *
//     * @param url
//     * @return
//     */
//    public static String reqPost(String url) {
//        Long s = System.currentTimeMillis();
//
//        HttpClient client = new DefaultHttpClient();
//        client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
//        client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);
//        String respBody = "";
//        try {
//            HttpPost post = new HttpPost(url);
//            HttpResponse resp = client.execute(post);
//            if (resp.getStatusLine().getStatusCode() == 200) {
//                HttpEntity entity = resp.getEntity();
//                respBody = EntityUtils.toString(entity);
//            } else {
//                respBody = null;
//            }
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            client.getConnectionManager().shutdown();
//        }
//        Long e = System.currentTimeMillis();
//        return respBody;
//    }

    /**
     * POST请求数据，无参数提交JSON
     *
     * @param url
     * @return
     */
    static String reqPost(String url) {
        String resp = "";
        CloseableHttpClient httpclient;
        CloseableHttpResponse response = null;
        try {
            httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(60000).setSocketTimeout(60000).build();
            httpPost.setConfig(requestConfig);

            response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();

            int code = response.getStatusLine().getStatusCode();
            if (code == HttpStatus.SC_OK) {
                resp = EntityUtils.toString(entity, "UTF-8");
            }
            EntityUtils.consume(entity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resp;
    }
    public static void main(String[] args){
//        String res = reqPost("http://best.api.util.domybox.local/api/ip/isGroupUser.json?userIP=172.17.17.114&version=1.0");
//        System.out.println(res);
        Map<String, String> cityMap = IpUtil.cityCodeByIp("172.17.17.114");
        System.out.print(cityMap);
    }
}
