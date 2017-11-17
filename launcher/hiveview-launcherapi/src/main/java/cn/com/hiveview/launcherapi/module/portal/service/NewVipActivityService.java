package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.NewVipActivityVo;
import cn.com.hiveview.entity.module.portal.OnlineGoodsList;
import cn.com.hiveview.launcherapi.module.portal.condition.NewVipActivityCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.NewVipActivityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2017/10/13.
 */
@Service
public class NewVipActivityService {
    @Autowired
    public NewVipActivityDao newVipActivityDao;
    public ScriptPage<NewVipActivityVo> getPageList(NewVipActivityCondition condition) throws Exception {
        ScriptPage<NewVipActivityVo> scriptPage = new ScriptPage<NewVipActivityVo>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<NewVipActivityVo> rows = this.newVipActivityDao.getPageList(condition);
        int total = this.newVipActivityDao.getPageCount(condition);
        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        return scriptPage;
    }
}
