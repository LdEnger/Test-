package cn.com.hiveview.core.util;

public class ActivityConstant {
	
	public static final String activityConf;
	public static final String activityServer;
	public static final String activityList;
	public static final String activityTypeList;
	
	static{
		activityConf = "conf/conf_activity";
		activityServer= ProperManager.getRB(activityConf,"activity_server");
		activityList= ProperManager.getRB(activityConf,"online_activity_list");
		activityTypeList= ProperManager.getRB(activityConf,"online_activity_type");
	}
	
}
