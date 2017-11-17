package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.portal.TempletApkList;
import cn.com.hiveview.entity.module.portal.TempletList;
import cn.com.hiveview.launcherapi.module.portal.condition.TempletApkCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.TempletCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.TempletApkDao;
import cn.com.hiveview.launcherapi.module.portal.dao.TempletDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2017/8/1.
 */
@Service
public class TempletApkService {
    @Autowired
    private TempletApkDao templetApkDao;

    public List<TempletApkList> getList(TempletApkCondition condition){
        return  templetApkDao.getList(condition);
    }
}
