package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.PortalSysAppIconsList;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalSysAppIconsListCondition;
import java.util.List;

/**
 * Created by admin on 2017/7/10.
 */
@SqlMapper
public interface PortalSysAppIconsListMapper {
    Integer add(PortalSysAppIconsListCondition condition);
    Integer getPageCount(PortalSysAppIconsListCondition condition);
    Integer update(PortalSysAppIconsListCondition condition);
    Integer delete(PortalSysAppIconsListCondition condition);
    List<PortalSysAppIconsList> getPageList(PortalSysAppIconsListCondition condition);
    List<PortalSysAppIconsList> getList(PortalSysAppIconsListCondition condition);
    Integer getCountByPackage(PortalSysAppIconsListCondition condition);
}
