package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.ActivityList;
import cn.com.hiveview.entity.module.portal.AppVo;
import cn.com.hiveview.launcherapi.module.portal.condition.ActivityCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.AppCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.AppDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2017/7/27.
 */
@Service
public class AppService {

    @Autowired
    private AppDao appDao;

    public List<AppVo> getList(AppCondition condition){
        return appDao.getList(condition);
    }
    public ScriptPage<AppVo> getPageList(AppCondition condition) throws Exception {
        ScriptPage<AppVo> scriptPage = new ScriptPage<AppVo>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<AppVo> rows = this.appDao.getList(condition);
        int total = this.appDao.getCount(condition);
        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        return scriptPage;
    }

    public AppVo get(AppCondition condition){
        return this.appDao.get(condition);
    }

    /**
     * 用于智能推荐接口,按appId列表取数据
     */
    public List<AppVo> getDataGroupList(AppCondition condition){
        return this.appDao.getDataGroupList(condition);
    }
}
