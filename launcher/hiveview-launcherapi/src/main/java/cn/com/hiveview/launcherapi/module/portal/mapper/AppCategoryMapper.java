package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.AppCategoryList;
import cn.com.hiveview.launcherapi.module.portal.condition.AppCategoryCondition;

import java.util.List;

/**
 * Created by admin on 2017/7/31.
 */
@SqlMapper
public interface AppCategoryMapper {
    List<AppCategoryList> getList(AppCategoryCondition condition);
}
