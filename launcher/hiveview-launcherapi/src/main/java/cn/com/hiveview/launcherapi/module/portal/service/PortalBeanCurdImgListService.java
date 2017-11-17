package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalBeanCurdImgListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalBeanCurdImgListCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalBeanCurdImgListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2017/7/26.
 */
@Service
public class PortalBeanCurdImgListService {

    @Autowired
    private PortalBeanCurdImgListDao portalBeanCurdImgListDao;

    public ScriptPage<PortalBeanCurdImgListVo> getPageList(PortalBeanCurdImgListCondition portalBeanCurdImgListCondition) throws  Exception{
        ScriptPage<PortalBeanCurdImgListVo> scriptPage = new ScriptPage<>();
        portalBeanCurdImgListCondition.setPageIndex(portalBeanCurdImgListCondition.getPage());
        portalBeanCurdImgListCondition.setPageSize(portalBeanCurdImgListCondition.getRows());
        List<PortalBeanCurdImgListVo> rows = this.portalBeanCurdImgListDao.getPageList(portalBeanCurdImgListCondition);
        Integer total = this.portalBeanCurdImgListDao.getCount(portalBeanCurdImgListCondition);
        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        return scriptPage;
    }

    public Integer update(PortalBeanCurdImgListCondition portalBeanCurdImgListCondition)throws  Exception{
        return  this.portalBeanCurdImgListDao.update(portalBeanCurdImgListCondition);
    }

    public Integer delete(PortalBeanCurdImgListCondition portalBeanCurdImgListCondition)throws Exception{
        return  this.portalBeanCurdImgListDao.delete(portalBeanCurdImgListCondition);
    }
    public PortalBeanCurdImgListVo getImgList(PortalBeanCurdImgListCondition portalBeanCurdImgListCondition)throws  Exception{
        return  this.portalBeanCurdImgListDao.getImgList(portalBeanCurdImgListCondition);
    }
    public  Integer save(PortalBeanCurdImgListCondition portalBeanCurdImgListCondition)throws Exception{
        return  this.portalBeanCurdImgListDao.save(portalBeanCurdImgListCondition);
    }
}
