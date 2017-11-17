package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalScreentRecommendContent;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalScreentRecommendContentCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalScreentRecommendContentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedisPool;

import java.util.List;

/**
 * Created by admin on 2017/7/10.
 */
@Service
public class PortalScreentRecommendContentService {
    @Autowired
    private ShardedJedisPool shardedJedisPool;
    private static String fixKey = "userid_:";
    @Autowired
    private PortalScreentRecommendContentDao pDao;
    public ScriptPage<PortalScreentRecommendContent> getPageList(PortalScreentRecommendContentCondition condition) throws Exception {
        ScriptPage<PortalScreentRecommendContent> scriptPage = new ScriptPage<PortalScreentRecommendContent>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<PortalScreentRecommendContent> rows = this.pDao.getPageList(condition);
        int total = this.pDao.getPageCount(condition);
        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        return scriptPage;
    }
    public Integer add(PortalScreentRecommendContentCondition condition) throws Exception {
        Integer seq=this.pDao.getMinSeq(condition);
        if(seq==null){
            condition.setSeq(100);
        }else {
            condition.setSeq(seq-1);
        }
        //推荐位内容新增要去重
        List<PortalScreentRecommendContent> rows = this.pDao.getHasList(condition);
        if(rows !=null && rows.size()>0){
            return -2;//存在
        }
        return this.pDao.add(condition);
    }
    public Integer delete(PortalScreentRecommendContentCondition condition) throws Exception {
        return this.pDao.delete(condition);
    }
    public Integer update(PortalScreentRecommendContentCondition condition) throws Exception {
        return this.pDao.update(condition);
    }
    public Integer getMaxSeq(PortalScreentRecommendContentCondition condition) throws Exception {
        return this.pDao.getMaxSeq(condition);
    }
    public Integer getMinSeq(PortalScreentRecommendContentCondition condition) throws Exception {
        return this.pDao.getMinSeq(condition);
    }
    public PortalScreentRecommendContent getMaxContent(PortalScreentRecommendContentCondition condition) throws Exception {
        return this.pDao.getMaxContent(condition);
    }
    public PortalScreentRecommendContent getMinContent(PortalScreentRecommendContentCondition condition) throws Exception {
        return this.pDao.getMinContent(condition);
    }

}
