package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.CustomRecomLayoutVo;
import cn.com.hiveview.entity.module.portal.CustomRecomTempleteListVo;
import cn.com.hiveview.entity.module.portal.PortalLauncherTempletTabList;
import cn.com.hiveview.launcherapi.module.portal.condition.*;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalLauncherTempletTabDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalTabDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalTabGroupDao;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/27.
 */
@Service
public class PortalLauncherTempletTabService {
    @Autowired
    private ShardedJedisPool shardedJedisPool;
    private static String fixKey = "userid_:";
    @Autowired
    private PortalLauncherTempletTabDao pDao;
    @Autowired
    private PortalTabGroupDao gDao;
    @Autowired
    private PortalTabDao tDao;
    @Autowired
    private NavigationService nS;
    @Autowired
    private CustomRecomTempleteListService crtService;
    @Autowired
    private CustomRecomGetLayoutService crt1Service;
    public ScriptPage<PortalLauncherTempletTabList> getPageList(PortalLauncherTempletTabCondition condition) throws Exception {
        ScriptPage<PortalLauncherTempletTabList> scriptPage = new ScriptPage<PortalLauncherTempletTabList>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<PortalLauncherTempletTabList> rows = this.pDao.getPageList(condition);
        for(int i=0;i<rows.size();i++){
            PortalTabGroupCondition gCondition =new PortalTabGroupCondition();
            //定义查询tabOne的参数
            PortalTabCondition tabCondition=new PortalTabCondition();
            tabCondition.setId(rows.get(i).getTabId());
            //定义接收tabOne的对象
            PortalTabCondition tabCondition1=new PortalTabCondition();
            tabCondition1=tDao.getOne(tabCondition);
            gCondition.setBelongTabId(rows.get(i).getTabId());
            Integer count=gDao.getCount(gCondition);
            rows.get(i).setGroupCount(count);
            rows.get(i).setTabTitle(tabCondition1.getTabTitle());
        }
        int total = this.pDao.getPageCount(condition);
        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        return scriptPage;
    }
    public Integer add(List<PortalLauncherTempletTabCondition> conditions) throws Exception {
        Integer addCount=-1;
        for (PortalLauncherTempletTabCondition condition : conditions) {
            PortalLauncherTempletTabList minTab=pDao.getTopSeq(condition);
            PortalLauncherTempletTabCondition condition2=new PortalLauncherTempletTabCondition();
            if(minTab==null){
                condition.setSeq(100);
                condition.setSeqIsTop(1);
                if(pDao.getCount(condition)>=1){
                    return -1;
                }
                addCount = this.pDao.add(condition);
                nS.flushNavigationListCacheByTmpId(condition.getBelongTempletId());
            }else {
                Integer minSeq=minTab.getSeq();
                condition.setSeq(minSeq-1);
                condition.setSeqIsTop(1);
                condition2.setId(minTab.getId());
                condition2.setSeqIsTop(0);
                if(pDao.getCount(condition)>=1){
                    return -1;
                }
                addCount = this.pDao.add(condition);
                if(addCount==0){
                    return addCount;
                }
                addCount=this.pDao.update(condition2);
                nS.flushNavigationListCacheByTmpId(condition.getBelongTempletId());
            }
        }
        return addCount;
    }
    public Integer delete(PortalLauncherTempletTabCondition condition) throws Exception {
        //判断如果传的参数为空时，删除失败
        if(condition.getId()==null && condition.getTabId()==null && condition.getBelongTempletId()==null){
            return 0;
        }
        if(condition.getSeqIsTop()==1){
            PortalLauncherTempletTabList tab=pDao.getMaxTab(condition);
            if(tab.getId()!=null){
                PortalLauncherTempletTabCondition tab2=new PortalLauncherTempletTabCondition();
                tab2.setId(tab.getId());
                tab2.setSeqIsTop(1);
                pDao.update(tab2);
                Integer result= this.pDao.delete(condition);
                nS.flushNavigationListCacheByTmpId(condition.getBelongTempletId());
                return result;
            }
        }
        Integer re= this.pDao.delete(condition);
        nS.flushNavigationListCacheByTmpId(condition.getBelongTempletId());
        return re;
    }
    public Integer updateMoveTop(PortalLauncherTempletTabCondition condition) throws Exception {
        Integer result=0;
        PortalLauncherTempletTabList topCondition=pDao.getTopSeq(condition);
        //condition1:之前为最顶的Tab
        PortalLauncherTempletTabCondition condition1=new PortalLauncherTempletTabCondition();
        condition1.setId(topCondition.getId());
        condition1.setSeqIsTop(0);
        result = pDao.update(condition1);
        nS.flushNavigationListCacheByTmpId(condition1.getBelongTempletId());
        if(result==0){
            return result;
        }
        condition.setSeqIsTop(1);
        int seq=topCondition.getSeq()-1;
        condition.setSeq(seq);
        result = pDao.update(condition);
        nS.flushNavigationListCacheByTmpId(condition.getBelongTempletId());
        return result;
    }
    public Integer updateMoveUp(PortalLauncherTempletTabCondition condition) throws Exception {
        Integer result=0;
        PortalLauncherTempletTabList minTab = pDao.getMinTab(condition);
        //condition1:之前为上面的Tab
        PortalLauncherTempletTabCondition condition1=new PortalLauncherTempletTabCondition();
        if(minTab.getSeqIsTop()==1){
            condition1.setId(minTab.getId());
            condition1.setSeq(condition.getSeq());
            condition1.setSeqIsTop(0);
            result = pDao.update(condition1);
            nS.flushNavigationListCacheByTmpId(condition1.getBelongTempletId());
            if(result==0){
                return result;
            }
            condition.setSeq(minTab.getSeq());
            condition.setSeqIsTop(1);
            result = pDao.update(condition);
            nS.flushNavigationListCacheByTmpId(condition.getBelongTempletId());
        }else {
            condition1.setId(minTab.getId());
            condition1.setSeq(condition.getSeq());
            result = pDao.update(condition1);
            nS.flushNavigationListCacheByTmpId(condition1.getBelongTempletId());
            if(result == 0){
                return result;
            }
            condition.setSeq(minTab.getSeq());
            result = pDao.update(condition);
            nS.flushNavigationListCacheByTmpId(condition.getBelongTempletId());
        }
        return result;
    }
    public Integer updateMoveDown(PortalLauncherTempletTabCondition condition) throws Exception {
        Integer result=0;
        PortalLauncherTempletTabList maxTab = pDao.getMaxTab(condition);
        //condition1:之前为下面的Tab
        PortalLauncherTempletTabCondition condition1=new PortalLauncherTempletTabCondition();
        if(condition.getSeqIsTop()==1){
            condition1.setId(maxTab.getId());
            condition1.setSeq(condition.getSeq());
            condition1.setSeqIsTop(1);
            result = pDao.update(condition1);
            nS.flushNavigationListCacheByTmpId(condition1.getBelongTempletId());
            if(result==0){
                return result;
            }
            condition.setSeq(maxTab.getSeq());
            condition.setSeqIsTop(0);
            result = pDao.update(condition);
            nS.flushNavigationListCacheByTmpId(condition.getBelongTempletId());
        }else {
            condition1.setId(maxTab.getId());
            condition1.setSeq(condition.getSeq());
            result = pDao.update(condition1);
            nS.flushNavigationListCacheByTmpId(condition1.getBelongTempletId());
            if(result==0){
                return result;
            }
            condition.setSeq(maxTab.getSeq());
            result = pDao.update(condition);
            nS.flushNavigationListCacheByTmpId(condition.getBelongTempletId());
        }
        return result;
    }
    public Integer update(PortalLauncherTempletTabCondition condition) throws Exception {
        //判断如果传的参数为空时，更新失败
        if(condition.getId()==null && condition.getTabId()==null){
            return 0;
        }
        Integer re= this.pDao.update(condition);
        nS.flushNavigationListCacheByTmpId(condition.getBelongTempletId());
        return re;
    }
    public void updateTab(PortalTabCondition condition) throws Exception {
        PortalLauncherTempletTabCondition updateCondition=new PortalLauncherTempletTabCondition();
        updateCondition.setTabId(condition.getId());
        updateCondition.setTabName(condition.getTabName());
        updateCondition.setTabIcon(condition.getTabIcon());
        this.pDao.update(updateCondition);
    }
    public Integer getMaxSeq(PortalLauncherTempletTabCondition condition) throws Exception {
        return this.pDao.getMaxSeq(condition);
    }
    public PortalLauncherTempletTabList getMinTab(PortalLauncherTempletTabCondition condition){
        return this.pDao.getMinTab(condition);
    }
    public PortalLauncherTempletTabList getMaxTab(PortalLauncherTempletTabCondition condition){
        return this.pDao.getMaxTab(condition);
    }
    public PortalLauncherTempletTabList getTopSeq(PortalLauncherTempletTabCondition condition){
        return this.pDao.getTopSeq(condition);
    }
    public Integer getCount(PortalLauncherTempletTabCondition condition){
        return this.pDao.getCount(condition);
    }

