package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.NavigationVo;
import cn.com.hiveview.entity.module.portal.PortalScreenRecommendList;
import cn.com.hiveview.entity.module.portal.PortalScreenRecommendListApiVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalScreenRecommendListCondition;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by admin on 2017/6/30.
 */
@Repository("NavigationMapper")
@SqlMapper
public interface NavigationMapper {
    List<NavigationVo> getNavigationList(Integer templetId);
    HashMap<String,String> getDefaultTapPic();
    String getVipPic(String vipId);

    List<PortalScreenRecommendListApiVo> getList(PortalScreenRecommendListCondition condition);
    Integer getCount(PortalScreenRecommendListCondition condition);
    Integer add(PortalScreenRecommendListCondition condition);
    List<PortalScreenRecommendList> getPageList(PortalScreenRecommendListCondition condition);
    Integer getPageCount(PortalScreenRecommendListCondition condition);
    Integer update(PortalScreenRecommendListCondition condition);
    Integer delete(PortalScreenRecommendListCondition condition);
    List<PortalScreenRecommendList> getComboboxList(PortalScreenRecommendListCondition condition);
}
