package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.PortalLauncherTempletContentList;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalLauncherTempletContentCondition;

import java.util.List;

/**
 * Created by user on 2017/7/19.
 */
@SqlMapper
public interface PortalLauncherTempletContentMapper {
    Integer add(PortalLauncherTempletContentCondition condition);
    Integer getPageCount(PortalLauncherTempletContentCondition condition);
    Integer update(PortalLauncherTempletContentCondition condition);
    Integer delete(PortalLauncherTempletContentCondition condition);
    Integer getMaxSeq(PortalLauncherTempletContentCondition condition);
    List<PortalLauncherTempletContentList> getPageList(PortalLauncherTempletContentCondition condition);
    PortalLauncherTempletContentList getMinContent(PortalLauncherTempletContentCondition condition);
    PortalLauncherTempletContentList getMaxContent(PortalLauncherTempletContentCondition condition);
    Integer getCount(PortalLauncherTempletContentCondition condition);
}
