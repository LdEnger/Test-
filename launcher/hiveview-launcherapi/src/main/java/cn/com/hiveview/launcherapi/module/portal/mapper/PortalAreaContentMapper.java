package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.PortalAreaContentListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalAreaContentListCondition;
import cn.com.hiveview.launcherapi.module.portal.controller.PortalAreaAdminirationController;

import java.util.List;

/**
 * Created by user on 2017/10/7.
 */
@SqlMapper
public interface PortalAreaContentMapper {
    List<PortalAreaContentListVo> getPage(PortalAreaContentListCondition contentListCondition);
    Integer getCount(PortalAreaContentListCondition condition);
    Integer delete(PortalAreaContentListCondition condition);
    Integer save(PortalAreaContentListCondition condition);
    Integer update(PortalAreaContentListCondition conditionc);
    PortalAreaContentListVo getAreaMinSeq(PortalAreaContentListCondition condition);
    PortalAreaContentListVo getAreaMaxSeq(PortalAreaContentListCondition condition);
}
