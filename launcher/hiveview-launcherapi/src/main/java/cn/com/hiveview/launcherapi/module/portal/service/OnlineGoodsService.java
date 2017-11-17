package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.NewContentChanList;
import cn.com.hiveview.entity.module.portal.OnlineGoodsList;
import cn.com.hiveview.launcherapi.module.portal.condition.NewContentChanCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.OnlineGoodsCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.NewContentChanDao;
import cn.com.hiveview.launcherapi.module.portal.dao.OnlineGoodsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2017/8/2.
 */
@Service
public class OnlineGoodsService {

    @Autowired
    public OnlineGoodsDao onlineGoodsDao;
    public ScriptPage<OnlineGoodsList> getPageList(OnlineGoodsCondition condition) throws Exception {
        ScriptPage<OnlineGoodsList> scriptPage = new ScriptPage<OnlineGoodsList>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<OnlineGoodsList> rows = this.onlineGoodsDao.getPageList(condition);
        int total = this.onlineGoodsDao.getPageCount(condition);
        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        return scriptPage;
    }
}
