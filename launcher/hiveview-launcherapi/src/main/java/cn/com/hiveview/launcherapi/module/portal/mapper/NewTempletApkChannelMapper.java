package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.NewTempletApkChannelListVo;
import cn.com.hiveview.entity.module.portal.NewTempletApkListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.NewTempletApkChannelCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.NewTempletApkCondition;

import java.util.List;

/**
 * Created by user on 2017/10/12.
 */
@SqlMapper
public interface NewTempletApkChannelMapper {
    List<NewTempletApkChannelListVo> getChannelPage(NewTempletApkChannelCondition condition);
}
