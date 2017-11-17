package cn.com.hiveview.core.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
public class ProperManager {
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("conf/conf");
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			throw new RuntimeException( "! config : "+ key + '!');
		}
	}
	public static String getRB(String rb, String key){
		try {
			String val = ResourceBundle.getBundle(rb).getString(key);
			return val;
		} catch (MissingResourceException e) {
			throw new RuntimeException( '!' + rb + ":" + key + '!');
		}
	}
	
	public static void main(String[] args) {
		//System.out.println(ProperManager.getString("user.qiyi.vip.info"));
		System.out.println(Constants.RECOM_URL);
	}
}
