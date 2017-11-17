package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.core.util.JsonUtil;
import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalSysAppIconsList;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalSysAppIconsListCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalSysAppIconsListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedisPool;

import java.util.List;
import java.util.Random;

/**
 * Created by admin on 2017/7/10.
 */
@Service
public class PortalSysAppIconsListService {
    @Autowired
    private ShardedJedisPool shardedJedisPool;
    private static String fixKey = "userid_:";
    @Autowired
    private RedisService redisService;
    @Autowired
    private PortalSysAppIconsListDao pDao;
    public ScriptPage<PortalSysAppIconsList> getPageList(PortalSysAppIconsListCondition condition) throws Exception {
        ScriptPage<PortalSysAppIconsList> scriptPage = new ScriptPage<PortalSysAppIconsList>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<PortalSysAppIconsList> rows = this.pDao.getPageList(condition);
        int total = this.pDao.getPageCount(condition);
        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        return scriptPage;
    }
    public Integer add(PortalSysAppIconsListCondition condition) throws Exception {
        if(pDao.getCountByPackage(condition)>0){
            return -1;
        }
        Integer result = this.pDao.add(condition);
        String key = "sysAppIconsList_getList";
        redisService.del(key);
        return result;
    }
    public Integer delete(PortalSysAppIconsListCondition condition) throws Exception {
        Integer result  =this.pDao.delete(condition);
        String key = "sysAppIconsList_getList";
        redisService.del(key);
        return result;
    }
    public Integer update(PortalSysAppIconsListCondition condition) throws Exception {
        if(pDao.getCountByPackage(condition)>0){
            return -1;
        }
        Integer result = this.pDao.update(condition);
        String key = "sysAppIconsList_getList";
        redisService.del(key);
        return result;
    }
    public List<PortalSysAppIconsList> getList(PortalSysAppIconsListCondition condition) throws Exception {
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<PortalSysAppIconsList> rows = this.pDao.getList(condition);
        String key = "sysAppIconsList_getList";
        int randNum = new Random().nextInt(1800)+5400;
        redisService.setEx(key,randNum, JsonUtil.toJSONString(rows));
        return rows;
    }
}
