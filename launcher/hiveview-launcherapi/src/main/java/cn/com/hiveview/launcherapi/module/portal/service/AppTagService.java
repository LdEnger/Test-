package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.AppTagListVo;
import cn.com.hiveview.entity.module.portal.CloudAppSubjectList;
import cn.com.hiveview.launcherapi.module.portal.condition.AppTagListConditon;
import cn.com.hiveview.launcherapi.module.portal.condition.CloudAppSubjectCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.AppTagDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2017/10/17.
 */
@Service
public class AppTagService {

    @Autowired
    private AppTagDao appTagDao;

        public ScriptPage<AppTagListVo> getPage(AppTagListConditon condition) throws Exception {
            ScriptPage< AppTagListVo> scriptPage = new ScriptPage< AppTagListVo>();
            condition.setPageIndex(condition.getPage());
            condition.setPageSize(condition.getRows());
            List< AppTagListVo> rows = this.appTagDao.getPage(condition);
            int total = this.appTagDao.getCoun(condition);
            scriptPage.setRows(rows);
            scriptPage.setTotal(total);
            return scriptPage;
    }
}
