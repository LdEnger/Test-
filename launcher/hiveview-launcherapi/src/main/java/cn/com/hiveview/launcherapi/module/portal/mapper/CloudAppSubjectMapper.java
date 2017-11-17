package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.ActivityList;
import cn.com.hiveview.entity.module.portal.CloudAppSubjectList;
import cn.com.hiveview.launcherapi.module.portal.condition.CloudAppSubjectCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.CompositeSubjectCondition;

import java.util.List;

/**
 * Created by admin on 2017/7/31.
 */
@SqlMapper
public interface CloudAppSubjectMapper {
    Integer getPageCount( CloudAppSubjectCondition condition);
    List<CloudAppSubjectList> getPageList(CloudAppSubjectCondition condition);
}
