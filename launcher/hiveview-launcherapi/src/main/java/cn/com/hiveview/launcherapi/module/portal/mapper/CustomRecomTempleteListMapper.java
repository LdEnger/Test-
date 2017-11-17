package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.CustomRecomTempleteListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.CustomRecomTempleteCondition;

import java.util.List;

/**
 * Created by user on 2017/7/20.
 */
@SqlMapper
public interface CustomRecomTempleteListMapper {
    List<CustomRecomTempleteListVo> getList(CustomRecomTempleteCondition customRecomTempleteCondition);
    Integer getCount(CustomRecomTempleteCondition customRecomTempleteCondition);
    CustomRecomTempleteListVo get(CustomRecomTempleteCondition customRecomTempleteCondition);
    Integer save(CustomRecomTempleteCondition customRecomTempleteCondition);
    Integer update(CustomRecomTempleteCondition customRecomTempleteCondition);
    Integer updateIsEffective(CustomRecomTempleteCondition customRecomTempleteCondition);
    Integer delete(CustomRecomTempleteCondition customRecomTempleteCondition);
    Integer selectFatherId(CustomRecomTempleteCondition customRecomTempleteCondition);
    List<CustomRecomTempleteListVo> getFirstList(CustomRecomTempleteCondition customRecomTempleteCondition);
    List<CustomRecomTempleteListVo> getTempleteByFatherId(Integer templeteId);
}
