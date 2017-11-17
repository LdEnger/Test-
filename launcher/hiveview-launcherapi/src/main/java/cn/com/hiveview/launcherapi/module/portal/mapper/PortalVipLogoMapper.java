package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalVipLogoCondition;

import java.util.List;

/**
 * Created by xiach on 2017/10/6.
 */
@SqlMapper
public interface PortalVipLogoMapper {

    Integer add(PortalVipLogoCondition condition);

    Integer delete(PortalVipLogoCondition condition);

    Integer update(PortalVipLogoCondition condition);

    List<PortalVipLogoCondition> getPageList(PortalVipLogoCondition condition);

    Integer getCount(PortalVipLogoCondition condition);

    Integer getMaxSeq();

    Integer getMinSeq();

    PortalVipLogoCondition getMaxContent(PortalVipLogoCondition condition);

    PortalVipLogoCondition getMinContent(PortalVipLogoCondition condition);
}
