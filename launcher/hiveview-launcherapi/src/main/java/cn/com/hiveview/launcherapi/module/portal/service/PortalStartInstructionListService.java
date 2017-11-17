package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.core.util.JsonUtil;
import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalLauncherTempletVo;
import cn.com.hiveview.entity.module.portal.PortalStartInstructionList;
import cn.com.hiveview.launcherapi.module.portal.condition.*;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalLauncherTempletDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalStartInstructionListDao;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by admin on 2017/7/10.
 */
@Service
public class PortalStartInstructionListService {
    @Autowired
    private ShardedJedisPool shardedJedisPool;
    private static String fixKey = "userid_:";
    @Autowired
    private PortalStartInstructionListDao pDao;
    @Autowired
    private PortalLauncherTempletDao launDao;
    @Autowired
    private PortalLauncherTempletInfoService portalLauncherTempletInfoService;
    @Autowired
    private RedisService redisService;
    private static Integer lockArray [] = {1,2,3,4,5,6,7,8,9,10};
    public ScriptPage<PortalStartInstructionList> getPageList(PortalStartInstructionCondition condition) throws Exception {
        ScriptPage<PortalStartInstructionList> scriptPage = new ScriptPage<PortalStartInstructionList>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<PortalStartInstructionList> rows = this.pDao.getPageList(condition);
        int total = this.pDao.getPageCount(condition);
        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        return scriptPage;
    }
    public List<PortalStartInstructionList> getComboboxList(PortalStartInstructionCondition condition) throws Exception {
        List<PortalStartInstructionList> rows = this.pDao.getComboboxList(condition);
        return rows;
    }
    public Integer add(PortalStartInstructionCondition condition) throws Exception {
        if(pDao.getCountByString(condition)>0){
            return -1;
        }
        Integer result = this.pDao.add(condition);
        String key = "startInstructionList_getList_"+condition.getId();
        redisService.del(key);
        return result;
    }
    public Integer delete(PortalStartInstructionCondition condition) throws Exception {
        PortalLauncherTempletCondition portalLauncherTempletCondition=new PortalLauncherTempletCondition();
        portalLauncherTempletCondition.setAutoId(condition.getId());
        if(launDao.getCount(portalLauncherTempletCondition)>0){
            return -1;
        }
        PortalBeanCurdEditListCondition portalBeanCurdEditListCondition=new PortalBeanCurdEditListCondition();
        portalBeanCurdEditListCondition.setEntranceAppId(condition.getId());
        if(pDao.getCountByCurdEdit(portalBeanCurdEditListCondition)>0){
            return -2;
        }
        Integer result = this.pDao.delete(condition);
        String key = "startInstructionList_getList_"+condition.getId();
        redisService.del(key);
        return result;
    }
    public Integer update(PortalStartInstructionCondition condition) throws Exception {
        //判断是否要下线，若下线，在判断是否有关联
        if(condition.getIsEffective()!=null){
            if(condition.getIsEffective()==0){
                //判断是否被launcher关联，若关联，不可下线
                PortalLauncherTempletCondition portalLauncherTempletCondition=new PortalLauncherTempletCondition();
                portalLauncherTempletCondition.setAutoId(condition.getId());
                if(launDao.getCount(portalLauncherTempletCondition)>0){
                    return -3;
                }
                //判断是否被豆腐块关联，若关联，不可下线
                PortalBeanCurdEditListCondition portalBeanCurdEditListCondition=new PortalBeanCurdEditListCondition();
                portalBeanCurdEditListCondition.setEntranceAppId(condition.getId());
                if(pDao.getCountByCurdEdit(portalBeanCurdEditListCondition)>0){
                    return -2;
                }
            }
        }
        if(condition.getCharacterString()!=null){
            if(pDao.getCountByString(condition)>0){
                return -1;
            }
        }
        Integer result  = this.pDao.update(condition);
        String key = "startInstructionList_getList_"+condition.getId();
        redisService.del(key);
        return result;
    }
    public  Map<String, Object> getList(PortalStartInstructionCondition condition) throws Exception {
        // 7、开机自启动数据
        Map<String, Object> startMap = new HashMap<String, Object>();
        condition.setCharacterString("com.hiveview.cloudscreen.vipvideo");
        PortalStartInstructionList result = this.pDao.getList(condition);
        if(result == null){
            startMap.put("packageName","com.hiveview.cloudscreen.vipvideo");
            startMap.put("priority", 1);
            startMap.put("instructionType",2);
        }else{
            startMap.put("packageName", result.getCharacterString());
            startMap.put("priority", 1);
            startMap.put("instructionType", result.getInstructionType());
        }
        return startMap;
    }
    public  Map<String, Object> getListByTempletInfo(PortalTempletControllerTypeCondition templet) throws Exception {
        // 7、开机自启动数据
        PortalLauncherTempletVo vo =portalLauncherTempletInfoService.getLauncherTempletInfo(templet);
        PortalStartInstructionCondition condition = new PortalStartInstructionCondition();
        condition.setPage(templet.getPage());
        condition.setRows(templet.getRows());
        condition.setPageIndex(templet.getPageIndex());
        condition.setPageSize(templet.getPageSize());
        Map<String, Object> startMap = new HashMap<String, Object>();
        if(vo ==null){
            return startMap;
        }
        condition.setId(vo.getAutoId());
        String key = "startInstructionList_getList_"+vo.getAutoId();
        String val = redisService.get(key);
        if(val != null){
            startMap  = JSON.parseObject(val, Map.class);
        }else {
            Integer index = new Random().nextInt(lockArray.length);
            synchronized (lockArray[index]) {
                if (redisService.exists(key)) {
                    val = redisService.get(key);
                    startMap = JSON.parseObject(val, Map.class);
                } else {
                    PortalStartInstructionList result = this.pDao.getList(condition);
                    if(result == null){
                        //不给默认值
//                        startMap.put("packageName","com.hiveview.cloudscreen.vipvideo");
//                        startMap.put("priority", 1);
//                        startMap.put("instructionType",2);
                    }else{
                        startMap.put("packageName", result.getCharacterString());
                        startMap.put("priority", 1);
                        startMap.put("instructionType", result.getInstructionType());
                    }
                    int randNum = new Random().nextInt(1800)+5400;
                    redisService.setEx(key,randNum, JsonUtil.toJSONString(startMap));
                }
            }
        }
        return startMap;
    }
}
