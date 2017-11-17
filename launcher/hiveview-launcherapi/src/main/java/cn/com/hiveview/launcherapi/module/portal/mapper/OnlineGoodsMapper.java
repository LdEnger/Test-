package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.NewContentChanList;
import cn.com.hiveview.entity.module.portal.OnlineGoodsList;
import cn.com.hiveview.launcherapi.module.portal.condition.NewContentChanCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.OnlineGoodsCondition;

import java.util.List;

/**
 * Created by admin on 2017/8/2.
 */
@SqlMapper
public interface OnlineGoodsMapper {
    List<OnlineGoodsList> getPageList(OnlineGoodsCondition condition);
    Integer getPageCount(OnlineGoodsCondition condition);
}
