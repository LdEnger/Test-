package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.EntranceAreaList;
import cn.com.hiveview.launcherapi.module.portal.condition.EntranceAreaCondition;

import java.util.List;

/**
 * Created by admin on 2017/8/2.
 */
@SqlMapper
public interface EntranceAreaMapper {
    List<EntranceAreaList> getCityByAreaId(EntranceAreaCondition condition);
    Integer add(EntranceAreaCondition condition);
    Integer update(EntranceAreaCondition condition);
    Integer deleteCitys(EntranceAreaCondition condition);
    Integer ifCount(EntranceAreaCondition condition);
}
