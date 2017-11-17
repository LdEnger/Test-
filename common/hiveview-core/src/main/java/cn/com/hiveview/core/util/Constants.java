package cn.com.hiveview.core.util;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by lgy on 2016/7/25.
 */
public final class Constants {

    /**
     * 管道符号
     */
    public final static String BAR = "|";

    /**
     * 下划线符号
     */
    public final static String UNDERLINE = "_";

    public static final String PASSWORD_SALT = "7f03519ef84128e7bdc9a704d0c9458e";

    /**
     * 允许访问的url
     */
    public static final String PERMIT_URL = "permitUrl";

    /**
     * 禁止访问的URl
     */
    public static final String FORBID_URL = "forbidUrl";

    /**
     * 未登录roleID
     */
    public final static Long NOT_LOGIN_ROLE_ID = 1L;

    /**
     * 已登录roleID
     */
    public final static Long LOGIN_IN_ROLE_ID = 2L;

    /**
     * 一分钟毫秒数
     */
    public final static Long ONE_MINUTE_MILLISECOND = TimeUnit.MINUTES.toMillis(1);

    /**
     * 分页默认显示10条
     */
    public static final Integer PAGE_COUNT = 5;

    /**
     * 章节 资源类型
     */
    public static final Integer RESOURCE_CHAPTER_TYPE = 1;

    /**
     * 课程 资源类型
     */
    public static final Integer RESOURCE_LESSON_TYPE = 2;

    /**
     * 课时 资源类型
     */
    public static final Integer RESOURCE_CLASS_TYPE = 3;

    /**
     * 环节 资源类型
     */
    public static final Integer RESOURCE_STEP_TYPE = 4;

    /**
     * 父资源/源资源
     */
    public static final Long RESOURCE_ROOT = -1L;

    /**
     * 生成验证码的最大数量
     */
    public static final Integer PICTURE_CODE_RANDOM_MAX = 9999;

    /**
     * redis 验证码数据存储前缀
     */
    public static final String PICTURE_CODE = "pictureCode:";
    public static final long TOKEN_EXPIRES_HOUR = 3600;

    public static final String UPLOADPATH_IMG = ProperManager.getString("upload.tvimg.path");
    public static final String UPLOADPATH_APK = ProperManager.getString("upload.tvapk.path");
    public static final String WEB_IMG_PATH = ProperManager.getString("webaccess_img.path");
    public static final String WEB_APK_PATH = ProperManager.getString("webaccess_apk.path");
    public static final String UPLOAD_EXCEL_PATH = ProperManager.getString("upload.excel.path");
    /**
     * 判断内外网
     */
    public static final String IP_IsGroupServer = ProperManager.getString("ip.isGroupSever");
    public static final String IP_IsGroupUser = ProperManager.getString("ip.isGroupUser");
    public static final String VERSION = "1.0";

    /**
     * vip用户信息
     */
    public static final String USER_QIYI_VIP_INFO;
    public static final String USER_DAMAI_VIP_INFO;
    static {
        USER_QIYI_VIP_INFO= ProperManager.getString("user.qiyi.vip.info");
        USER_DAMAI_VIP_INFO= ProperManager.getString("user.damai.vip.info");
    }
    /**
     * 获取盒子类型
     */
    public static final String GET_EQUMENTS_TYPE_URL = ProperManager.getString("getEquipmentsType");
    /**
     * 直播播频道获取地区code
     */
    public static final String CAROUSEL_TV_AREA_CODE_URL = ProperManager.getString("carouselTvAreaCodeUrl");

//    官网
//    服务器上传
    public static final String UPLOADPATH_IMG_PORTAL = ProperManager.getString("upload.portal.path");
//    本地获取
    public static final String WEB_IMG_PATH_PORTAL = ProperManager.getString("webaccess.portal.path");
    public static final String RECOM_URL = ProperManager.getString("recom.url");
    public static final String domain = ProperManager.getString("domain.url");


    //随机专用
    public static final Random PB_RANDOM = new Random();
    //导航栏专用key
    public static final String NAVIGATION_REDIS_KEY="NAVIGATION_REDIS_KEY";
    public static final String NAVIGATION_TAB_KEY="NAVIGATION_TAB_KEY";

    //大数据智能推荐接口
    private static final String RECOM_SERVER = ProperManager.getString("recom.server");
    public static final String RECOM_USER_URL =RECOM_SERVER + "/data/getRecomVideosByUser.json";

    //通过tabId获取group
    public static final String GROUPLIST_BYTABID_REDIS_KEY="GROUPLIST_BYTABID_REDIS_KEY";
    //统一缓存
    public static final Integer X =Integer.parseInt(ProperManager.getString("redis_x"));
    public static final Integer Y =Integer.parseInt(ProperManager.getString("redis_y"));

    //sso
    public static final String ssoDomain = ProperManager.getString("sso.domain.url");
    public static final String cookieDomain = ProperManager.getString("sso.cookieDomain.url");
    public static final int     COOKIE_EXPIRES_HALF_AN_HOUR = 1800;
}
