package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.PortalGroupEntity;
import cn.com.hiveview.entity.module.portal.PortalTabPageEntity;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalTabGroupCondition;

import java.util.List;

/**
 * Created by xiach on 2017/10/9.
 */
@SqlMapper
public interface PortalTabGroupMapper {
    List<PortalTabGroupCondition> getPageList(PortalTabGroupCondition condition);

    Integer add(PortalTabGroupCondition condition);

    Integer delete(PortalTabGroupCondition condition);

    Integer update(PortalTabGroupCondition condition);

    Integer getCount(PortalTabGroupCondition condition);

    Integer getMaxSeq(PortalTabGroupCondition condition);

    PortalTabGroupCondition getMinContent(PortalTabGroupCondition condition);

    PortalTabGroupCondition getMaxContent(PortalTabGroupCondition condition);

    List<PortalTabPageEntity> getList(PortalTabGroupCondition condition);

    Integer getMinSeq(PortalTabGroupCondition condition);

    List<PortalTabGroupCondition> getTabListByGroupId(PortalTabGroupCondition condition);
}
