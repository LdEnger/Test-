package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.PortalCustomizeApkVersionList;
import cn.com.hiveview.entity.module.portal.PortalMacAreaList;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalCustomizeApkVersionCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalMacAreaCondition;

import java.util.List;

/**
 * Created by admin on 2017/7/10.
 */
@SqlMapper
public interface PortalCustomizeApkVersionListMapper {
    Integer add(PortalCustomizeApkVersionCondition condition);
    Integer getPageCount(PortalCustomizeApkVersionCondition condition);
    Integer update(PortalCustomizeApkVersionCondition condition);
    Integer delete(PortalCustomizeApkVersionCondition condition);
    List<PortalCustomizeApkVersionList> getPageList(PortalCustomizeApkVersionCondition condition);
    String getMaxVersion(PortalCustomizeApkVersionCondition condition);
    PortalCustomizeApkVersionList getApkPageName(PortalCustomizeApkVersionCondition condition);
    Integer getCountByApkId(PortalCustomizeApkVersionCondition condition);
    Integer getCountByVersion(PortalCustomizeApkVersionCondition condition);
}
