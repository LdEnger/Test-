package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.core.util.Constants;
import cn.com.hiveview.core.util.JsonUtil;
import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.NavigationVo;
import cn.com.hiveview.entity.module.portal.PortalLauncherTempletVo;
import cn.com.hiveview.entity.module.portal.PortalScreenRecommendList;
import cn.com.hiveview.entity.module.portal.PortalScreenRecommendListApiVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalScreenRecommendListCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalTempletControllerTypeCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.NavigationDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalLauncherTempletDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalScreenRecommendListDao;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedisPool;

import java.util.*;

/**
 * Created by admin on 2017/6/29.
 */
@Service
public class NavigationService {
    private static final Logger logger = LoggerFactory.getLogger(NavigationService.class.getName());
    @Autowired
    private NavigationDao navigationDao;
    @Autowired
    private ShardedJedisPool shardedJedisPool;
    @Autowired
    private PortalLauncherTempletDao launDao;
    private static String fixKey = "userid_:";
    @Autowired
    private NavigationDao pDao;
    @Autowired
    private PortalLauncherTempletInfoService portalLauncherTempletInfoService;
    @Autowired
    private RedisService redisService;


    private static Integer lockArray [] = {1,2,3,4,5,6,7,8,9,10};
    private static Integer lockTabArray [] = {1,2,3,4,5,6,7,8,9,10};


