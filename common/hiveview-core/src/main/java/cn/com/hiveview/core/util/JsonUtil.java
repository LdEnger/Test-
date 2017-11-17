package cn.com.hiveview.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil
{
//  public static void main(String[] args)
//    throws IOException
//  {
//    String t = "";
//    String m1 = "2011-05-23 11:57:34.0";
//    Map m = new HashMap();
//    m.put("str1", m1);
//    System.out.println(t);
//
//    String jsonstr = toJSONString(m);
//    System.out.println(jsonstr);
//    System.out.println(parseMap(jsonstr));
//  }

  public static synchronized String toJSONString(Object object)
  {
    ObjectMapper mapper = new ObjectMapper();
    String jsonstr = null;
    try {
      jsonstr = mapper.writeValueAsString(object);
    } catch (JsonGenerationException e) {
      e.printStackTrace();
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return jsonstr;
  }

  public static Map<String, Object> parseMap(String text)
  {
    Map map = null;

    ObjectMapper mapper = new ObjectMapper();
    try {
      map = (Map)mapper.readValue(text, Map.class);
    } catch (JsonParseException e) {
      e.printStackTrace();
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return map;
  }
    public static void main(String[] args) {
//        URL url;
//        try {
//            url = new URL("http://124.207.119.85/data4/caihongyinyue/2015.11.30/hdmv085191.mp4");
//          System.out.println(url);
//            InputStream in = url.openStream();
//            System.out.println("连接可用");
//        } catch (Exception e1) {
//            e1.printStackTrace();
//            System.out.println("连接打不开!");
//            url = null;
//        }
//
//        String srcUrl="http://172.16.152.142/media/new/2013/icntv2/media/2016/01/08/SD330bb474a15e432eaffd1d6065397d55.ts";
//        String [] ips={"172.16.152.140","172.16.152.141","172.16.152.142"};
//        for(int i=0;i<ips.length;i++){
//            URL url;
//            try {
//                srcUrl =srcUrl.replaceAll("(?:(?:[01]?\\d{1,2}|2[0-4]\\d|25[0-5])\\.){3}(?:[01]?\\d{1,2}|2[0-4]\\d|25[0-5])",ips[i]);
//                url = new URL(srcUrl);
//                System.out.println(url);
//                InputStream in = url.openStream();
//                //sourceMap.put("location", srcUrl);
//                System.out.println("能打开："+srcUrl);
//                break;
//            } catch (Exception e1) {
//               // e1.printStackTrace();
//                System.out.println("连接打不开!");
//                url = null;
//            }
//        }
    	String vJson = "{data :{result: [ ]}}";
    	Map<String, Object> videoData=JsonUtil.parseMap(vJson);
    	Object result = videoData.get("result");
    	if(result == null){
    		System.out.println("======================");
    	}
		List<Map<String, Object>> videoList=(List<Map<String, Object>>) videoData.get("result");

    }
}