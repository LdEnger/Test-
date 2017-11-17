package cn.com.hiveview.core.util;

public class YouGouConstants {
    private static final String youGouUrl = ProperManager.getString("yougou.url");
    public static final String SPECIALS_LIST = youGouUrl + "/api/open/domyshop/app/getSpecList";
    public static final String GOODS_TOP_CATEGORY_LIST = youGouUrl + "/api/open/domyshop/app/getTopCategory";
    public static final String GOODS_SUB_CATEGORY_LIST = youGouUrl + "/api/open/domyshop/app/getSubCategory";
    public static final String GOODS_LIST = youGouUrl + "/api/open/domyshop/app/getGoodsByCategory";
}
