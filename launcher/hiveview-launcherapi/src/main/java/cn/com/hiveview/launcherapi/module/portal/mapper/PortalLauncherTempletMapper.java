package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.PortalLauncherTempletList;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalLauncherTempletCondition;

import java.util.List;

/**
 * Created by user on 2017/7/19.
 */
@SqlMapper
public interface PortalLauncherTempletMapper {
    Integer add(PortalLauncherTempletCondition condition);
    Integer getPageCount(PortalLauncherTempletCondition condition);
    Integer update(PortalLauncherTempletCondition condition);
    Integer updateLogoId(PortalLauncherTempletCondition condition);
    Integer delete(PortalLauncherTempletCondition condition);
    Integer getCount(PortalLauncherTempletCondition condition);
    List<PortalLauncherTempletList> getPageList(PortalLauncherTempletCondition condition);
    List<PortalLauncherTempletList> getComboboxList(PortalLauncherTempletCondition condition);

}
