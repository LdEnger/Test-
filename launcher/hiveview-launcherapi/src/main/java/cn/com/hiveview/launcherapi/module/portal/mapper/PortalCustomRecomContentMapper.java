package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.PortalCustomRecomContentApiVo;
import cn.com.hiveview.entity.module.portal.PortalCustomRecomContentVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalCustomRecomContentCondition;

import java.util.List;

/**
 * Created by user on 2017/7/25.
 */
@SqlMapper
public interface PortalCustomRecomContentMapper {

    List<PortalCustomRecomContentApiVo> getPortalCustomRecomContentAPIList(PortalCustomRecomContentCondition condition);
    List<PortalCustomRecomContentVo> getList(PortalCustomRecomContentCondition condition);
    Integer save(PortalCustomRecomContentCondition condition);
    Integer update(PortalCustomRecomContentCondition condition);
    Integer delete(PortalCustomRecomContentCondition condition);
    Integer updateByLayoutIdAndTempleteId(PortalCustomRecomContentCondition condition);
    Integer getCount(PortalCustomRecomContentCondition condition);
    List<PortalCustomRecomContentVo> getContentLayoutListByContentId(PortalCustomRecomContentCondition condition);
    List<PortalCustomRecomContentVo> getContentListByTempletId(PortalCustomRecomContentCondition condition);
    Integer updateContentById(PortalCustomRecomContentCondition condition);
    Integer saveCopyContent(PortalCustomRecomContentCondition condition);
    Integer getVideoCount(PortalCustomRecomContentCondition condition);
    Integer updateVersion(PortalCustomRecomContentCondition condition);
    Integer updateBackUpVersion(PortalCustomRecomContentCondition condition);
    List<PortalCustomRecomContentCondition> getPageList(PortalCustomRecomContentCondition condition);
}
