package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.PortalBeanCurdEditListVo;
import cn.com.hiveview.entity.module.portal.PortalBeanCurdListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalBeanCurdListCondition;

import java.util.List;

/**
 * Created by user on 2017/7/24.
 */
@SqlMapper
public interface PortalBeanCurdListMapper {
    List<PortalBeanCurdEditListVo> getPageList(PortalBeanCurdListCondition curdListCondition);
    Integer getCount(PortalBeanCurdListCondition curdListCondition);
    Integer save(PortalBeanCurdListCondition curdListCondition);
    Integer update(PortalBeanCurdListCondition curdListCondition);
    Integer delete(PortalBeanCurdListCondition curdListCondition);
    Integer updateIs(PortalBeanCurdListCondition portalBeanCurdListCondition);
    List<PortalBeanCurdListVo> getCurdList(PortalBeanCurdListCondition portalBeanCurdListCondition);
}
