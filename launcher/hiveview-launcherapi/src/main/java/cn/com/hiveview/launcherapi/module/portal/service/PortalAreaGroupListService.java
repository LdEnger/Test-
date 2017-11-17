package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalAreaGroupList;
import cn.com.hiveview.entity.module.portal.PortalMacAreaList;
import cn.com.hiveview.launcherapi.module.portal.condition.EntranceAreaCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalAreaGroupCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalMacAreaCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.EntranceAreaDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalAreaGroupListDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalMacAreaListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedisPool;

import java.util.List;

/**
 * Created by Administrator on 2017/7/14.
 */
@Service
public class PortalAreaGroupListService {
    @Autowired
    private ShardedJedisPool shardedJedisPool;
    private static String fixKey = "userid_:";
    @Autowired
    private PortalAreaGroupListDao pDao;
    @Autowired
    private PortalMacAreaListDao macDao;
    @Autowired
    private EntranceAreaDao aDao;

    public ScriptPage<PortalAreaGroupList> getPageList(PortalAreaGroupCondition condition) throws Exception {
        ScriptPage<PortalAreaGroupList> scriptPage = new ScriptPage<PortalAreaGroupList>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<PortalAreaGroupList> rows = this.pDao.getPageList(condition);
        int total = this.pDao.getPageCount(condition);
        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        return scriptPage;
    }
    public List<PortalAreaGroupList> getList(PortalAreaGroupCondition condition) throws Exception {
        List<PortalAreaGroupList> rows = this.pDao.getList(condition);
        return rows;
    }
    public PortalAreaGroupList getAreaGroupByCode(PortalAreaGroupCondition condition) throws Exception {
        return this.pDao.getAreaGroupByCode(condition);
    }
    public Integer add(PortalAreaGroupCondition condition) throws Exception {
        //添加时判断AreaCode是否存在
        if(pDao.getCountByAreaCode(condition)>0){
            return -1;
        }
        //添加时判断AreaName是否存在
        if(pDao.getCountByAreaName(condition)>0){
            return -2;
        }
        return this.pDao.add(condition);
    }
    public Integer delete(PortalAreaGroupCondition condition) throws Exception {
        //删除之前判断分组是否被launcher区域配置关联
        EntranceAreaCondition areaCondition=new EntranceAreaCondition();
        areaCondition.setControllerType(2);
        areaCondition.setCityId(condition.getAreaCode());
        if(aDao.ifCount(areaCondition)>0){
            return -1;
        }
        //删除之前先删除分组下的macsn
        PortalMacAreaCondition mac=new PortalMacAreaCondition();
        mac.setAreaCode(condition.getAreaCode());
        int i= macDao.getCountByCode(mac);
        if(i>0){
            if(macDao.delete(mac)>0){
                return this.pDao.delete(condition);
            }
            return 0;
        }
        return this.pDao.delete(condition);

    }
    public Integer update(PortalAreaGroupCondition condition) throws Exception {

        //添加时判断AreaName是否存在
        if(pDao.getCountByAreaName(condition)>0){
            return -2;
        }
        PortalMacAreaCondition mac=new PortalMacAreaCondition();
        mac.setAreaCode(condition.getAreaCode());
        int i= macDao.getCountByCode(mac);
        if(i>0){
            if( pDao.updateMacAreaName(condition)>0){
                return this.pDao.update(condition);
            }
            return 0;
        }
        return this.pDao.update(condition);
    }
}
