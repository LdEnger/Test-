package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.portal.NewTempletApkChannelListVo;
import cn.com.hiveview.entity.module.portal.NewTempletApkListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.NewTempletApkChannelCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.NewTempletApkCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.NewTempletApkChannelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2017/10/12.
 */
@Service
public class NewTempletApkChannelService {

    @Autowired
    private NewTempletApkChannelDao newTempletApkChannelDao;

    public List<NewTempletApkChannelListVo> getChannelPage(NewTempletApkChannelCondition condition) throws  Exception{
        return this.newTempletApkChannelDao.getChannelPage(condition);
    }
}
