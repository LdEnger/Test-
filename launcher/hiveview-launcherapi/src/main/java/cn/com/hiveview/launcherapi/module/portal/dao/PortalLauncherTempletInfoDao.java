package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.PortalLauncherTempletVo;
import cn.com.hiveview.entity.module.portal.PortalRecommendListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalLauncherTempletAreaCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalLauncherTempletContentCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.PortalLauncherTempletInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by user on 2017/7/19.
 */
@Repository("portalLauncherTempletInfoDao")
public class PortalLauncherTempletInfoDao {

    @Autowired
    PortalLauncherTempletInfoMapper portalLauncherTempletInfoMapper;

    public PortalLauncherTempletVo getLauncherTempletInfo(PortalLauncherTempletAreaCondition condition){
        return portalLauncherTempletInfoMapper.getLauncherTempletInfo(condition);
    }

    public PortalLauncherTempletVo getAreaIdByTwo(String equipmentNo, String IP,Integer type){
        return portalLauncherTempletInfoMapper.getAreaIdByTwo(equipmentNo,IP,type);
    }

    public List<PortalLauncherTempletVo> getLauncherTempletInfoList(PortalLauncherTempletAreaCondition condition){
        return portalLauncherTempletInfoMapper.getLauncherTempletInfoList(condition);
    }

    public Integer getCount(PortalLauncherTempletAreaCondition condition){
        return portalLauncherTempletInfoMapper.getCount(condition);
    }

    public List<PortalRecommendListVo> getPortalRecommendList(PortalLauncherTempletContentCondition condition){
        return portalLauncherTempletInfoMapper.getPortalRecommendList(condition);
    }


}
