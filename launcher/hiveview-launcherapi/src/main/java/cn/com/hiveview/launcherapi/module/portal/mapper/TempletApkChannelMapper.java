package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.TempletApkChannelVo;
import cn.com.hiveview.launcherapi.module.portal.condition.TempletApkChannelCondition;

import java.util.List;

/**
 * Created by user on 2017/7/27.
 */
@SqlMapper
public interface TempletApkChannelMapper {

    List<TempletApkChannelVo> getChannelList(TempletApkChannelCondition condition);
    List<TempletApkChannelVo> getChannelTypeList(TempletApkChannelCondition condition);
}
