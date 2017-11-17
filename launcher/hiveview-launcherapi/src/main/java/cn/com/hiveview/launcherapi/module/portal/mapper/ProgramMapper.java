package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.MergeVideoDataVo;
import cn.com.hiveview.launcherapi.module.portal.condition.MergeVideoData;
import java.util.List;

/**
 * Created by admin on 2017/10/13.
 */
@SqlMapper
public interface ProgramMapper {
    List<MergeVideoDataVo> getPageList(MergeVideoData condition);
    Integer getPageCount(MergeVideoData condition);
}
