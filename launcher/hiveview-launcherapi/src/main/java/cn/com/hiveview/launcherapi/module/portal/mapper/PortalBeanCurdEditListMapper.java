package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.PortalBeanCurdEditListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalBeanCurdEditListCondition;

import java.util.List;

/**
 * Created by user on 2017/7/25.
 */
@SqlMapper
public interface PortalBeanCurdEditListMapper {
    List<PortalBeanCurdEditListVo> getPageList(PortalBeanCurdEditListCondition portalBeanCurdEditListCondition);
    Integer getCount(PortalBeanCurdEditListCondition portalBeanCurdEditListCondition);
    Integer getCountList(PortalBeanCurdEditListCondition portalBeanCurdEditListCondition);
    Integer delete(PortalBeanCurdEditListCondition portalBeanCurdEditListCondition);
    Integer save(PortalBeanCurdEditListCondition portalBeanCurdEditListCondition);
    Integer update(PortalBeanCurdEditListCondition portalBeanCurdEditListCondition);
    List<PortalBeanCurdEditListVo> getList(PortalBeanCurdEditListCondition portalBeanCurdEditListCondition);
    List<PortalBeanCurdEditListVo> getEditList(PortalBeanCurdEditListCondition portalBeanCurdEditListCondition);
    PortalBeanCurdEditListVo getEdit(PortalBeanCurdEditListCondition portalBeanCurdEditListCondition);
    Integer getEditCount(PortalBeanCurdEditListCondition portalBeanCurdEditListCondition);
    Integer updateTime(PortalBeanCurdEditListCondition condition);
}
