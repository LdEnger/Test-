package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.PortalFixedRecommendContentVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalFixedRecomContentCondition;

import java.util.List;

/**
 * Created by user on 2017/7/14.
 */
@SqlMapper
public interface PortalFixedRecommendContentMapper {
    List<PortalFixedRecommendContentVo> getList(PortalFixedRecomContentCondition portalFixedRecomContentCondition);
    Integer getCount(PortalFixedRecomContentCondition portalFixedRecomContentCondition);
    PortalFixedRecommendContentVo getFixedRecommendContent(PortalFixedRecomContentCondition portalFixedRecomContentCondition);
    Integer save(PortalFixedRecomContentCondition portalFixedRecomContentCondition);
    Integer delete(PortalFixedRecomContentCondition portalFixedRecomContentCondition);
    Integer update(PortalFixedRecomContentCondition portalFixedRecomContentCondition);
    List<PortalFixedRecommendContentVo> getFixedRecommendContentList(PortalFixedRecomContentCondition portalFixedRecomContentCondition);
    Integer getFixedRecommendContentCount(PortalFixedRecomContentCondition portalFixedRecomContentCondition);
    PortalFixedRecommendContentVo getFixedRecommendContentOne(PortalFixedRecomContentCondition portalFixedRecomContentCondition);
    Integer getFixedRecommendContentCountByContentId(PortalFixedRecomContentCondition portalFixedRecomContentCondition);
    PortalFixedRecommendContentVo getMinMapping(PortalFixedRecomContentCondition portalFixedRecomContentCondition);
    PortalFixedRecommendContentVo getMaxMapping(PortalFixedRecomContentCondition portalFixedRecomContentCondition);
    Integer getMaxSeq(PortalFixedRecomContentCondition portalFixedRecomContentCondition);
    Integer getMinSeq(PortalFixedRecomContentCondition portalFixedRecomContentCondition);
}
