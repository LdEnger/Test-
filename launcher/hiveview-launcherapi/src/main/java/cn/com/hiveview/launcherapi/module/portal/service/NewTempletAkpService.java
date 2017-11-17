package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.NewTempletApkListVo;
import cn.com.hiveview.entity.module.portal.NewTempletChannelWordsListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.NewTempletApkCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.NewTmpletChannelWordsCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.NewTempletApkDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2017/10/11.
 */
@Service
public class NewTempletAkpService {

    @Autowired
    private NewTempletApkDao newTempletApkDao;

    public List<NewTempletApkListVo> getPage(NewTempletApkCondition condition) throws  Exception{
            return this.newTempletApkDao.getPage(condition);
    }




}

