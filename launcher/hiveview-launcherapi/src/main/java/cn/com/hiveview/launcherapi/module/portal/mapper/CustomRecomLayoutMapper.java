package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.CustomRecomLayoutVo;
import cn.com.hiveview.launcherapi.module.portal.condition.CustomRecomLayoutCondition;

import java.util.List;

/**
 * Created by user on 2017/8/2.
 */
@SqlMapper
public interface CustomRecomLayoutMapper {
    List<CustomRecomLayoutVo> getList(CustomRecomLayoutCondition condition);
    Integer getCount(CustomRecomLayoutCondition condition);
    CustomRecomLayoutVo getLayoutById(CustomRecomLayoutCondition condition);
    Integer save(CustomRecomLayoutCondition condition);
    Integer delete(CustomRecomLayoutCondition condition);
    List<CustomRecomLayoutVo> getListByPosition(CustomRecomLayoutCondition condition);
    Integer updateLayoutTeam(CustomRecomLayoutCondition condition);
}
