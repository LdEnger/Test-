package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalCustomizeApkList;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalBeanCurdEditListCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalCustomizeApkCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalCustomizeApkVersionCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalNotStartInstructionListConditon;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalCustomizeApkListDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalCustomizeApkVersionListDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalNotStartInstructionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedisPool;

import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */
@Service
public class PortalCustomizeApkListService {
    @Autowired
    private ShardedJedisPool shardedJedisPool;
    private static String fixKey = "userid_:";
    @Autowired
    private PortalCustomizeApkListDao pDao;
    @Autowired
    private PortalCustomizeApkVersionListDao vDao;
    @Autowired
    private PortalNotStartInstructionDao nDao;
    @Autowired
    private PortalBeanCurdEditListService curdService;
    @Autowired
    private PortalNotStartInstructionService portalNotStartInstructionService;

    public ScriptPage<PortalCustomizeApkList> getPageList(PortalCustomizeApkCondition condition) throws Exception {
        ScriptPage<PortalCustomizeApkList> scriptPage = new ScriptPage<PortalCustomizeApkList>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<PortalCustomizeApkList> rows = this.pDao.getPageList(condition);
        int total = this.pDao.getPageCount(condition);
        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        //System.out.println("ApiService:"+scriptPage);
        return scriptPage;
    }
    public List<PortalCustomizeApkList> getComboboxList(PortalCustomizeApkCondition condition) throws Exception {
        List<PortalCustomizeApkList> rows = this.pDao.getComboboxList(condition);
        return rows;
    }
    public Integer add(PortalCustomizeApkCondition condition) throws Exception {
        return this.pDao.add(condition);
    }
    public Integer delete(PortalCustomizeApkCondition condition) throws Exception {
        //删除之前做校验，判断是否被非开机启动指令管理关联
        PortalNotStartInstructionListConditon portalNotStartInstructionListConditon=new PortalNotStartInstructionListConditon();
        portalNotStartInstructionListConditon.setStartApk(condition.getId());
        if(nDao.getCount(portalNotStartInstructionListConditon)>0){
            return -1;
        }
        //删除apk之前删除apk的所有版本号
        PortalCustomizeApkVersionCondition version = new PortalCustomizeApkVersionCondition();
        version.setApkId(condition.getId());
        Integer i=vDao.getPageCount(version);
        if(i>0){
            if(vDao.delete(version)>0){
                return this.pDao.delete(condition);
            }
            return 0;
        }
        return this.pDao.delete(condition);

    }
    public Integer update(PortalCustomizeApkCondition condition) throws Exception {
        Integer i=this.pDao.update(condition);
        PortalNotStartInstructionListConditon updateBean =new PortalNotStartInstructionListConditon();
        updateBean.setStartApk(condition.getId());
        portalNotStartInstructionService.updateNotice(updateBean);
        curdService.updateTime(condition);
        return i;

    }

}
