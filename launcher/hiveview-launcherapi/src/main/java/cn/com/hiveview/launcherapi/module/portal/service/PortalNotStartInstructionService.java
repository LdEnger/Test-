package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalNotStartInstructionListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalCustomRecomContentCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalNotStartInstructionListConditon;
import cn.com.hiveview.launcherapi.module.portal.dao.CustomRecomBackupsContentDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalCustomRecomContentDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalNotStartInstructionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2017/10/13.
 */
@Service
public class PortalNotStartInstructionService {

    @Autowired
    private PortalNotStartInstructionDao portalNotStartInstructionDao;
    @Autowired
    private PortalCustomRecomContentDao portalCustomRecomContentDao;
    @Autowired
    private CustomRecomBackupsContentDao customRecomBackupsContentDao;

    public ScriptPage<PortalNotStartInstructionListVo> getPage(PortalNotStartInstructionListConditon conditon) throws Exception{
        ScriptPage<PortalNotStartInstructionListVo> scriptPage =new ScriptPage<>();

        conditon.setPageIndex(conditon.getPage());
        conditon.setPageSize(conditon.getRows());
        List<PortalNotStartInstructionListVo> rows = this.portalNotStartInstructionDao.getPage(conditon);
        Integer total = this.portalNotStartInstructionDao.getCount(conditon);
        scriptPage.setTotal(total);
        scriptPage.setRows(rows);
        return  scriptPage;
    }

    public Integer save(PortalNotStartInstructionListConditon conditon ) throws Exception{
        return this.portalNotStartInstructionDao.save(conditon);
    }
    public Integer update(PortalNotStartInstructionListConditon conditon) throws Exception{
        return this.portalNotStartInstructionDao.update(conditon);
    }
    public Integer updateEffice(PortalNotStartInstructionListConditon conditon) throws Exception{
        return this.portalNotStartInstructionDao.update(conditon);
    }


    public Integer delete(PortalNotStartInstructionListConditon conditon) throws Exception{
        return this.portalNotStartInstructionDao.delete(conditon);
    }

    public ScriptPage<PortalNotStartInstructionListVo> getPageList(PortalNotStartInstructionListConditon conditon) throws Exception{
        ScriptPage<PortalNotStartInstructionListVo> scriptPage =new ScriptPage<>();

        conditon.setPageIndex(conditon.getPage());
        conditon.setPageSize(conditon.getRows());
        List<PortalNotStartInstructionListVo> rows = this.portalNotStartInstructionDao.getPageList(conditon);
        Integer total = this.portalNotStartInstructionDao.getPageCount(conditon);
        scriptPage.setTotal(total);
        scriptPage.setRows(rows);
        return  scriptPage;
    }

    public  void  updateNotice(PortalNotStartInstructionListConditon conditon) throws Exception{
        conditon.setPageIndex(1);
        conditon.setPageSize(1000);
        List<PortalNotStartInstructionListVo> rows = this.portalNotStartInstructionDao.getPageList(conditon);
        for(PortalNotStartInstructionListVo  noVo:rows){
            PortalCustomRecomContentCondition versionVo = new PortalCustomRecomContentCondition();
            versionVo.setContentId(noVo.getId());
            versionVo.setContentType(12);
            versionVo.setApkVersionCode(noVo.getVersionNo());
            versionVo.setApkDownUrl(noVo.getVersionUrl());
            versionVo.setAction(noVo.getActionStr());
            versionVo.setApk(noVo.getPackageName());
            versionVo.setInstallStyle(noVo.getInstallStyle());
            versionVo.setAppType(noVo.getAppType());
            //通知关联内容表
            portalCustomRecomContentDao.updateVersion(versionVo);
            //通知备份表
            portalCustomRecomContentDao.updateBackUpVersion(versionVo);
        }
    }
}
