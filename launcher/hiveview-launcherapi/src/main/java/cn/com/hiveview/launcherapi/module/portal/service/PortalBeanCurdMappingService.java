package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalBeanCurdEditListVo;
import cn.com.hiveview.entity.module.portal.PortalBeanCurdMappingListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalBeanCurdEditListCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalBeanCurdMappingListCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalBeanCurdEditListDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalBeanCurdMappingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2017/8/2.
 */
@Service
public class PortalBeanCurdMappingService {

    @Autowired
    private PortalBeanCurdMappingDao portalBeanCurdMappingDao;

    @Autowired
    private PortalBeanCurdEditListDao portalBeanCurdEditListDao;

    public ScriptPage<PortalBeanCurdMappingListVo> getPageList(PortalBeanCurdMappingListCondition portalBeanCurdMappingListCondition) throws Exception{
        ScriptPage<PortalBeanCurdMappingListVo>  scriptPage = new ScriptPage<>();
        portalBeanCurdMappingListCondition.setPageIndex(portalBeanCurdMappingListCondition.getPage());
        portalBeanCurdMappingListCondition.setPageSize(portalBeanCurdMappingListCondition.getRows());
        List<PortalBeanCurdMappingListVo>  rows = this.portalBeanCurdMappingDao.getPageList(portalBeanCurdMappingListCondition);
        Integer total = this.portalBeanCurdMappingDao.getCount(portalBeanCurdMappingListCondition);
        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        return  scriptPage;
    }
    public Integer save(PortalBeanCurdMappingListCondition p)throws Exception{
        PortalBeanCurdEditListCondition edit = new PortalBeanCurdEditListCondition();
        edit.setEntranceId(p.getEntranceId());
        PortalBeanCurdEditListVo editVo = portalBeanCurdEditListDao.getEdit(edit);
        Integer isEffective = editVo.getIsEffeCtice();
        Integer seq = portalBeanCurdMappingDao.getMaxSeq(p);
        seq = seq == null ? 1 : seq + 1;
        p.setSeq(seq);
        p.setIsEffective(isEffective);
        return   this.portalBeanCurdMappingDao.save(p);
    }

    public Integer delete(PortalBeanCurdMappingListCondition portalBeanCurdMappingListCondition) throws  Exception{
        return this.portalBeanCurdMappingDao.delete(portalBeanCurdMappingListCondition);
    }
    public Integer update(PortalBeanCurdMappingListCondition portalBeanCurdMappingListCondition)throws  Exception{
        return  this.portalBeanCurdMappingDao.update(portalBeanCurdMappingListCondition);
    }
    public Integer getMappingCount(PortalBeanCurdMappingListCondition portalBeanCurdMappingListCondition)throws Exception{
        return  this.portalBeanCurdMappingDao.getMappingCount(portalBeanCurdMappingListCondition);
    }
    public PortalBeanCurdMappingListVo getMinMapping(PortalBeanCurdMappingListCondition portalBeanCurdMappingListCondition)throws Exception{
        return this.portalBeanCurdMappingDao.getMinMapping(portalBeanCurdMappingListCondition);
    }
    public PortalBeanCurdMappingListVo getMaxMapping(PortalBeanCurdMappingListCondition portalBeanCurdMappingListCondition)throws Exception{
        return this.portalBeanCurdMappingDao.getMaxMapping(portalBeanCurdMappingListCondition);
    }

    public Integer getMinSeq(PortalBeanCurdMappingListCondition portalBeanCurdMappingListCondition)throws Exception{
        return this.portalBeanCurdMappingDao.getMinSeq(portalBeanCurdMappingListCondition);
    }
}
