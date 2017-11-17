package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.PortalScreenRecommendList;
import cn.com.hiveview.entity.module.portal.PortalScreenRecommendListApiVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalScreenRecommendListCondition;

import java.util.List;

/**
 * Created by admin on 2017/6/30.
 */
@SqlMapper
public interface PortalScreenRecommendListMapper {
    List<PortalScreenRecommendListApiVo> getList(PortalScreenRecommendListCondition condition);
    Integer getCount(PortalScreenRecommendListCondition condition);
    Integer add(PortalScreenRecommendListCondition condition);
    List<PortalScreenRecommendList> getPageList(PortalScreenRecommendListCondition condition);
    Integer getPageCount(PortalScreenRecommendListCondition condition);
    Integer update(PortalScreenRecommendListCondition condition);
    Integer delete(PortalScreenRecommendListCondition condition);
    List<PortalScreenRecommendList> getComboboxList(PortalScreenRecommendListCondition condition);
}