    public List<NavigationVo> getNavigationList(HashMap<String,String> condition){

        PortalTempletControllerTypeCondition controllerCondition = new PortalTempletControllerTypeCondition();
        controllerCondition.setMac(condition.get("mac"));
        controllerCondition.setSn(condition.get("sn"));
        controllerCondition.setEquipmentNo(condition.get("model"));
        controllerCondition.setRomversion(condition.get("romversion"));
        controllerCondition.setIp(condition.get("ip"));
        controllerCondition.setVersion(condition.get("version"));
        Integer templetId= 1;
        List<NavigationVo> res = null;
        try {
            PortalLauncherTempletVo vo = portalLauncherTempletInfoService.getLauncherTempletInfo(controllerCondition);//获取模板
            if (null != vo) {
                templetId = vo.getTempletId();
                logger.info("导航栏接口参数："+controllerCondition.getIp());
                logger.info("导航栏接口："+templetId);
                logger.debug("导航栏接口的模板："+templetId);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("导航栏接口报错："+e.getMessage());
            logger.debug("导航栏接口报错:"+e.getMessage());
            templetId= 1;
        }
        res=navigationDao.getNavigationList(templetId);
        return res;
    }

    public List<NavigationVo> getNavigationListCache(HashMap<String,String> condition) {
        PortalTempletControllerTypeCondition controllerCondition = new PortalTempletControllerTypeCondition();
        controllerCondition.setMac(condition.get("mac"));
        controllerCondition.setSn(condition.get("sn"));
        controllerCondition.setEquipmentNo(condition.get("model"));
        controllerCondition.setRomversion(condition.get("romversion"));
        controllerCondition.setIp(condition.get("ip"));
        controllerCondition.setVersion(condition.get("version"));
        Integer templetId = 1;
        List<NavigationVo> res = null;
        int index = 0;
        try {
            PortalLauncherTempletVo vo = portalLauncherTempletInfoService.getLauncherTempletInfo(controllerCondition);//获取模板
            if (null != vo) {
                templetId = vo.getTempletId();
                logger.info("导航栏接口参数：" + controllerCondition.getIp());
                logger.info("导航栏接口：" + templetId);
                logger.debug("导航栏接口的模板：" + templetId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("导航栏接口报错：" + e.getMessage());
            logger.debug("导航栏接口报错:" + e.getMessage());
            templetId = 1;
        }
        try {
            String navigationRedisValue = redisService.get(Constants.NAVIGATION_REDIS_KEY + templetId);
            if (null != navigationRedisValue && !navigationRedisValue.equalsIgnoreCase("")) {
                res = JSON.parseArray(navigationRedisValue, NavigationVo.class);
                return res;
            } else {
                index = Constants.PB_RANDOM.nextInt(lockArray.length);
                synchronized (lockArray[index]) {
                    String navigationRedisValueLocal = redisService.get(Constants.NAVIGATION_REDIS_KEY + templetId);
                    if (null != navigationRedisValueLocal && !navigationRedisValueLocal.equalsIgnoreCase("")) {
                        res = JSON.parseArray(navigationRedisValueLocal, NavigationVo.class);
                        return res;
                    }
                    res = navigationDao.getNavigationList(templetId);
                    String parm = JsonUtil.toJSONString(res);
                    if (res != null) {
                        redisService.setEx(Constants.NAVIGATION_REDIS_KEY + templetId, Constants.PB_RANDOM.nextInt(1800) + 5400, parm);
                    }
                }
                return res;
            }
        } catch (Exception e) {
            return getNavigationListDeFaultCache();
        }
    }

    /**
     * 获取默认模板数据
     * @return
     */
    public List<NavigationVo> getNavigationListDeFaultCache(){

        Integer templetId= 1;
        List<NavigationVo> res = null;
        int index=0;
        try {
            String navigationRedisValue = redisService.get(Constants.NAVIGATION_REDIS_KEY+templetId);
            if (null != navigationRedisValue && !navigationRedisValue.equalsIgnoreCase("")) {
                res=JSON.parseArray(navigationRedisValue,NavigationVo.class);
                return res;
            } else {
                index = Constants.PB_RANDOM.nextInt(lockArray.length);
                synchronized (lockArray[index]) {
                    String navigationRedisValueLocal = redisService.get(Constants.NAVIGATION_REDIS_KEY+templetId);
                    if (null != navigationRedisValueLocal && !navigationRedisValueLocal.equalsIgnoreCase("")) {
                        res=JSON.parseArray(navigationRedisValueLocal,NavigationVo.class);
                        return res;
                    }
                    res=navigationDao.getNavigationList(templetId);
                    String parm=JsonUtil.toJSONString(res);
                    if (res != null) {
                        redisService.setEx(Constants.NAVIGATION_REDIS_KEY+templetId,Constants.PB_RANDOM.nextInt(1800)+5400,parm );
                    }
                }
                return res;
            }
        }catch (Exception e){
            return null;
        }
    }

    public boolean flushNavigationListCacheALL(){
        try {
            return redisService.clearPrefex(Constants.NAVIGATION_REDIS_KEY);
        }catch(Exception e){
            return false;
        }
    }

    public boolean flushNavigationListCacheByTmpId(Integer tmpId){
        long index=0;
        try {
           index =  redisService.del(Constants.NAVIGATION_REDIS_KEY+tmpId);
            if(index>0){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    public HashMap<String,String> getDefaultTapPic(){
        HashMap<String,String> res =null;
        res=navigationDao.getDefaultTapPic();
        return res;
    }
    public HashMap<String,String> getDefaultTapPicCache(){
        HashMap<String,String> res =null;
        int index=0;
        try {
            String navigationRedisValue = redisService.get(Constants.NAVIGATION_TAB_KEY);
            if (null != navigationRedisValue && !navigationRedisValue.equalsIgnoreCase("")) {
                res=JSON.parseObject(navigationRedisValue,HashMap.class);
                return res;
            } else {
                index = Constants.PB_RANDOM.nextInt(lockTabArray.length);
                synchronized (lockTabArray[index]) {
                    String navigationRedisValueLocal = redisService.get(Constants.NAVIGATION_TAB_KEY);
                    if (null != navigationRedisValueLocal && !navigationRedisValueLocal.equalsIgnoreCase("")) {
                        res=JSON.parseObject(navigationRedisValueLocal,HashMap.class);
                        return res;
                    }
                    res=navigationDao.getDefaultTapPic();
                    String parm=JsonUtil.toJSONString(res);
                    if (res != null) {
                        redisService.setEx(Constants.NAVIGATION_TAB_KEY,7200,parm );
                    }
                }
                return res;
            }
        }catch (Exception e){
            return null;
        }
    }
    public boolean flushDefaultTapPicCache(){
        long index=0;
        try {
            index = redisService.del(Constants.NAVIGATION_TAB_KEY);
            if(index>0){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }
    public HashMap<String,String> getVipPic(){
        HashMap<String,String> res=new HashMap<String,String>();
        String aqyUrl=navigationDao.getVipPic("aiqy");
        String vipUrl=navigationDao.getVipPic("dymxvip");
        res.put("aqyUrl",aqyUrl);
        res.put("vipUrl",vipUrl);
        return res;
    }







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
