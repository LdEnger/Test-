package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.AppVo;
import cn.com.hiveview.launcherapi.module.portal.condition.AppCondition;

import java.util.List;

/**
 * Created by user on 2017/7/27.
 */
@SqlMapper
public interface AppMapper {
    Integer getCount(AppCondition condition);
    List<AppVo> getList(AppCondition condition);
    AppVo get(AppCondition condition);
    List<AppVo> getDataGroupList(AppCondition condition);
}
