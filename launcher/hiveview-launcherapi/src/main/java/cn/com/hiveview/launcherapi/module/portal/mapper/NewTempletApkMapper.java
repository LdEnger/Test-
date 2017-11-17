package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.NewTempletApkListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.NewTempletApkCondition;


import java.util.List;

/**
 * Created by user on 2017/10/11.
 */
@SqlMapper
public interface NewTempletApkMapper {
    List<NewTempletApkListVo> getPage(NewTempletApkCondition condition);


}
