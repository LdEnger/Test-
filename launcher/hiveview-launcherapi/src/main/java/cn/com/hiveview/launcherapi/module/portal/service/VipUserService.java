package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.core.util.Constants;
import cn.com.hiveview.core.util.HttpUtils;
import cn.com.hiveview.core.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedisPool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static cn.com.hiveview.core.util.Constants.USER_DAMAI_VIP_INFO;

/**
 * Created by admin on 2017/7/17.
 */
@Service
public class VipUserService {

    @Autowired
    private NavigationService navigationService;

    public Map getDomyVipUserInfo(String userId,Integer templateId){
        Map resultMap = new HashMap();
        HashMap<String,String> vipUrlMap=navigationService.getVipPic();
        try {
            String info = Constants.USER_DAMAI_VIP_INFO;
            String  userInfo =  HttpUtils.doGet(info+userId+"-"+1+".json");
            Map<String, Object> map=null;
            Map<String, Object> data=null;
            Map<String, Object> result=null;
            if(null!=userInfo&&!userInfo.equalsIgnoreCase(""))
                map  = JsonUtil.parseMap(userInfo);
            if(null!=map)
                data= (HashMap<String, Object>) map.get("data");
            if(null!=data)
                result = (Map<String, Object>) data.get("result");
            if(result == null ){
                return null;
            }
            else {
                if(Integer.parseInt(result.get("isVip")+"")==1){
                    String time = (String) result.get("expiredDate");
                    resultMap.put("isVip",1);
                    resultMap.put("expiredDate",time.substring(0,10));
                    resultMap.put("picUrl",vipUrlMap.get("vipUrl"));
                    resultMap.put("cp","大麦尊享会员" );
                    return resultMap;
                }
                else{
                    return null;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map getQiyiVipUserInfo(String userId,String version){
        Map resultMap = new HashMap();
        HashMap<String,String> vipUrlMap=navigationService.getVipPic();
        try {
            String  userInfo =  HttpUtils.doGet(Constants.USER_QIYI_VIP_INFO+userId+"-4.json");
            Map<String, Object> map  = null;
            Map<String, Object> data = null;
            Map<String, Object> result = null;
            if(null!=userInfo &&!userInfo.equalsIgnoreCase(""))
                map  = JsonUtil.parseMap(userInfo);
            if(null!=map)
                data = (HashMap<String, Object>) map.get("data");
            if(null!=data)
                result = (Map<String, Object>) data.get("result");
            if(result == null ){
               return null;
            }
            else {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Long long1 = Long.valueOf((String)result.get("deadline"));
                String format = simpleDateFormat.format(new Date(long1 * 1000L));
                String today =simpleDateFormat.format(new Date());
                int parm=format.compareTo(today);
                if(parm>=0) { //只有结束时间 大于当天才返回数据 否则 返回空
                    resultMap.put("expiredDate", format);
                    resultMap.put("isVip", 1);
                    resultMap.put("picUrl", vipUrlMap.get("aqyUrl"));
                    resultMap.put("cp", "奇异果会员");
                    return resultMap;
                }else{
                    return null;
                }
            }
        } catch (Exception e) {
           return null;
        }
    }
}
