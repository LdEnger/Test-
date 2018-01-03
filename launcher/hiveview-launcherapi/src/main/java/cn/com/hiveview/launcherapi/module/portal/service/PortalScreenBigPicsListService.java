package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalScreenBigPicsListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalCustomRecomContentCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalScreenBigPicsListCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalScreentRecommendContentCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalCustomRecomContentDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalScreenBigPicsListDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalScreentRecommendContentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2017/7/11.
 */
@Service
public class PortalScreenBigPicsListService {

    @Autowired
    private NavigationService  navigationService;

    @Autowired
    private PortalScreenBigPicsListDao portalScreenBigPicsListDao;

    @Autowired
    private PortalScreentRecommendContentDao portalScreentRecommendContentDao;

    @Autowired
    private PortalCustomRecomContentDao portalCustomRecomContentDao;

    public ScriptPage<PortalScreenBigPicsListVo> getList(PortalScreenBigPicsListCondition condition) throws Exception {
        ScriptPage<PortalScreenBigPicsListVo> scriptPage = new ScriptPage<PortalScreenBigPicsListVo>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<PortalScreenBigPicsListVo> rows = this.portalScreenBigPicsListDao.getList(condition);
        Integer total = this.portalScreenBigPicsListDao.getCount(condition);
        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        return scriptPage;
    }
    public Integer save(PortalScreenBigPicsListCondition condition) throws Exception {
        Integer rev = this.portalScreenBigPicsListDao.save(condition);
        navigationService.flushDefaultTapPicCache();
        return rev;
    }
    public Integer delete(PortalScreenBigPicsListCondition condition) throws Exception {
        PortalScreentRecommendContentCondition pCondition = new PortalScreentRecommendContentCondition();
        pCondition.setContentId(condition.getImgId());
        pCondition.setContentType(6);
        PortalCustomRecomContentCondition psConditon = new PortalCustomRecomContentCondition();
        psConditon.setContentType(6);
        psConditon.setContentId(condition.getImgId());
        Integer pResult = portalScreentRecommendContentDao.getBigPic(pCondition);
        Integer psResult = portalCustomRecomContentDao.getCount(psConditon);
        if((pResult > 0) || (psResult > 0) ){
            return -1;
        }
        Integer rev = this.portalScreenBigPicsListDao.delete(condition);
        navigationService.flushDefaultTapPicCache();
        return rev;
    }
    public Integer update(PortalScreenBigPicsListCondition condition) throws Exception {
        Integer srv = this.portalScreenBigPicsListDao.update(condition);
        navigationService.flushDefaultTapPicCache();
        return srv;
    }

    public Integer updateBig(PortalScreenBigPicsListCondition condition)throws Exception{
        Integer rev =  this.portalScreenBigPicsListDao.updateBig(condition);
        navigationService.flushDefaultTapPicCache();
        return rev;
    }

}
