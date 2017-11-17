package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalMacAreaList;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalMacAreaCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalMacAreaListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/14.
 */
@Service
public class PortalMacAreaListService {
    @Autowired
    private ShardedJedisPool shardedJedisPool;
    private static String fixKey = "userid_:";
    @Autowired
    private PortalMacAreaListDao pDao;
    public ScriptPage<PortalMacAreaList> getPageList(PortalMacAreaCondition condition) throws Exception {
        ScriptPage<PortalMacAreaList> scriptPage = new ScriptPage<PortalMacAreaList>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<PortalMacAreaList> rows = this.pDao.getPageList(condition);
        int total = this.pDao.getPageCount(condition);
        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        return scriptPage;
    }
    public Integer add(PortalMacAreaCondition condition) throws Exception {
        String mac=condition.getMac();
        condition.setMac(mac.replaceAll(":", "").replaceAll("-", "").toUpperCase());
        condition.setSn(condition.getSn().toUpperCase());
        if(pDao.getCountByMacSn(condition)>0){
            return -1;
        }
        return this.pDao.add(condition);
    }
    public Integer delete(PortalMacAreaCondition condition) throws Exception {
        //判断如果传的参数为空时，删除失败
        if(condition.getId()==null && condition.getAreaCode()==null){
            return 0;
        }
        return this.pDao.delete(condition);
    }
    public Integer update(PortalMacAreaCondition condition) throws Exception {
        String mac=condition.getMac();
        condition.setMac(mac.replaceAll(":", "").replaceAll("-", "").toUpperCase());
        condition.setSn(condition.getSn().toUpperCase());
        if(pDao.getCountByMacSn(condition)>0){
            return -1;
        }
        return this.pDao.update(condition);
    }

    public PortalMacAreaList get(PortalMacAreaCondition condition) throws Exception {
        return this.pDao.get(condition);
    }
    public List<PortalMacAreaList> getList(PortalMacAreaCondition condition) throws Exception {
        List<PortalMacAreaList> rows = this.pDao.getPageList(condition);
        return rows;
    }
}