    public JSONArray preview(PortalTabGroupCondition condition) throws Exception{
        List<PortalTabGroupCondition> listCondition=gDao.getPageList(condition);
        CustomRecomTempleteCondition crtCondition=new CustomRecomTempleteCondition();
        CustomRecomTempleteCondition crtCondition1=new CustomRecomTempleteCondition();
        JSONArray ja1 = new JSONArray();
        for (PortalTabGroupCondition portalTabGroupCondition : listCondition) {
            Integer belongGroupId = portalTabGroupCondition.getBelongGroupId();
            crtCondition.setTempleteId(belongGroupId);
            CustomRecomTempleteListVo customRecomTempleteListVo = crtService.get(crtCondition);
            crtCondition1.setTempleteId(customRecomTempleteListVo.getTempleteId());
            crtCondition1.setFatherId(customRecomTempleteListVo.getFatherId());
            JSONArray ja = crtService.getContent(crtCondition1);
            JSONArray tempJaList = new JSONArray();
            for(Object crlVo : ja) {
                CustomRecomLayoutVo tempVo = new CustomRecomLayoutVo();
                BeanUtils.copyProperties(crlVo, tempVo);
                tempJaList.add(tempVo);
            }
            tempJaList.add(portalTabGroupCondition.getGroupName());
            ja1.add(tempJaList);
        }
        return ja1;
    }

}
