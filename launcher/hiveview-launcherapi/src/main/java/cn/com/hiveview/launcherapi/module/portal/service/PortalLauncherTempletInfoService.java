package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.portal.PortalLauncherTempletVo;
import cn.com.hiveview.entity.module.portal.PortalMacAreaList;
import cn.com.hiveview.entity.module.portal.PortalRecommendListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalLauncherTempletAreaCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalLauncherTempletContentCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalMacAreaCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalTempletControllerTypeCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalLauncherTempletInfoDao;
import cn.com.hiveview.launcherapi.module.portal.util.IpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by user on 2017/7/17.
 */
@Service
public class PortalLauncherTempletInfoService {
    static Log logger = LogFactory.getLog(PortalLauncherTempletInfoService.class);
    @Autowired
    private ShardedJedisPool shardedJedisPool;
    private static String fixKey = "userid_:";
    @Autowired
    private PortalLauncherTempletInfoDao portalLauncherTempletInfoDao;
    @Autowired
    private PortalMacAreaListService portalMacAreaListService;
    @Autowired
    private RedisService redisService;

    public PortalLauncherTempletVo getLauncherTempletInfo(PortalTempletControllerTypeCondition condition){
        String mac = condition.getMac();
        String sn = condition.getSn();
        String equipmentNo = condition.getEquipmentNo();
        String ip = condition.getIp();
        String romversion = condition.getRomversion();
        String version = condition.getVersion();
        try{
            PortalLauncherTempletAreaCondition search = new PortalLauncherTempletAreaCondition();
            PortalLauncherTempletVo list = null;
            String ccode = "00";

            String keyList = "R_K_LAUNCHER_CATCH_TEMPLET_INFO"+"_"+mac+"_"+sn+"_"+equipmentNo+"_"+ip+"_"+romversion+"_"+version;
            if(redisService.exists(keyList)){
                String val = redisService.get(keyList);
                list = JSONObject.parseObject(val, PortalLauncherTempletVo.class);
            }else{

                if (list == null) {

                    // 先从百事通mac库里判断
                    String mKey = "R_K_LAUNCHER_CATCH_AREA_CODE_MACSN_"+ mac + "_" + sn;
                    ccode = redisService.get(mKey);
                    if (StringUtils.isEmpty(ccode)) {
                        PortalMacAreaCondition ma = new PortalMacAreaCondition();
                        ma.setMac(mac);
                        ma.setSn(sn);
                        PortalMacAreaList mav = portalMacAreaListService.get(ma);
                        if (mav != null && !StringUtils.isEmpty(mav.getAreaCode())) {
                            ccode = mav.getAreaCode();
                        }
                    }
                    if (!StringUtils.isEmpty(ccode) && ccode.length()<20){
                        redisService.setEx(mKey, 21600 + new Random().nextInt(3600), ccode);
                        search.setAreaCode(ccode);
                        search.setType(2);
                        list = portalLauncherTempletInfoDao.getLauncherTempletInfo(search);
                    }
                }
                if(list == null){
                    logger.info("第一次打印ccode;"+ccode);
                    String citycodeKey = "R_K_LAUNCHER_CATCH_AREA_CODE_IP_"+ ip+"_"+mac+"_"+sn;
                    ccode = redisService.get(citycodeKey);
                    logger.info("ccode = redisService.get(citycodeKey);"+ccode);
                    if (StringUtils.isEmpty(ccode)) {
                        Map<String, String> cityMap = IpUtil.cityCodeByIp(ip);
                        logger.info("ip地址是什么"+ip);
                        logger.debug("debug===ip地址是什么"+ip);
                        logger.info("cityMap:"+cityMap);
                        if (cityMap != null) {
                            ccode = cityMap.get("ccode");
                        }
                    }
                    logger.info("最后===ccode"+ccode);
                    if (!StringUtils.isEmpty(ccode) && ccode.length() < 20) {
                        redisService.setEx(citycodeKey,getRandomTime(), ccode);
                    }
                    logger.info("根据硬件型号+IP地址查找====IP："+ip+"硬件型号："+equipmentNo);
                    //根据硬件型号+IP地址查找
                    if(!StringUtils.isEmpty(equipmentNo) && equipmentNo.length()<20 && !StringUtils.isEmpty(ip) && ip.length()<20){
                        search.setAreaCode(equipmentNo);
                        search.setType(5);
                        logger.info("根据硬件型号+IP地址查找====ccode："+ccode+"硬件型号："+equipmentNo);
                        list = portalLauncherTempletInfoDao.getAreaIdByTwo(equipmentNo, ccode,5);
                    }

                }

                //根据ROM按本区分
                if(list == null){
                    if (!StringUtils.isEmpty(romversion) && romversion.length()<20){
                        search.setAreaCode(romversion);
                        search.setType(4);
                        list = portalLauncherTempletInfoDao.getLauncherTempletInfo(search);
                    }
                }


                // 根据盒子型号查找
                if (list == null) {
                    search.setAreaCode(equipmentNo);
                    search.setType(3);
                    if(!StringUtils.isEmpty(equipmentNo) && equipmentNo.length()>10){
                        search.setAreaCode("00");
                    }
                    ccode = "00";
                    // 根据盒子型号查找
                    list = portalLauncherTempletInfoDao.getLauncherTempletInfo(search);
                }

                // 根据IP查找
                if (list == null) {
                    logger.info("最后一种逻辑,IP地址逻辑====ip："+ip);
                    String citycodeKey = "R_K_LAUNCHER_CATCH_AREA_CODE_IP_"+ ip+"_"+mac+"_"+sn;
                    ccode = redisService.get(citycodeKey);
                    if (StringUtils.isEmpty(ccode)) {
                        Map<String, String> cityMap = IpUtil.cityCodeByIp(ip);
                        if (cityMap != null) {
                            ccode = cityMap.get("ccode");
                        }
                    }
                    logger.info("2>>>>>>ccode："+ccode);
                    if (!StringUtils.isEmpty(ccode) && ccode.length() < 20) {
                        redisService.setEx(citycodeKey,getRandomTime(), ccode);
                    }
                    if (ccode != null && ccode.length() > 20) {
                        ccode = "00";
                        search.setType(null);
                    }
                    search.setAreaCode(ccode);
                    search.setType(1);
                    logger.info("3>>>>>>ccode："+ccode);
                    list = portalLauncherTempletInfoDao.getLauncherTempletInfo(search);
                }

                if (list == null) {
                    search.setAreaCode("00");
                    search.setType(null);
                    list = portalLauncherTempletInfoDao.getLauncherTempletInfo(search);
                }

                if(list!=null){
                    redisService.setEx(keyList,getRandomTime(), JSON.toJSONString(list));
                }
            }

            return list;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public List<PortalRecommendListVo> getPortalRecommendList(PortalLauncherTempletContentCondition condition){
        return portalLauncherTempletInfoDao.getPortalRecommendList(condition);
    }

    public int getRandomTime(){
        return 5400 + new Random().nextInt(1800);
    }
}
