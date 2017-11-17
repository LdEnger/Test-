package cn.com.hiveview.core.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 基于httpclient4.5
 */
public class HttpUtils4 {
    private HttpUtils4() {
        throw new AssertionError();
    }

    private static Log logger = LogFactory.getLog(HttpUtils4.class);

    // get
    public static String getByJson(String url) {
        String resp = "";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        resp = getString(resp, httpclient, httpGet);
        return resp;
    }

    public static String get(String url) {
        String resp = "";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        resp = getString(resp, httpclient, httpGet);
        return resp;
    }

    private static String getString(String resp, CloseableHttpClient httpclient, HttpGet httpGet) {
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            int code = response.getStatusLine().getStatusCode();
            if (code == HttpStatus.SC_OK) {
                resp = EntityUtils.toString(entity, "UTF-8");
            }
            EntityUtils.consume(entity);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        } finally {
            responseClose(response);
        }
        return resp;
    }

    // post
    public static String postByJson(String url, String json) {
        String resp = "";
        CloseableHttpClient httpclient;
        CloseableHttpResponse response = null;
        try {
            httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            StringEntity se = new StringEntity(json);
            se.setContentType("application/json;charset==UTF-8");
            httpPost.setEntity(se);
            response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            int code = response.getStatusLine().getStatusCode();
            if (code == HttpStatus.SC_CREATED) {
                resp = EntityUtils.toString(entity, "UTF-8");
            }
            EntityUtils.consume(entity);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        } finally {
            responseClose(response);
        }
        return resp;
    }


    // put
    public static String putByJson(String url, String json) {
        String resp = "";
        CloseableHttpClient httpclient;
        CloseableHttpResponse response = null;
        try {
            httpclient = HttpClients.createDefault();
            HttpPut httpPut = new HttpPut(url);
            StringEntity se = new StringEntity(json);
            se.setContentType("application/json;charset==UTF-8");
            httpPut.setEntity(se);
            response = httpclient.execute(httpPut);
            HttpEntity entity = response.getEntity();
            int code = response.getStatusLine().getStatusCode();
            if (code == HttpStatus.SC_OK) {
                resp = EntityUtils.toString(entity, "UTF-8");
            }
            EntityUtils.consume(entity);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        } finally {
            responseClose(response);
        }
        return resp;
    }


    // delete
    public static boolean delete(String url) {
        CloseableHttpClient httpclient;
        CloseableHttpResponse response = null;
        try {
            httpclient = HttpClients.createDefault();
            HttpDelete httpDelete = new HttpDelete(url);
            response = httpclient.execute(httpDelete);
            HttpEntity entity = response.getEntity();
            int code = response.getStatusLine().getStatusCode();
            if (code == HttpStatus.SC_NO_CONTENT) {
                return true;
            }
            EntityUtils.consume(entity);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        } finally {
            responseClose(response);
        }
        return false;
    }

    private static void responseClose(CloseableHttpResponse response) {
        try {
            if (response != null) {
                response.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e);
        }
    }
}
