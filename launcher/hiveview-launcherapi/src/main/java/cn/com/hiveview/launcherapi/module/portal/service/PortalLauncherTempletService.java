package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalCustomizeApkList;
import cn.com.hiveview.entity.module.portal.PortalLauncherTempletList;
import cn.com.hiveview.entity.module.portal.PortalLauncherTempletTabList;
import cn.com.hiveview.launcherapi.module.portal.condition.*;
import cn.com.hiveview.launcherapi.module.portal.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedisPool;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/7/27.
 */
@Service
public class PortalLauncherTempletService {
    @Autowired
    private ShardedJedisPool shardedJedisPool;
    private static String fixKey = "userid_:";
    @Autowired
    private PortalLauncherTempletDao pDao;
    @Autowired
    private PortalLauncherTempletTabDao tDao;
    @Autowired
    private EntranceAreaDao eDao;
    @Autowired
    private NavigationService nS;
    public ScriptPage<PortalLauncherTempletList> getPageList(PortalLauncherTempletCondition condition) throws Exception {
        ScriptPage<PortalLauncherTempletList> scriptPage = new ScriptPage<PortalLauncherTempletList>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<PortalLauncherTempletList> rows = this.pDao.getPageList(condition);
        int total = this.pDao.getPageCount(condition);
        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        //System.out.println("ApiService:"+scriptPage);
        return scriptPage;
    }
    public Integer add(PortalLauncherTempletCondition condition) throws Exception {
        return this.pDao.add(condition);
    }
    public Integer delete(PortalLauncherTempletCondition condition) throws Exception {
        //删除区域配置内容
        EntranceAreaCondition area = new EntranceAreaCondition();
        area.setAreaId(condition.getId());
        eDao.deleteCitys(area);
        //删除关联的导航栏
        PortalLauncherTempletTabCondition content = new PortalLauncherTempletTabCondition();
        content.setBelongTempletId(condition.getId());
        Integer i=tDao.getPageCount(content);
        if(i>0){
            if(tDao.delete(content)>0){
                return this.pDao.delete(condition);
            }
            return 0;
        }
        return this.pDao.delete(condition);
    }
    public Integer update(PortalLauncherTempletCondition condition) throws Exception {
        return this.pDao.update(condition);
    }

    public Integer addCopy(PortalLauncherTempletCondition condition) throws Exception {
        Integer result=null;
        PortalLauncherTempletCondition copyCondition=new PortalLauncherTempletCondition();
        copyCondition.setLogoId(condition.getLogoId());
        copyCondition.setAutoId(condition.getAutoId());
        copyCondition.setTempletName(condition.getTempletName()+"-副本");
        copyCondition.setIsHide(1);
        Integer addCondition=this.pDao.add(copyCondition);
        if(addCondition==0){
            return addCondition;
        }
        Integer templetId=copyCondition.getId();
        PortalLauncherTempletTabCondition tabCondition=new PortalLauncherTempletTabCondition();
        tabCondition.setBelongTempletId(condition.getId());
        List<PortalLauncherTempletTabList> tabList=tDao.getComboboxList(tabCondition);
        if(tabList.size()<=0){
            return addCondition;
        }
        for(int i=0;i<tabList.size();i++){
            PortalLauncherTempletTabCondition tabCondition1=new PortalLauncherTempletTabCondition();
            tabCondition1.setBelongTempletId(templetId);
            tabCondition1.setTabId(tabList.get(i).getTabId());
            tabCondition1.setCreateTime(new Date());
            tabCondition1.setSeq(tabList.get(i).getSeq());
            tabCondition1.setSeqIsTop(tabList.get(i).getSeqIsTop());
            tabCondition1.setTabIcon(tabList.get(i).getTabIcon());
            tabCondition1.setTabName(tabList.get(i).getTabName());
            tabCondition1.setIsEffective(tabList.get(i).getIsEffective());
            result=tDao.add(tabCondition1);
            nS.flushNavigationListCacheByTmpId(tabCondition1.getBelongTempletId());
            if(result==0){
                return result;
            }
        }
        return result;
    }
}
