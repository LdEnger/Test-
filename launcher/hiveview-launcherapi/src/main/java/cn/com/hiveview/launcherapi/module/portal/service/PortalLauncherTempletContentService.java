package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalLauncherTempletContentList;
import cn.com.hiveview.entity.module.portal.PortalMacAreaList;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalLauncherTempletContentCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalMacAreaCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalLauncherTempletContentDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalMacAreaListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedisPool;

import java.util.List;

/**
 * Created by Administrator on 2017/7/27.
 */
@Service
public class PortalLauncherTempletContentService {
    @Autowired
    private ShardedJedisPool shardedJedisPool;
    private static String fixKey = "userid_:";
    @Autowired
    private PortalLauncherTempletContentDao pDao;
    public ScriptPage<PortalLauncherTempletContentList> getPageList(PortalLauncherTempletContentCondition condition) throws Exception {
        ScriptPage<PortalLauncherTempletContentList> scriptPage = new ScriptPage<PortalLauncherTempletContentList>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<PortalLauncherTempletContentList> rows = this.pDao.getPageList(condition);
        int total = this.pDao.getPageCount(condition);
        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        return scriptPage;
    }
    public Integer add(PortalLauncherTempletContentCondition condition) throws Exception {
        Integer seq=this.pDao.getMaxSeq(condition);
        if(seq==null){
            condition.setSeq(1);
        }else {
            condition.setSeq(seq+1);
        }
        return this.pDao.add(condition);
    }
    public Integer delete(PortalLauncherTempletContentCondition condition) throws Exception {
        return this.pDao.delete(condition);
    }
    public Integer update(PortalLauncherTempletContentCondition condition) throws Exception {
        return this.pDao.update(condition);
    }
    public Integer getMaxSeq(PortalLauncherTempletContentCondition condition) throws Exception {
        return this.pDao.getMaxSeq(condition);
    }
    public PortalLauncherTempletContentList getMinContent(PortalLauncherTempletContentCondition condition){
        return this.pDao.getMinContent(condition);
    }
    public PortalLauncherTempletContentList getMaxContent(PortalLauncherTempletContentCondition condition){
        return this.pDao.getMaxContent(condition);
    }

    public Integer getCount(PortalLauncherTempletContentCondition condition){
        return this.pDao.getCount(condition);
    }
}
