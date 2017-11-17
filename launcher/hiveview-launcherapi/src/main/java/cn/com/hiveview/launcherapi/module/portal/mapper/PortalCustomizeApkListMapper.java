package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.PortalCustomizeApkList;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalBeanCurdEditListCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalCustomizeApkCondition;

import java.util.List;

/**
 * Created by admin on 2017/7/10.
 */
@SqlMapper
public interface PortalCustomizeApkListMapper {
    Integer add(PortalCustomizeApkCondition condition);
    Integer getPageCount(PortalCustomizeApkCondition condition);
    Integer update(PortalCustomizeApkCondition condition);
    Integer delete(PortalCustomizeApkCondition condition);
    List<PortalCustomizeApkList> getPageList(PortalCustomizeApkCondition condition);
    Integer updateVersion(PortalCustomizeApkCondition condition);
    Integer getCountByCurdEdit(PortalBeanCurdEditListCondition condition);
    List<PortalCustomizeApkList> getComboboxList(PortalCustomizeApkCondition condition);
}
