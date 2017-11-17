package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.ActivityList;
import cn.com.hiveview.entity.module.portal.CompositeSubjectList;
import cn.com.hiveview.launcherapi.module.portal.condition.ActivityCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.CompositeSubjectCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.ActivityDao;
import cn.com.hiveview.launcherapi.module.portal.dao.CompositeSubjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2017/7/31.
 */
@Service
public class CompositeSubjectService {
    @Autowired
    private CompositeSubjectDao compositeSubjectDao;
    public ScriptPage<CompositeSubjectList> getPageList(CompositeSubjectCondition condition) throws Exception {
        ScriptPage<CompositeSubjectList> scriptPage = new ScriptPage<CompositeSubjectList>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<ActivityList> rows = this.compositeSubjectDao.getPageList(condition);
        int total = this.compositeSubjectDao.getPageCount(condition);
        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        return scriptPage;
    }
}
