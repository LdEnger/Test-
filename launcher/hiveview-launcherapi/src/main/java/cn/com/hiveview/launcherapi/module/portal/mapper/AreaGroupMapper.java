package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.AreaGroupList;
import cn.com.hiveview.launcherapi.module.portal.condition.AreaGroupCondition;

import java.util.List;

/**
 * Created by admin on 2017/8/2.
 */
@SqlMapper
public interface AreaGroupMapper {
    List<AreaGroupList> getAreaGroupList(AreaGroupCondition a);
}
