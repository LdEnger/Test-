package cn.com.hiveview.launcherapi.module.portal.util;

import org.springframework.stereotype.Component;

@Component
public class DBHelper {
	 /** 
     * 线程独立 
     */  
    private ThreadLocal<String> contextHolder = new ThreadLocal<String>();  
    
    public static final String DB_TYPE_RW = "dataSource_db01";
    public static final String DB_TYPE_R = "dataSource_db02";
  
    public String getDBType() {
        System.out.println("---contextHoldex-----"+contextHolder+"---"+contextHolder.get());
        String db = contextHolder.get();  
        if (db == null) {
            db = DB_TYPE_RW;// 默认是读写库
        }
        return db;  
    }
  
    public void setDBType(String str) {
        contextHolder.set(str);
    }
   
    public void clearDBType() {  
        contextHolder.remove();  
    } 
}
