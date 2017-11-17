package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.PortalLauncherTempletTabList;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalLauncherTempletTabCondition;

import java.util.List;

/**
 * Created by user on 2017/7/19.
 */
@SqlMapper
public interface PortalLauncherTempletTabMapper {
    Integer add(PortalLauncherTempletTabCondition condition);
    Integer getPageCount(PortalLauncherTempletTabCondition condition);
    Integer update(PortalLauncherTempletTabCondition condition);
    Integer delete(PortalLauncherTempletTabCondition condition);
    Integer getMaxSeq(PortalLauncherTempletTabCondition condition);
    List<PortalLauncherTempletTabList> getPageList(PortalLauncherTempletTabCondition condition);
    List<PortalLauncherTempletTabList> getComboboxList(PortalLauncherTempletTabCondition condition);
    PortalLauncherTempletTabList getMinTab(PortalLauncherTempletTabCondition condition);
    PortalLauncherTempletTabList getMaxTab(PortalLauncherTempletTabCondition condition);
    Integer getCount(PortalLauncherTempletTabCondition condition);
    PortalLauncherTempletTabList getTopSeq(PortalLauncherTempletTabCondition condition);
}
