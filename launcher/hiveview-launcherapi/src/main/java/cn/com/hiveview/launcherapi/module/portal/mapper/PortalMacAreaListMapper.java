package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.PortalMacAreaList;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalMacAreaCondition;

import java.util.List;

/**
 * Created by admin on 2017/7/10.
 */
@SqlMapper
public interface PortalMacAreaListMapper {
    Integer add(PortalMacAreaCondition condition);
    Integer getPageCount(PortalMacAreaCondition condition);
    Integer getCountByCode(PortalMacAreaCondition condition);
    Integer update(PortalMacAreaCondition condition);
    Integer delete(PortalMacAreaCondition condition);
    List<PortalMacAreaList> getPageList(PortalMacAreaCondition condition);
    PortalMacAreaList get(PortalMacAreaCondition condition);
    Integer getCountByMacSn(PortalMacAreaCondition condition);
}
