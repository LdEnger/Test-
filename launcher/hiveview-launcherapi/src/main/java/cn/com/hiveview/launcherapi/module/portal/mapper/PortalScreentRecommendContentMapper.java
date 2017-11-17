package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.PortalScreentRecommendContent;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalScreentRecommendContentCondition;
import java.util.List;

/**
 * Created by admin on 2017/7/10.
 */
@SqlMapper
public interface PortalScreentRecommendContentMapper {
    Integer add(PortalScreentRecommendContentCondition condition);
    List<PortalScreentRecommendContent> getPageList(PortalScreentRecommendContentCondition condition);
    Integer getPageCount(PortalScreentRecommendContentCondition condition);
    Integer update(PortalScreentRecommendContentCondition condition);
    Integer delete(PortalScreentRecommendContentCondition condition);
    Integer getMaxSeq(PortalScreentRecommendContentCondition condition);
    Integer getMinSeq(PortalScreentRecommendContentCondition condition);
    PortalScreentRecommendContent getMaxContent(PortalScreentRecommendContentCondition condition);
    PortalScreentRecommendContent getMinContent(PortalScreentRecommendContentCondition condition);
    List<PortalScreentRecommendContent> getHasList(PortalScreentRecommendContentCondition condition);
    Integer getBigPic(PortalScreentRecommendContentCondition condition);
}
