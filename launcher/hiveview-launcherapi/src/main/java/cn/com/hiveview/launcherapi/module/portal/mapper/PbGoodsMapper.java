package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.OnlineGoodsList;
import cn.com.hiveview.entity.module.portal.PbsGoodsList;
import cn.com.hiveview.launcherapi.module.portal.condition.PbGoodsCondition;

import java.util.List;

/**
 * Created by admin on 2017/12/4.
 */
@SqlMapper
public interface PbGoodsMapper {
    List<PbsGoodsList> getPageList(PbGoodsCondition condition);
    Integer getPageCount(PbGoodsCondition condition);
}
