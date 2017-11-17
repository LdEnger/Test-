package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.portal.TempletApkChannelVo;
import cn.com.hiveview.entity.module.portal.TempletList;
import cn.com.hiveview.launcherapi.module.portal.condition.TempletApkChannelCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.TempletCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.TempletApkChannelDao;
import cn.com.hiveview.launcherapi.module.portal.dao.TempletDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2017/8/1.
 */
@Service
public class TempletService {

    @Autowired
    private TempletDao templetDao;

    public List<TempletList> getList(TempletCondition condition){
        return  templetDao.getList(condition);
    }
}
