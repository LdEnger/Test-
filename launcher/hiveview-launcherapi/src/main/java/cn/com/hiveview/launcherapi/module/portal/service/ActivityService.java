package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.ActivityList;
import cn.com.hiveview.entity.module.portal.PortalSysAppIconsList;
import cn.com.hiveview.launcherapi.module.portal.condition.ActivityCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalSysAppIconsListCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.ActivityDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalSysAppIconsListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2017/7/31.
 */
@Service
public class ActivityService {
    @Autowired
    private ActivityDao activityDao;
    public ScriptPage<ActivityList> getPageList(ActivityCondition condition) throws Exception {
        ScriptPage<ActivityList> scriptPage = new ScriptPage<ActivityList>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<ActivityList> rows = this.activityDao.getPageList(condition);
        int total = this.activityDao.getPageCount(condition);
        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        return scriptPage;
    }
}
