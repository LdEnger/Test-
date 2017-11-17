package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.portal.AppCategoryList;
import cn.com.hiveview.launcherapi.module.portal.condition.AppCategoryCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.AppCategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2017/7/31.
 */
@Service
public class AppCategoryService {
    @Autowired
    private AppCategoryDao appCategoryDao;
    public List<AppCategoryList> getList(AppCategoryCondition condition) throws Exception {
        List<AppCategoryList> rows = this.appCategoryDao.getList(condition);
        return rows;
    }
}
