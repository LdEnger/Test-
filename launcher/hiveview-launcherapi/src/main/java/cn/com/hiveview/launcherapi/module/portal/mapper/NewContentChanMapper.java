package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.NewContentChanList;
import cn.com.hiveview.launcherapi.module.portal.condition.NewContentChanCondition;

import java.util.List;

/**
 * Created by admin on 2017/7/18.
 */
@SqlMapper
public interface NewContentChanMapper {
    List<NewContentChanList> getPageList(NewContentChanCondition condition);
    Integer getPageCount(NewContentChanCondition condition);
    List<NewContentChanList> getPageListByPremiere(NewContentChanCondition condition);
    Integer getCountByPremiere(NewContentChanCondition condition);
    NewContentChanList get(NewContentChanCondition condition);
    List<NewContentChanList> getList(NewContentChanCondition condition);
    List<NewContentChanList> getDataGroupList(NewContentChanCondition condition);
    NewContentChanCondition getOneChan(NewContentChanCondition condition);
}
