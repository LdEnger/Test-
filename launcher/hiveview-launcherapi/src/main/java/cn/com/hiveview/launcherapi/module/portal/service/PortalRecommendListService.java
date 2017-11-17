package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.portal.PortalLauncherTempletVo;
import cn.com.hiveview.entity.module.portal.PortalRecommendListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalLauncherTempletContentCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalTempletControllerTypeCondition;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by user on 2017/7/21.
 * 获取推荐位列表
 */
@Service
public class PortalRecommendListService {
    @Autowired
    private PortalLauncherTempletInfoService portalLauncherTempletInfoService;
    @Autowired
    private RedisService redisService;
    private static Integer portalRecomArray [] = {1,2,3,4,5,6,7,8,9,10};

    public List<PortalRecommendListVo> getPortalRecommendList(PortalTempletControllerTypeCondition condition){

        List<PortalRecommendListVo> list = new ArrayList<PortalRecommendListVo>();

        try{
            PortalLauncherTempletVo vo =portalLauncherTempletInfoService.getLauncherTempletInfo(condition);
            String keyList = "R_K_LAUNCHER_CATCH_PORTAL_RECOMMEND_LIST"+"_"+vo.getTempletId();
            if(redisService.exists(keyList)){
                String val = redisService.get(keyList);
                list = JSONObject.parseObject(val, List.class);
            }else{

                Integer index = new Random().nextInt(portalRecomArray.length);
                synchronized (portalRecomArray[index]) {
                    if (redisService.exists(keyList)){
                        String val = redisService.get(keyList);
                        list  = JSON.parseObject(val, List.class);
                    }else{

                        PortalLauncherTempletContentCondition recomCondition = new PortalLauncherTempletContentCondition();
                        recomCondition.setTempletId(vo.getTempletId());
                        list = portalLauncherTempletInfoService.getPortalRecommendList(recomCondition);
                        PortalRecommendListVo portalRecommendListVo = new PortalRecommendListVo();
                        if(vo.getIsHide() == 1){
                            portalRecommendListVo.setRecommendId(-1);
                            portalRecommendListVo.setRecommendType(3);
                            portalRecommendListVo.setRecommendName("智能推荐");
                            list.add(portalRecommendListVo);
                        }
                        if(list!=null && list.size() > 0){
                            redisService.setEx(keyList,getRandomTime(), JSON.toJSONString(list));
                        }
                    }
                }
            }
       }catch (Exception e){
            e.printStackTrace();
        }

        return list;

    }

    public int getRandomTime(){
        return 5400 + new Random().nextInt(1800);
    }

}
