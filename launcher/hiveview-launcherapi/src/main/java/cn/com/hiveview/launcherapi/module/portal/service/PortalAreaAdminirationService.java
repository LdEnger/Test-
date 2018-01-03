package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalAreaAdministrationListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalAreaAdminirationListCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalAreaContentListCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalAreaAdminirationDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalAreaContentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2017/10/6.
 */
@Service
public class PortalAreaAdminirationService {

    @Autowired
    private PortalAreaAdminirationDao portalAreaAdminirationDao;

    @Autowired
    private PortalAreaContentDao portalAreaContentDao;

    public ScriptPage<PortalAreaAdministrationListVo> getPage(PortalAreaAdminirationListCondition condition) throws  Exception{
        ScriptPage<PortalAreaAdministrationListVo> scriptPage = new ScriptPage<>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<PortalAreaAdministrationListVo> rows = this.portalAreaAdminirationDao.getPage(condition);
        Integer total = this.portalAreaAdminirationDao.getCount(condition);
        scriptPage.setTotal(total);
        scriptPage.setRows(rows);
        return scriptPage;
    }
    public Integer delete(PortalAreaAdminirationListCondition condition) throws  Exception{
   /*     PortalAreaContentListCondition portalAreaContentListCondition=new PortalAreaContentListCondition();
        portalAreaContentListCondition.setAreaId(condition.getId());
        if(portalAreaContentDao.getCount(portalAreaContentListCondition)>0){
            return -1;
        }*/
        return this.portalAreaAdminirationDao.delete(condition);
    }
    public Integer save(PortalAreaAdminirationListCondition condition) throws Exception{
        return  this.portalAreaAdminirationDao.save(condition);
    }
    public Integer update(PortalAreaAdminirationListCondition condition)throws Exception{
        return this.portalAreaAdminirationDao.update(condition);
    }
}
