package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.PortalScreenBigPicsListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalScreenBigPicsListCondition;

import java.util.List;

/**
 * Created by user on 2017/7/11.
 */
@SqlMapper
public interface PortalScreenBigPicsListMapper {
    List<PortalScreenBigPicsListVo> getList(PortalScreenBigPicsListCondition portalScreenBigPicsListCondition);
    Integer getCount(PortalScreenBigPicsListCondition portalScreenBigPicsListCondition);
    Integer save(PortalScreenBigPicsListCondition portalScreenBigPicsListCondition);
    Integer delete(PortalScreenBigPicsListCondition portalScreenBigPicsListCondition);
    Integer update(PortalScreenBigPicsListCondition portalScreenBigPicsListCondition);
    Integer updateBig(PortalScreenBigPicsListCondition condition);
}
