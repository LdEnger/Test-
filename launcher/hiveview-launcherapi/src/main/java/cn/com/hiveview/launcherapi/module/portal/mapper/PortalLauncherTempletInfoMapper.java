package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.PortalLauncherTempletVo;
import cn.com.hiveview.entity.module.portal.PortalRecommendListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalLauncherTempletAreaCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalLauncherTempletContentCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by user on 2017/7/19.
 */
@SqlMapper
public interface PortalLauncherTempletInfoMapper {

    //根据code获取模板信息
    PortalLauncherTempletVo getLauncherTempletInfo(PortalLauncherTempletAreaCondition condition);
    PortalLauncherTempletVo getAreaIdByTwo(@Param("cityId1")String cityId1,@Param("cityId2")String cityId2 ,@Param("type")Integer type);
    List<PortalLauncherTempletVo> getLauncherTempletInfoList(PortalLauncherTempletAreaCondition condition);
    Integer getCount(PortalLauncherTempletAreaCondition condition);

    //根据模板Id获取推荐位列表
    List<PortalRecommendListVo> getPortalRecommendList(PortalLauncherTempletContentCondition condition);
}
