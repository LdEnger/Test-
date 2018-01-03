package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.PortalAreaAdministrationListEntity;
import cn.com.hiveview.entity.module.portal.PortalAreaAdministrationListVo;
import cn.com.hiveview.entity.module.portal.PortalAreaContentListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalAreaContentListCondition;
import cn.com.hiveview.launcherapi.module.portal.controller.PortalAreaAdminirationController;

import java.util.List;

/**
 * Created by user on 2017/10/7.
 */
@SqlMapper
public interface PortalAreaContentMapper {
    List<PortalAreaContentListVo> getPage(PortalAreaContentListCondition condition);
    Integer getCount(PortalAreaContentListCondition condition);
    Integer delete(PortalAreaContentListCondition condition);
    Integer save(PortalAreaContentListCondition condition);
    Integer update(PortalAreaContentListCondition conditionc);
    PortalAreaContentListVo getMinContent(PortalAreaContentListCondition condition);
    PortalAreaContentListVo getMaxContent(PortalAreaContentListCondition condition);
    Integer getMaxSeq(PortalAreaContentListCondition condition);
    PortalAreaContentListVo getTopSeq(PortalAreaContentListCondition condition);
    List<PortalAreaAdministrationListEntity>  getAreaContentList(PortalAreaContentListCondition condition);
}
