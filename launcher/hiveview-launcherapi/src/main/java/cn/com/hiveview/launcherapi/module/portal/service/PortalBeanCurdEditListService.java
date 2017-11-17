package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalBeanCurdEditListVo;
import cn.com.hiveview.entity.module.portal.PortalCustomizeApkVersionList;
import cn.com.hiveview.entity.module.portal.PortalStartInstructionList;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalBeanCurdEditListCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalCustomizeApkCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalCustomizeApkVersionCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalStartInstructionCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalBeanCurdEditListDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalCustomizeApkVersionListDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalStartInstructionListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2017/7/25.
 */
@Service
public class PortalBeanCurdEditListService {

    @Autowired
    private PortalBeanCurdEditListDao portalBeanCurdEditListDao;

    @Autowired
    private PortalStartInstructionListDao portalStartInstructionListDao;

    @Autowired
    private PortalCustomizeApkVersionListDao apkDao;


    public ScriptPage<PortalBeanCurdEditListVo> getPageList(PortalBeanCurdEditListCondition portalBeanCurdEditListCondition)throws  Exception{
        ScriptPage<PortalBeanCurdEditListVo> scriptPage = new ScriptPage<>();
        portalBeanCurdEditListCondition.setPageIndex(portalBeanCurdEditListCondition.getPage());
        portalBeanCurdEditListCondition.setPageSize(portalBeanCurdEditListCondition.getRows());
        List<PortalBeanCurdEditListVo> rows = this.portalBeanCurdEditListDao.getPageList(portalBeanCurdEditListCondition);
        Integer total = this.portalBeanCurdEditListDao.getCount(portalBeanCurdEditListCondition);
        scriptPage.setTotal(total);
        scriptPage.setRows(rows);
        return scriptPage;
    }
    public ScriptPage<PortalBeanCurdEditListVo> getList(PortalBeanCurdEditListCondition portalBeanCurdEditListCondition)throws Exception{
        ScriptPage<PortalBeanCurdEditListVo> scriptPage = new ScriptPage<>();
        portalBeanCurdEditListCondition.setPageIndex(portalBeanCurdEditListCondition.getPage());
        portalBeanCurdEditListCondition.setPageSize(portalBeanCurdEditListCondition.getRows());
        List<PortalBeanCurdEditListVo> rows = this.portalBeanCurdEditListDao.getList(portalBeanCurdEditListCondition);
        Integer total = this.portalBeanCurdEditListDao.getCountList(portalBeanCurdEditListCondition);
        scriptPage.setTotal(total);
        scriptPage.setRows(rows);
        return  scriptPage;
    }
    public List<PortalBeanCurdEditListVo> getEditList(PortalBeanCurdEditListCondition portalBeanCurdEditListCondition)throws Exception{
        return this.portalBeanCurdEditListDao.getEditList(portalBeanCurdEditListCondition);
    }
    public PortalBeanCurdEditListVo getEdit(PortalBeanCurdEditListCondition portalBeanCurdEditListCondition)throws  Exception{
        return  this.portalBeanCurdEditListDao.getEdit(portalBeanCurdEditListCondition);
    }

    public Integer delete(PortalBeanCurdEditListCondition portalBeanCurdEditListCondition)throws  Exception{
        return  this.portalBeanCurdEditListDao.delete(portalBeanCurdEditListCondition);
    }
    public Integer getEditCount(PortalBeanCurdEditListCondition portalBeanCurdEditListCondition)throws Exception{
        return this.portalBeanCurdEditListDao.getEditCount(portalBeanCurdEditListCondition);
    }

    public Integer save(PortalBeanCurdEditListCondition p)throws Exception{
        PortalStartInstructionCondition pCoditipn = new PortalStartInstructionCondition();
        PortalCustomizeApkVersionCondition apkVersionCondition = new PortalCustomizeApkVersionCondition();
        if(p.getEntranceAppId() != null) {
            pCoditipn.setId(p.getEntranceAppId());
            PortalStartInstructionList psilCondition = portalStartInstructionListDao.getList(pCoditipn);
            String entranceAppStr = psilCondition.getCharacterString();
            String entranceAppInstall = String.valueOf(psilCondition);//.getStartType()
            p.setIsEffeCtice(0);
            p.setEntranceAppInstall(entranceAppInstall);
            p.setEntranceAppStr(entranceAppStr);
            p.setEntranceAppName(entranceAppStr);
        }else if(p.getCustomizeAppId() != null){
            apkVersionCondition.setApkId(p.getCustomizeAppId());
            apkVersionCondition.setVersionNo(p.getEntranceAppVersion());
            PortalCustomizeApkVersionList apk =  apkDao.getApkPageName(apkVersionCondition);
            p.setEntranceAppStr(apk.getPackageName());
            p.setEntranceAppName(apk.getPackageName());
        }
        p.setIsEffeCtice(0);
        return this.portalBeanCurdEditListDao.save(p);
    }
    public Integer update(PortalBeanCurdEditListCondition p)throws Exception{
        PortalStartInstructionCondition pCoditipn = new PortalStartInstructionCondition();
        PortalCustomizeApkVersionCondition apkVersionCondition = new PortalCustomizeApkVersionCondition();
        if(p.getEntranceAppId() != null) {
            pCoditipn.setId(p.getEntranceAppId());
            PortalStartInstructionList psilCondition = portalStartInstructionListDao.getList(pCoditipn);
            String entranceAppStr = psilCondition.getCharacterString();
            String entranceAppInstall = String.valueOf(psilCondition);//.getStartType()
            p.setEntranceAppInstall(entranceAppInstall);
            p.setEntranceAppStr(entranceAppStr);
            p.setEntranceAppName(entranceAppStr);
        }else if(p.getCustomizeAppId() != null) {
            apkVersionCondition.setApkId(p.getCustomizeAppId());
            apkVersionCondition.setVersionNo(p.getEntranceAppVersion());
            PortalCustomizeApkVersionList apk =  apkDao.getApkPageName(apkVersionCondition);
            p.setEntranceAppStr(apk.getPackageName());
            p.setEntranceAppName(apk.getPackageName());
        }
        return  this.portalBeanCurdEditListDao.update(p);
    }


    public  Integer updateTime(PortalCustomizeApkCondition condition)throws Exception{
        PortalBeanCurdEditListCondition p = new PortalBeanCurdEditListCondition();

        p.setCustomizeAppId(condition.getId());
        PortalCustomizeApkVersionCondition apk  = new PortalCustomizeApkVersionCondition();
        if(condition.getApkName() !=  null && condition.getInstallStyle() != null){
            p.setCustommizeAppName(condition.getApkName());
            p.setEntranceAppInstall(String.valueOf(condition.getInstallStyle()));
        }else {
            apk.setApkId(condition.getId());
            apk.setVersionNo(condition.getLastVersion());
            PortalCustomizeApkVersionList apkv = apkDao.getApkPageName(apk);
            p.setEntranceAppVersion(condition.getLastVersion());
            p.setEntranceAppName(apkv.getPackageName());
            p.setEntranceAppStr(apkv.getPackageName());
        }
        return this.portalBeanCurdEditListDao.updateTime(p);
    }
}
