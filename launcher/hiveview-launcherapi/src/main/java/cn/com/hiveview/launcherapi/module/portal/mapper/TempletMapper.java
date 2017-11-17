package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.TempletList;
import cn.com.hiveview.launcherapi.module.portal.condition.TempletCondition;

import java.util.List;

/**
 * Created by admin on 2017/8/1.
 */
@SqlMapper
public interface TempletMapper {
    List<TempletList> getList(TempletCondition condition);
}
