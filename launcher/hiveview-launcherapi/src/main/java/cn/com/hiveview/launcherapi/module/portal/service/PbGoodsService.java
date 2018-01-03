package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PbsGoodsList;
import cn.com.hiveview.launcherapi.module.portal.condition.PbGoodsCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.PbGoodsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by admin on 2017/12/4.
 */
@Service
public class PbGoodsService {
    @Autowired
    public PbGoodsDao pbGoodsDao;
    public ScriptPage<PbsGoodsList> getPageList(PbGoodsCondition condition) throws Exception {
        ScriptPage<PbsGoodsList> scriptPage = new ScriptPage<PbsGoodsList>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<PbsGoodsList> rows = this.pbGoodsDao.getPageList(condition);
        int total = this.pbGoodsDao.getPageCount(condition);
        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        return scriptPage;
    }
}
