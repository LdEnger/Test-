package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalBeanCurdEditListVo;
import cn.com.hiveview.entity.module.portal.PortalBeanCurdListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalBeanCurdListCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalLauncherTempletCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalBeanCurdListDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalLauncherTempletDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2017/7/24.
 */
@Service
public class PortalBeanCurdListService {

    @Autowired
    private PortalBeanCurdListDao portalBeanCurdListDao;


    @Autowired
    private PortalLauncherTempletDao launDao;

    public ScriptPage<PortalBeanCurdEditListVo> getPageList(PortalBeanCurdListCondition portalBeanCurdListCondition)throws  Exception{
            ScriptPage<PortalBeanCurdEditListVo> scriptPage = new ScriptPage<>();
            portalBeanCurdListCondition.setPageIndex(portalBeanCurdListCondition.getPage());
            portalBeanCurdListCondition.setPageSize(portalBeanCurdListCondition.getRows());
            List<PortalBeanCurdEditListVo> rows = this.portalBeanCurdListDao.getPageList(portalBeanCurdListCondition);
            Integer total = this.portalBeanCurdListDao.getCount(portalBeanCurdListCondition);
            scriptPage.setTotal(total);
            scriptPage.setRows(rows);
            return scriptPage;
    }

    public Integer save(PortalBeanCurdListCondition portalBeanCurdListCondition)throws Exception{
        return this.portalBeanCurdListDao.save(portalBeanCurdListCondition);
    }
    public Integer delete(PortalBeanCurdListCondition condition)throws  Exception{
        PortalLauncherTempletCondition portalLauncherTempletCondition=new PortalLauncherTempletCondition();
        portalLauncherTempletCondition.setBlockId(condition.getCurdID());
        if(launDao.getCount(portalLauncherTempletCondition)>0){
            return -1;
        }
        return this.portalBeanCurdListDao.delete(condition);
    }
    public Integer update(PortalBeanCurdListCondition portalBeanCurdListCondition)throws  Exception{
        return this.portalBeanCurdListDao.update(portalBeanCurdListCondition);
    }
    public  Integer updateIs(PortalBeanCurdListCondition portalBeanCurdListCondition)throws  Exception{
        return  this.portalBeanCurdListDao.updateIs(portalBeanCurdListCondition);
    }
    public List<PortalBeanCurdListVo> getCurdList(PortalBeanCurdListCondition portalBeanCurdListCondition)throws Exception{
        return  this.portalBeanCurdListDao.getCurdList(portalBeanCurdListCondition);
    }
}
