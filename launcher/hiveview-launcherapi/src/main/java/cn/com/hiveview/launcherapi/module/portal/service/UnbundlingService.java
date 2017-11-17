package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.portal.PortalCustomRecomContentVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalSysAppIconsListCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.UnbundlingCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.UnbundlingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2017/8/17.
 */
@Service
public class UnbundlingService {
    @Autowired
    private UnbundlingDao unbundlingDao;
    @Autowired
    private PortalCustomRecomContentService portalCustomRecomContentService;
    public Integer delete(UnbundlingCondition condition) throws Exception {
            //去删备份库
            this.unbundlingDao.deleteBackupsContent(condition);
            //自定义推荐位
            portalCustomRecomContentService.delNotice(condition);
            //portalCustomRecomContentService.updateContentById(list);
            //在删除之前,进行备份替换操作
            //通过片子id和包名 去自定义表里查  返回多个自定义推荐位id
            this.unbundlingDao.deletefixedRecomContent(condition);
            //this.unbundlingDao.deleteScreentRecommendContent(condition);
        return 1;
    }
}
