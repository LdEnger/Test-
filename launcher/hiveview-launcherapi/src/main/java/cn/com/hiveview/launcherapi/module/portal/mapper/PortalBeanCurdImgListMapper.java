package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.PortalBeanCurdImgListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalBeanCurdImgListCondition;

import java.util.List;

/**
 * Created by user on 2017/7/26.
 */
@SqlMapper
public interface PortalBeanCurdImgListMapper {
    List<PortalBeanCurdImgListVo> getPageList(PortalBeanCurdImgListCondition portalBeanCurdImgListCondition);
    Integer getCount(PortalBeanCurdImgListCondition portalBeanCurdImgListCondition);
    Integer update(PortalBeanCurdImgListCondition portalBeanCurdImgListCondition);
    Integer delete(PortalBeanCurdImgListCondition portalBeanCurdImgListCondition);
    PortalBeanCurdImgListVo getImgList(PortalBeanCurdImgListCondition portalBeanCurdImgListCondition);
    Integer save(PortalBeanCurdImgListCondition portalBeanCurdImgListCondition);
}
