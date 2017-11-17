package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.PortalBeanCurdMappingListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalBeanCurdMappingListCondition;

import java.util.List;

/**
 * Created by user on 2017/8/2.
 */
@SqlMapper
public interface PortalBeanCurdMappingListMapper {
    List<PortalBeanCurdMappingListVo> getPageList(PortalBeanCurdMappingListCondition portalBeanCurdMappingListCondition);
    Integer getCount(PortalBeanCurdMappingListCondition portalBeanCurdMappingListCondition);
    Integer save(PortalBeanCurdMappingListCondition portalBeanCurdMappingListCondition);
    Integer update(PortalBeanCurdMappingListCondition portalBeanCurdMappingListCondition);
    Integer delete(PortalBeanCurdMappingListCondition portalBeanCurdMappingListCondition);
    Integer getMaxSeq(PortalBeanCurdMappingListCondition portalBeanCurdMappingListCondition);
    Integer getMappingCount(PortalBeanCurdMappingListCondition portalBeanCurdMappingListCondition);
    PortalBeanCurdMappingListVo getMinMapping(PortalBeanCurdMappingListCondition portalBeanCurdMappingListCondition);
    PortalBeanCurdMappingListVo getMaxMapping(PortalBeanCurdMappingListCondition portalBeanCurdMappingListCondition);
    Integer getMinSeq(PortalBeanCurdMappingListCondition portalBeanCurdMappingListCondition);
}
