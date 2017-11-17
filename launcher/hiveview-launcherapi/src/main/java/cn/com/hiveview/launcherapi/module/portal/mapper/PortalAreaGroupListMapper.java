package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.PortalAreaGroupList;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalAreaGroupCondition;

import java.util.List;

/**
 * Created by admin on 2017/7/10.
 */
@SqlMapper
public interface PortalAreaGroupListMapper {
    Integer add(PortalAreaGroupCondition condition);
    Integer getPageCount(PortalAreaGroupCondition condition);
    Integer getCountByAreaCode(PortalAreaGroupCondition condition);
    Integer getCountByAreaName(PortalAreaGroupCondition condition);
    Integer update(PortalAreaGroupCondition condition);
    Integer updateMacAreaName(PortalAreaGroupCondition condition);
    Integer delete(PortalAreaGroupCondition condition);
    List<PortalAreaGroupList> getPageList(PortalAreaGroupCondition condition);
    List<PortalAreaGroupList> getList(PortalAreaGroupCondition condition);
    PortalAreaGroupList getAreaGroupByCode(PortalAreaGroupCondition condition);
}
