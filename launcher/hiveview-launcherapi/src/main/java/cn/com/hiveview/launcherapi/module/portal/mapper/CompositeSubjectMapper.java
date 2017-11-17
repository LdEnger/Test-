package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.ActivityList;
import cn.com.hiveview.launcherapi.module.portal.condition.ActivityCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.CompositeSubjectCondition;

import java.util.List;

/**
 * Created by admin on 2017/7/31.
 */
@SqlMapper
public interface CompositeSubjectMapper {
    Integer getPageCount(CompositeSubjectCondition condition);
    List<ActivityList> getPageList(CompositeSubjectCondition condition);
}
