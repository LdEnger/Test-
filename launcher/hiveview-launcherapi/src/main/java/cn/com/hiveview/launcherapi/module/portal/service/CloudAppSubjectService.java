package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.ActivityList;
import cn.com.hiveview.entity.module.portal.CloudAppSubjectList;
import cn.com.hiveview.entity.module.portal.CompositeSubjectList;
import cn.com.hiveview.launcherapi.module.portal.condition.CloudAppSubjectCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.CompositeSubjectCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.CloudAppSubjectDao;
import cn.com.hiveview.launcherapi.module.portal.dao.CompositeSubjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2017/7/31.
 */
@Service
public class CloudAppSubjectService {
    @Autowired
    private CloudAppSubjectDao cloudAppSubjectDao;
    public ScriptPage<CloudAppSubjectList> getPageList(CloudAppSubjectCondition condition) throws Exception {
        ScriptPage< CloudAppSubjectList> scriptPage = new ScriptPage< CloudAppSubjectList>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List< CloudAppSubjectList> rows = this.cloudAppSubjectDao.getPageList(condition);
        int total = this.cloudAppSubjectDao.getPageCount(condition);
        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        return scriptPage;
    }
}
