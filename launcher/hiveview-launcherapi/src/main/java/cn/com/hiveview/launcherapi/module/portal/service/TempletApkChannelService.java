package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.portal.TempletApkChannelVo;
import cn.com.hiveview.launcherapi.module.portal.condition.TempletApkChannelCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.TempletApkChannelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2017/7/27.
 */
@Service
public class TempletApkChannelService {

    @Autowired
    private TempletApkChannelDao templetApkChannelDao;

    public List<TempletApkChannelVo> getChannelList(TempletApkChannelCondition condition){
        return  templetApkChannelDao.getChannelList(condition);
    }

    public List<TempletApkChannelVo> getChannelTypeList(TempletApkChannelCondition condition){
        return  templetApkChannelDao.getChannelTypeList(condition);
    }
}
