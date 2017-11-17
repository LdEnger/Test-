package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.CustomRecomBackupsContentVo;
import cn.com.hiveview.launcherapi.module.portal.condition.CustomRecomBackupsContentCondition;

import java.util.List;

/**
 * Created by user on 2017/8/25.
 */
@SqlMapper
public interface CustomRecomBackupsContentMapper {

    List<CustomRecomBackupsContentVo> getList(CustomRecomBackupsContentCondition condition);
    Integer getCount(CustomRecomBackupsContentCondition condition);
    CustomRecomBackupsContentVo get(CustomRecomBackupsContentCondition condition);
    CustomRecomBackupsContentVo getBySeq(CustomRecomBackupsContentCondition condition);
    Integer save(CustomRecomBackupsContentCondition condition);
    Integer update(CustomRecomBackupsContentCondition condition);
    Integer delete(CustomRecomBackupsContentCondition condition);
    Integer getMaxSeq(CustomRecomBackupsContentCondition condition);
    Integer getMinSeq(CustomRecomBackupsContentCondition condition);
    CustomRecomBackupsContentVo getMinMapping(CustomRecomBackupsContentCondition condition);
    CustomRecomBackupsContentVo getMaxMapping(CustomRecomBackupsContentCondition condition);
    Integer insertBackUpList(CustomRecomBackupsContentCondition condition);
    Integer updateIsUseredCount(CustomRecomBackupsContentCondition condition);
    CustomRecomBackupsContentVo getDataIfPlay(CustomRecomBackupsContentCondition condition);
}
