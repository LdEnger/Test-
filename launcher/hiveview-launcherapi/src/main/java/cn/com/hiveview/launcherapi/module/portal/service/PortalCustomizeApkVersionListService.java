package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalCustomizeApkVersionList;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalCustomizeApkCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalCustomizeApkVersionCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalNotStartInstructionListConditon;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalCustomizeApkListDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalCustomizeApkVersionListDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalNotStartInstructionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedisPool;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */
@Service
public class PortalCustomizeApkVersionListService {
    @Autowired
    private ShardedJedisPool shardedJedisPool;
    private static String fixKey = "userid_:";
    @Autowired
    private PortalCustomizeApkVersionListDao pDao;
    @Autowired
    private PortalCustomizeApkListDao apkDao;
    @Autowired
    private PortalNotStartInstructionDao notDao;
    @Autowired
    private PortalNotStartInstructionService portalNotStartInstructionService;

    public ScriptPage<PortalCustomizeApkVersionList> getPageList(PortalCustomizeApkVersionCondition condition) throws Exception {
        ScriptPage<PortalCustomizeApkVersionList> scriptPage = new ScriptPage<PortalCustomizeApkVersionList>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<PortalCustomizeApkVersionList> rows = this.pDao.getPageList(condition);
        int total = this.pDao.getPageCount(condition);
        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        return scriptPage;
    }
    public Integer add(PortalCustomizeApkVersionCondition condition) throws Exception {
        if(pDao.getCountByVersion(condition)>0){
            return -1;
        }
        Integer i=this.pDao.add(condition);
        if(i>0){
            //先判断通过apkId查询，如果只有一条记录（上一条语句插入的），则把这条刚加的版本号放到最大的版本号
            Integer j=this.pDao.getCountByApkId(condition);
            if(j==1){
                PortalCustomizeApkCondition apkCondition=new PortalCustomizeApkCondition();
                apkCondition.setLastVersion(condition.getVersionNo());
                apkCondition.setUpdateTime(new Date());
                apkCondition.setId(condition.getApkId());
                apkDao.updateVersion(apkCondition);
                PortalNotStartInstructionListConditon updateBean =new PortalNotStartInstructionListConditon();
                updateBean.setStartApk(condition.getApkId());
                portalNotStartInstructionService.updateNotice(updateBean);
                //curdService.updateTime(apkCondition);
                return 1;
            }
            String maxVersion= pDao.getMaxVersion(condition);
            PortalCustomizeApkCondition apkCondition=new PortalCustomizeApkCondition();
            apkCondition.setLastVersion(maxVersion);
            apkCondition.setUpdateTime(new Date());
            apkCondition.setId(condition.getApkId());
            apkDao.updateVersion(apkCondition);
            PortalNotStartInstructionListConditon updateBean =new PortalNotStartInstructionListConditon();
            updateBean.setStartApk(condition.getApkId());
            portalNotStartInstructionService.updateNotice(updateBean);
            //curdService.updateTime(apkCondition);
            return 1;
        }else {
            return 0;
        }
    }
    public Integer delete(PortalCustomizeApkVersionCondition condition) throws Exception {
        //判断如果传的参数为空时，删除失败
        if(condition.getId()==null && condition.getApkId()==null){
            return 0;
        }
        PortalNotStartInstructionListConditon notCondition=new PortalNotStartInstructionListConditon();
        notCondition.setStartApk(condition.getApkId());
        if(notDao.getCount(notCondition)>0){
            if(pDao.getCountByApkId(condition)==1){
                return -1;
            }
        }
        Integer i=this.pDao.delete(condition);
        if(i>0){
            String maxVersion= pDao.getMaxVersion(condition);
            PortalCustomizeApkCondition apkCondition=new PortalCustomizeApkCondition();
            apkCondition.setLastVersion(maxVersion);
            apkCondition.setUpdateTime(new Date());
            apkCondition.setId(condition.getApkId());
            apkDao.updateVersion(apkCondition);
            PortalNotStartInstructionListConditon updateBean =new PortalNotStartInstructionListConditon();
            updateBean.setStartApk(condition.getApkId());
            portalNotStartInstructionService.updateNotice(updateBean);
            //curdService.updateTime(apkCondition);
            return 1;
        }else {
            return 0;
        }
    }
    public Integer update(PortalCustomizeApkVersionCondition condition) throws Exception {
        if(pDao.getCountByVersion(condition)>0){
            return -1;
        }
        Integer i=this.pDao.update(condition);
        if(i>0){
            //先判断通过apkId查询，如果只有一条记录（上一条语句插入的），则把这条刚加的版本号放到最大的版本号
            Integer j=this.pDao.getCountByApkId(condition);
            if(j==1){
                PortalCustomizeApkCondition apkCondition=new PortalCustomizeApkCondition();
                apkCondition.setLastVersion(condition.getVersionNo());
                apkCondition.setUpdateTime(new Date());
                apkCondition.setId(condition.getApkId());
                apkDao.updateVersion(apkCondition);
                PortalNotStartInstructionListConditon updateBean =new PortalNotStartInstructionListConditon();
                updateBean.setStartApk(condition.getApkId());
                portalNotStartInstructionService.updateNotice(updateBean);
                //curdService.updateTime(apkCondition);
                return 1;
            }
            String maxVersion= pDao.getMaxVersion(condition);
            PortalCustomizeApkCondition apkCondition=new PortalCustomizeApkCondition();
            apkCondition.setLastVersion(maxVersion);
            apkCondition.setUpdateTime(new Date());
            apkCondition.setId(condition.getApkId());
            apkDao.updateVersion(apkCondition);
            PortalNotStartInstructionListConditon updateBean =new PortalNotStartInstructionListConditon();
            updateBean.setStartApk(condition.getApkId());
            portalNotStartInstructionService.updateNotice(updateBean);
            //curdService.updateTime(apkCondition);
            return 1;
        }else {
            return 0;
        }
    }
}
