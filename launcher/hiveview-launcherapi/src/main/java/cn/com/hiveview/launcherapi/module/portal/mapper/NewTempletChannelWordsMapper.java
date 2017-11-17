package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.NewTempletChannelWordsListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.NewTmpletChannelWordsCondition;

import java.util.List;

/**
 * Created by user on 2017/10/10.
 */
@SqlMapper
public interface NewTempletChannelWordsMapper {
    List<NewTempletChannelWordsListVo> getPage(NewTmpletChannelWordsCondition condition);
    Integer getCount(NewTmpletChannelWordsCondition condition);
    List<NewTempletChannelWordsListVo> getPageList(NewTmpletChannelWordsCondition condition);
    Integer getApkCount(NewTmpletChannelWordsCondition condition);
}
