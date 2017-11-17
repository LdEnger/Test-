package cn.com.hiveview.core.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017/7/17.
 */
public class HttpUtils {
    static Log logger = LogFactory.getLog(HttpUtils.class);
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

    public  static void main(String [] args){
        try {
            String str =  HttpUtils.doGet("http://api.bc.pthv.gitv.tv/api/open/special/qiyiVipInfo/getQiyiVipUserInfo/"+3648776+"-"+1+".json");
           System.out.println(str);
            Map<String, Object> map  = JsonUtil.parseMap(str);
            Map<String, Object> resultMap = new HashMap<String, Object>();
            Map<String, Object> data = (HashMap<String, Object>) map.get("data");
            Map<String, Object> result = (Map<String, Object>) data.get("result");
            if(result == null ){
                resultMap.put("isVip",0);
                resultMap.put("expiredDate",null);
            }else {
                resultMap.put("isVip", result.get("isVip"));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                        "yyyy-MM-dd");
                Long long1 = Long.valueOf((String)result.get("deadline"));
                String format = simpleDateFormat.format(new Date(long1 * 1000L));
                System.out.println(format);
                resultMap.put("expiredDate",format);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
