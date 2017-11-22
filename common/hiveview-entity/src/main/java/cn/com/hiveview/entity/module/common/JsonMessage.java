package cn.com.hiveview.entity.module.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chimeilong on 17/5/25.
 */
@Data
public class JsonMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 返回值 0成功 -1失败
     */
    private long returnValue;
    /**
     * 错误信息
     */
    private String errorMessage;
    /**
     * 返回数据
     */
    private Object data;
    /**
     * 当前时间
     */
    private String currentTime;

    /**
     * 生成返回数据
     *
     * @param value      返回值
     * @param errMessage 错误信息
     * @param data       返回数据
     * @return public static String GetReturnMessage(long value, String errMessage, Object data) {
        JsonMessage jsonMessage = new JsonMessage();
        jsonMessage.returnValue = value;
        jsonMessage.errorMessage = errMessage;
        jsonMessage.data = data;
        return JSON.toJSONString(jsonMessage);
    } */

    /**
     * 创建并且返回String形式的JsonMessage;
     *
     * @param value
     * @param errMessage
     * @param data
     * @return
     */
    public static Object create(long value, String errMessage, Object data) {
        //String dataStr = JSON.toJSONString(data);
        JsonMessage jsonMessage = new JsonMessage();
        jsonMessage.returnValue = value;
        jsonMessage.errorMessage = errMessage;
        jsonMessage.data = data;
        jsonMessage.currentTime = getTime();
        System.out.println(JSON.toJSONString(jsonMessage,SerializerFeature.WriteMapNullValue));
        return  JSON.toJSONString(jsonMessage, SerializerFeature.WriteMapNullValue);
    }

//    /**
//     * 获取JSON解析后的data数据
//     *
//     * @param clazz
//     * @return
//     */
//    public <T> T getDataParse(Class<T> clazz) {
//        return JSON.parseObject(data, clazz);
//    }
    public static String getTime(){
         SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         Date date = new Date();
         String res = simpleDateFormat.format(date);
         return res;
     }
}
