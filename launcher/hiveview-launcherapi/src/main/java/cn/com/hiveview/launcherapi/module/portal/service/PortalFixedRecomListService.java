package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalFiexdRecomListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalFixedRecomListCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalLauncherTempletContentCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalFixedRecomListDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalLauncherTempletContentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2017/7/14.
 */
@Service
public class PortalFixedRecomListService {

    @Autowired
    private PortalFixedRecomListDao portalFixedRecomListDao;

    @Autowired
    private PortalLauncherTempletContentDao portalLauncherTempletContentDao;

    public ScriptPage<PortalFiexdRecomListVo> getPageList(PortalFixedRecomListCondition condition) throws  Exception{
        ScriptPage<PortalFiexdRecomListVo>  scriptPage = new ScriptPage<>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<PortalFiexdRecomListVo> rows = this.portalFixedRecomListDao.getList(condition);
        Integer total = this.portalFixedRecomListDao.getCount(condition);
        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        return  scriptPage;
    }


    public  Integer save(PortalFixedRecomListCondition condition) throws  Exception{
        return this.portalFixedRecomListDao.save(condition);
    }
    public  Integer delete(PortalFixedRecomListCondition condition) throws  Exception{
        PortalLauncherTempletContentCondition portalLauncherTempletContentCondition=new PortalLauncherTempletContentCondition();
        portalLauncherTempletContentCondition.setRecommendId(condition.getRecomId());
        portalLauncherTempletContentCondition.setRecommendType(1);
        if(portalLauncherTempletContentDao.getCount(portalLauncherTempletContentCondition)>0){
           return -1;
        }
        return  this.portalFixedRecomListDao.delete(condition);
    }
    public  Integer update(PortalFixedRecomListCondition condition) throws  Exception{
        return  this.portalFixedRecomListDao.update(condition);
    }
    public  List<PortalFiexdRecomListVo> getTypeList(PortalFixedRecomListCondition condition) throws  Exception{
        return  this.portalFixedRecomListDao.getTypeList(condition);
    }
    public Integer updateEffective(PortalFixedRecomListCondition condition) throws  Exception{
        return  this.portalFixedRecomListDao.updateEffective(condition);
    }
    public PortalFiexdRecomListVo getFixedRecomListOne(PortalFixedRecomListCondition condition) throws  Exception{
        return  this.portalFixedRecomListDao.getFixedRecomListOne(condition);
    }

}
