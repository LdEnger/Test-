package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.portal.LauncherHomeApiVo;
import cn.com.hiveview.entity.module.portal.PortalLauncherTempletVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalTempletControllerTypeCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalBeanCurdDao;
import cn.com.hiveview.launcherapi.module.portal.mapper.PortalBeanCurdMapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by user on 2017/7/18.
 */
@Service
public class PortalBeanCurdService {

    @Autowired
    private PortalLauncherTempletInfoService portalLauncherTempletInfoService;
    @Autowired
    private PortalBeanCurdDao portalBeanCurdDao;
    @Autowired
    private RedisService redisService;
    private static Integer launcherHomeArray [] = {1,2,3,4,5,6,7,8,9,10};

    /*获取豆腐块信息接口*/
    public Object launcherHome(PortalTempletControllerTypeCondition condition){

        List<LauncherHomeApiVo> launcherHome = new ArrayList<LauncherHomeApiVo>();

        try{
            PortalLauncherTempletVo vo =portalLauncherTempletInfoService.getLauncherTempletInfo(condition);
            String keyList = "R_K_LAUNCHER_CATCH_LAUNCHER_HOME"+"_"+vo.getBlockId();
            if(redisService.exists(keyList)){
                String val = redisService.get(keyList);
                launcherHome = JSONObject.parseObject(val, List.class);
            }else{
                Integer index = new Random().nextInt(launcherHomeArray.length);
                synchronized (launcherHomeArray[index]) {
                    if (redisService.exists(keyList)){
                        String val = redisService.get(keyList);
                        launcherHome  = JSON.parseObject(val, List.class);
                    }else{
                        if(vo.getBlockId()!=null){
                            launcherHome = portalBeanCurdDao.getLauncherHome(vo.getBlockId());
                        }
                        if(launcherHome!=null && launcherHome.size() > 0){
                            redisService.setEx(keyList,getRandomTime(),JSON.toJSONString(launcherHome));
                        }
                    }
                }


            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return launcherHome;

    }

    public int getRandomTime(){
        return 5400 + new Random().nextInt(1800);
    }
}
