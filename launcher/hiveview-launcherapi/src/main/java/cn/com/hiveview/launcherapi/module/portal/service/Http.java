package cn.com.hiveview.launcherapi.module.portal.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by user on 2017/8/21.
 */
public class Http {

    public static String get(String url,String charset) {
        try {
            URL httpURL = new URL(url);
            HttpURLConnection http = (HttpURLConnection) httpURL.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    http.getInputStream(),charset));
            StringBuilder sb = new StringBuilder();
            String temp = null;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
                sb.append("\n");
            }
            br.close();
            return sb.toString();
        }catch(Exception e){ e.printStackTrace(); }
        return null;
    }
}
