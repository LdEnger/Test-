package cn.com.hiveview.core.db;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Created by yansheng on 2014/8/22.
 */
@Component
public class DynamicDataSourceHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    public static final String DB_TYPE_RW = "dataSource_db01";
    public static final String DB_TYPE_R = "dataSource_db02";
    // 设置数据源类型
    public static void setDataSourceType(String dataSourceType) {
        Assert.notNull(dataSourceType, "DataSourceType cannot be null");
        contextHolder.set(dataSourceType);
    }

    // 获取数据源类型
    public static String getDataSourceType() {
        String db = contextHolder.get();
        if (db == null) {
            db = DB_TYPE_RW;// 默认是读写库
        }
        return db;
    }

    // 清除数据源类型
    public static void clearDataSourceType() {
        contextHolder.remove();
    }
}
