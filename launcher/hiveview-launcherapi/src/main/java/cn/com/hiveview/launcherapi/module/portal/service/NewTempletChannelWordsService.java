package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.NewTempletChannelWordsListVo;
import cn.com.hiveview.entity.module.portal.PortalAreaAdministrationListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.NewTmpletChannelWordsCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalAreaAdminirationListCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.NewTempletChannelWordsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2017/10/10.
 */
@Service
public class NewTempletChannelWordsService {

    @Autowired
    private NewTempletChannelWordsDao newTempletChannelWordsDao;

    public ScriptPage<NewTempletChannelWordsListVo> getPage(NewTmpletChannelWordsCondition condition) throws  Exception{
        ScriptPage<NewTempletChannelWordsListVo> scriptPage = new ScriptPage<>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<NewTempletChannelWordsListVo> rows = this.newTempletChannelWordsDao.getPage(condition);
        Integer total = this.newTempletChannelWordsDao.getCount(condition);
        scriptPage.setTotal(total);
        scriptPage.setRows(rows);
        return scriptPage;
    }
    public ScriptPage<NewTempletChannelWordsListVo> getPageList(NewTmpletChannelWordsCondition condition) throws  Exception{
        ScriptPage<NewTempletChannelWordsListVo> scriptPage = new ScriptPage<>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<NewTempletChannelWordsListVo> rows = this.newTempletChannelWordsDao.getPageList(condition);
        Integer total = this.newTempletChannelWordsDao.getApkCount(condition);
        scriptPage.setTotal(total);
        scriptPage.setRows(rows);
        return scriptPage;
    }
}
