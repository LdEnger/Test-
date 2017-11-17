package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.TempletApkList;
import cn.com.hiveview.launcherapi.module.portal.condition.TempletApkCondition;

import java.util.List;

/**
 * Created by admin on 2017/8/1.
 */
@SqlMapper
public interface TempletApkMapper {
    List<TempletApkList> getList(TempletApkCondition condition);

}
