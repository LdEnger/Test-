package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.PortalAreaAdministrationListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalAreaAdminirationListCondition;

import java.util.List;

/**
 * Created by user on 2017/10/6.
 */
@SqlMapper
public interface PortalAreaAdminirationMapper {
    List<PortalAreaAdministrationListVo> getPage(PortalAreaAdminirationListCondition condition);
    Integer getCount(PortalAreaAdminirationListCondition condition);
    Integer save(PortalAreaAdminirationListCondition condition);
    Integer delete(PortalAreaAdminirationListCondition condition);
    Integer update(PortalAreaAdminirationListCondition condition);
}
