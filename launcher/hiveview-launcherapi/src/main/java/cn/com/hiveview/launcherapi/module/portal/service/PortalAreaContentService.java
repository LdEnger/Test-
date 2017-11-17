package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalAreaAdministrationListVo;
import cn.com.hiveview.entity.module.portal.PortalAreaContentListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalAreaAdminirationListCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalAreaContentListCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalAreaContentDao;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2017/10/8.
 */
@Service
public class PortalAreaContentService {

    @Autowired
    PortalAreaContentDao portalAreaContentDao;


    public ScriptPage<PortalAreaContentListVo> getPage(PortalAreaContentListCondition condition) throws  Exception{
        ScriptPage<PortalAreaContentListVo> scriptPage = new ScriptPage<>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<PortalAreaContentListVo> rows = this.portalAreaContentDao.getPage(condition);
        Integer total = this.portalAreaContentDao.getCount(condition);
        scriptPage.setTotal(total);
        scriptPage.setRows(rows);
        return scriptPage;
    }

    public  Integer delete(PortalAreaContentListCondition condition)throws Exception{
        return this.portalAreaContentDao.delete(condition);
    }

    public Integer save(PortalAreaContentListCondition condition)throws Exception{
        return this.portalAreaContentDao.save(condition);
    }
    public Integer update(PortalAreaContentListCondition condition)throws Exception{
        return this.portalAreaContentDao.update(condition);
    }

    public PortalAreaContentListVo getAreaMinSeq(PortalAreaContentListCondition condition)throws Exception{
        return  this.portalAreaContentDao.getAreaMinSeq(condition);
    }

    public PortalAreaContentListVo getAreaMaxSeq(PortalAreaContentListCondition condition) throws Exception{
        return  this.portalAreaContentDao.getAreaMaxSeq(condition);
    }
}
