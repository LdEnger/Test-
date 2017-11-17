package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalCustomRecomContentVo;
import cn.com.hiveview.entity.module.portal.PortalLauncherTempletTabList;
import cn.com.hiveview.launcherapi.module.portal.condition.*;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalCustomRecomContentDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalLauncherTempletTabDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalTabDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalTabGroupDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Condition;

/**
 * Created by xiach on 2017/10/9.
 */
@Service
public class PortalTabService {
    @Autowired
    private PortalTabDao portalTabDao;
    @Autowired
    private PortalTabGroupDao portalTabGroupDao;
    @Autowired
    private PortalLauncherTempletTabDao portalLauncherTempletTabDao;
    @Autowired
    private PortalCustomRecomContentDao portalCustomRecomContentDao;
    @Autowired
    private NavigationService navigationService;

    /**
     *
     * @param condition
     * @return  查询一页数据
     * @throws Exception
     */
    public ScriptPage<PortalTabCondition> getPageList(PortalTabCondition condition) throws Exception {
        ScriptPage<PortalTabCondition> scriptPage = new ScriptPage<PortalTabCondition>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<PortalTabCondition> rows = this.portalTabDao.getPageList(condition);
        int total = this.portalTabDao.getCount(condition);
        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        return scriptPage;
    }

    /*增删改*/
    public Integer add(PortalTabCondition condition)throws Exception{
        Integer insertCount = -1;
        Integer copyId=condition.getId();
        Integer maxSeq = portalTabDao.getMaxSeq();
        if(maxSeq==null){//当表中没有数据从一开始排序
            condition.setSeq(1);
        }else {
            condition.setSeq(maxSeq+1);//新添加置顶排序
        }
        if(condition.getId()==null){//判断是复制，新建
            condition.setCreateTime(new Date());
            insertCount = portalTabDao.add(condition);
        }else {
            condition = portalTabDao.getOne(condition);
            condition.setTabName(condition.getTabName()+"-副本");
            condition.setTabTitle(condition.getTabTitle()+"-副本");
            insertCount = portalTabDao.add(condition);//先复制tab生成id
            PortalTabGroupCondition portalTabGroupCondition = new PortalTabGroupCondition();
            portalTabGroupCondition.setBelongTabId(copyId);
            List<PortalTabGroupCondition> pageList = portalTabGroupDao.getPageList(portalTabGroupCondition);//查询复制的关联的group
            for (PortalTabGroupCondition tabGroupCondition : pageList) {
                tabGroupCondition.setBelongTabId(condition.getId());
                portalTabGroupDao.add(tabGroupCondition);
            }
        }
        //刷新缓存
        PortalLauncherTempletTabCondition linkParam = new PortalLauncherTempletTabCondition();
        linkParam.setTabId(condition.getId());
        List<PortalLauncherTempletTabList> comboboxList = portalLauncherTempletTabDao.getComboboxList(linkParam);
        for (PortalLauncherTempletTabList portalLauncherTempletTabList : comboboxList) {
            navigationService.flushNavigationListCacheByTmpId(portalLauncherTempletTabList.getBelongTempletId());
        }
        return insertCount;
    }

    public Integer delete(PortalTabCondition condition){
        //launcher校验关联
        PortalLauncherTempletTabCondition portalLauncherTempletTabCondition = new PortalLauncherTempletTabCondition();
        portalLauncherTempletTabCondition.setTabId(condition.getId());
        Integer count = this.portalLauncherTempletTabDao.getCount(portalLauncherTempletTabCondition);//launcher关联
        //推荐位关联
        PortalCustomRecomContentCondition portalCustomRecomContentCondition = new PortalCustomRecomContentCondition();
        portalCustomRecomContentCondition.setContentType(10);
        portalCustomRecomContentCondition.setContentId(condition.getId());
        Integer count1 = portalCustomRecomContentDao.getCount(portalCustomRecomContentCondition);
        if (count>0){
            return -2;
        }
        if (count1>0){
            return -3;
        }

        PortalTabGroupCondition portalTabGroupCondition = new PortalTabGroupCondition();
        portalTabGroupCondition.setBelongTabId(condition.getId());
        portalTabGroupDao.delete(portalTabGroupCondition);
        Integer delCount = portalTabDao.delete(condition);
        //刷新缓存
        PortalLauncherTempletTabCondition linkParam = new PortalLauncherTempletTabCondition();
        linkParam.setTabId(condition.getId());
        List<PortalLauncherTempletTabList> comboboxList = portalLauncherTempletTabDao.getComboboxList(linkParam);
        for (PortalLauncherTempletTabList portalLauncherTempletTabList : comboboxList) {
            navigationService.flushNavigationListCacheByTmpId(portalLauncherTempletTabList.getBelongTempletId());
        }
        return delCount;
    }
    public Integer update(PortalTabCondition condition){
        if(condition.getIsEffective()!=null && condition.getIsEffective()==0){//判断是否下线操作
            PortalLauncherTempletTabCondition portalLauncherTempletTabCondition = new PortalLauncherTempletTabCondition();
            portalLauncherTempletTabCondition.setTabId(condition.getId());
            Integer count = this.portalLauncherTempletTabDao.getCount(portalLauncherTempletTabCondition);//launcher关联
            //推荐位关联
            PortalCustomRecomContentCondition portalCustomRecomContentCondition = new PortalCustomRecomContentCondition();
            portalCustomRecomContentCondition.setContentType(10);
            portalCustomRecomContentCondition.setContentId(condition.getId());
            Integer count1 = portalCustomRecomContentDao.getCount(portalCustomRecomContentCondition);
            if (count>0){
                return -2;
            }
            if (count1>0){
                return -3;
            }
        }
        //更新launcher关联的tab
        PortalLauncherTempletTabCondition updateCondition=new PortalLauncherTempletTabCondition();
        updateCondition.setTabId(condition.getId());
        updateCondition.setTabName(condition.getTabName());
        updateCondition.setTabIcon(condition.getTabIcon());
        this.portalLauncherTempletTabDao.update(updateCondition);
        Integer updateCount = portalTabDao.update(condition);
        //刷新缓存
        PortalLauncherTempletTabCondition linkParam = new PortalLauncherTempletTabCondition();
        linkParam.setTabId(condition.getId());
        List<PortalLauncherTempletTabList> comboboxList = portalLauncherTempletTabDao.getComboboxList(linkParam);
        for (PortalLauncherTempletTabList portalLauncherTempletTabList : comboboxList) {
            navigationService.flushNavigationListCacheByTmpId(portalLauncherTempletTabList.getBelongTempletId());
        }
        return updateCount;
    }
}
