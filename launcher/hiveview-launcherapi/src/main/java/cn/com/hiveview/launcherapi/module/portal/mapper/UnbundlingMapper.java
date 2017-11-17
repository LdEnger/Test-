package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.launcherapi.module.portal.condition.UnbundlingCondition;

/**
 * Created by admin on 2017/8/17.
 */
@SqlMapper
public interface UnbundlingMapper {
    Integer deleteScreentRecommendContent(UnbundlingCondition condition);
    Integer deletefixedRecomContent(UnbundlingCondition condition);
    Integer deleteCustomRecomContent(UnbundlingCondition condition);
    Integer deleteBackupsContent(UnbundlingCondition condition);
}
