package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.core.util.JsonUtil;
import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalLauncherTempletVo;
import cn.com.hiveview.entity.module.portal.PortalScreenRecommendList;
import cn.com.hiveview.entity.module.portal.PortalScreenRecommendListApiVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalLauncherTempletCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalScreenRecommendListCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalTempletControllerTypeCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalLauncherTempletDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalScreenRecommendListDao;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by admin on 2017/6/29.
 */
@Service
public class PortalScreenRecommendListService {
    @Autowired
    private ShardedJedisPool shardedJedisPool;
    @Autowired
    private PortalLauncherTempletDao launDao;
    private static String fixKey = "userid_:";
    @Autowired
    private PortalScreenRecommendListDao pDao;
    @Autowired
    private PortalLauncherTempletInfoService portalLauncherTempletInfoService;
    @Autowired
    private RedisService redisService;
    private static Integer lockArray [] = {1,2,3,4,5,6,7,8,9,10};
    public ScriptPage<PortalScreenRecommendListApiVo> getList(PortalScreenRecommendListCondition condition) throws Exception {
        ScriptPage<PortalScreenRecommendListApiVo> scriptPage = new ScriptPage<PortalScreenRecommendListApiVo>();
        if(condition.getRecommendType() == null || condition.getRecommendType() == 0){
            List<PortalScreenRecommendListApiVo> rows = this.pDao.getList(condition);
            int total = this.pDao.getCount(condition);
            scriptPage.setRows(rows);
            scriptPage.setTotal(total);
            scriptPage.setPageIndex(condition.getPage());
            scriptPage.setPageSize(condition.getRows());
        }else{
            List<HashMap<String,ArrayList<PortalScreenRecommendListApiVo>>> rows = new  ArrayList<HashMap<String,ArrayList<PortalScreenRecommendListApiVo>>>();
            condition.setImageType(1);//横图
            List<PortalScreenRecommendListApiVo> htList = this.pDao.getList(condition);
            condition.setImageType(2);//竖图
            List<PortalScreenRecommendListApiVo> stList = this.pDao.getList(condition);
            HashMap result = new HashMap();
            result.put("htList",htList);
            result.put("stList",stList);
            rows.add(result);
            int total = this.pDao.getCount(condition);
            scriptPage.setRows(rows);
            scriptPage.setTotal(total);
            scriptPage.setPageIndex(condition.getPage());
            scriptPage.setPageSize(condition.getRows());
        }
        return scriptPage;
    }
    public ScriptPage<PortalScreenRecommendListApiVo> getListByTempletInfo(PortalTempletControllerTypeCondition templet) throws Exception {
        PortalLauncherTempletVo vo =portalLauncherTempletInfoService.getLauncherTempletInfo(templet);
        PortalScreenRecommendListCondition condition = new PortalScreenRecommendListCondition();
        condition.setTempletId(vo.getTempletId());
        condition.setRecommendType(templet.getType());
        condition.setPage(templet.getPage());
        condition.setRows(templet.getRows());
        condition.setPageIndex(templet.getPageIndex());
        condition.setPageSize(templet.getPageSize());
        condition.setIsEffective(1);
        ScriptPage<PortalScreenRecommendListApiVo> scriptPage = new ScriptPage<PortalScreenRecommendListApiVo>();
        String key = "screenRecommendList_getPortalList_"+vo.getTempletId()+"_"+templet.getType()+"_"+templet.getPage()+"_"+templet.getRows();
        String val = redisService.get(key);
        if(val != null){
            scriptPage  = JSON.parseObject(val, ScriptPage.class);
        }else{
            Integer index = new Random().nextInt(lockArray.length);
            synchronized (lockArray[index]) {
                if (redisService.exists(key)){
                    val = redisService.get(key);
                    scriptPage  = JSON.parseObject(val, ScriptPage.class);
                }else{
                    if(condition.getRecommendType() == null || condition.getRecommendType() == 0){
                        condition.setPageSize(100);
                        condition.setRecommendId(vo.getBigImageId());
                        List<PortalScreenRecommendListApiVo> rows = this.pDao.getList(condition);
                        int total = this.pDao.getCount(condition);
                        scriptPage.setRows(rows);
                        scriptPage.setTotal(total);
                        scriptPage.setPageIndex(condition.getPage());
                        scriptPage.setPageSize(condition.getRows());
                    }else{
                        condition.setRecommendId(vo.getSmallImageId());
                        List<HashMap<String,ArrayList<PortalScreenRecommendListApiVo>>> rows = new  ArrayList<HashMap<String,ArrayList<PortalScreenRecommendListApiVo>>>();
                        condition.setImageType(1);//横图
                        List<PortalScreenRecommendListApiVo> htList = this.pDao.getList(condition);
                        condition.setImageType(2);//竖图
                        List<PortalScreenRecommendListApiVo> stList = this.pDao.getList(condition);
                        HashMap result = new HashMap();
                        result.put("htList",htList);
                        result.put("stList",stList);
                        rows.add(result);
                        int total = this.pDao.getCount(condition);
                        scriptPage.setRows(rows);
                        scriptPage.setTotal(total);
                        scriptPage.setPageIndex(condition.getPage());
                        scriptPage.setPageSize(condition.getRows());
                    }
                    int randNum = new Random().nextInt(1800)+5400;
                    redisService.setEx(key,randNum, JsonUtil.toJSONString(scriptPage));
                }
            }
        }
        return scriptPage;
    }

    public ScriptPage<PortalScreenRecommendList> getPageList(PortalScreenRecommendListCondition condition) throws Exception {
        ScriptPage<PortalScreenRecommendList> scriptPage = new ScriptPage<PortalScreenRecommendList>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<PortalScreenRecommendList> rows = this.pDao.getPageList(condition);
        int total = this.pDao.getPageCount(condition);
        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        return scriptPage;
    }
    public Integer add(PortalScreenRecommendListCondition condition) throws Exception {
       return this.pDao.add(condition);
    }
    public Integer delete(PortalScreenRecommendListCondition condition) throws Exception {
        return this.pDao.delete(condition);
    }
    public Integer update(PortalScreenRecommendListCondition condition) throws Exception {
        /*if(condition.getIsEffective()==null){
            return this.pDao.update(condition);
        }
        //==-1表示进行删除,==0表示下线,判断是否被关联
        if(condition.getIsEffective()<1){
            PortalLauncherTempletCondition portalLauncherTempletCondition=new PortalLauncherTempletCondition();
            if(condition.getRecommendType()==1){
                portalLauncherTempletCondition.setSmallImageId(condition.getRecommendId());
            }else if(condition.getRecommendType()==0){
                portalLauncherTempletCondition.setBigImageId(condition.getRecommendId());
            }
            if(launDao.getCount(portalLauncherTempletCondition)>0){
                return -1;
            }else {
                return this.pDao.update(condition);
            }
        }*/
        return this.pDao.update(condition);
    }

    public List<PortalScreenRecommendList> getComboboxList(PortalScreenRecommendListCondition condition) throws Exception {
        List<PortalScreenRecommendList> rows = this.pDao.getComboboxList(condition);
        return rows;
    }
}
