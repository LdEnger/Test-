package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalTabCondition;

import java.util.List;

/**
 * Created by xiach on 2017/10/9.
 */
@SqlMapper
public interface PortalTabMapper {

    List<PortalTabCondition> getPageList(PortalTabCondition condition);

    Integer add(PortalTabCondition condition);

    Integer delete(PortalTabCondition condition);

    Integer update(PortalTabCondition condition);

    Integer getCount(PortalTabCondition condition);

    Integer getMaxSeq();

    PortalTabCondition getOne(PortalTabCondition condition);
}
