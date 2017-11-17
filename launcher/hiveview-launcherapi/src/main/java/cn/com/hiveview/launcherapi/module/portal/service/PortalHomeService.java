package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.entity.module.portal.LauncherHomeApiVo;
import cn.com.hiveview.entity.module.portal.LogoLicenseManageListVo;
import cn.com.hiveview.entity.module.portal.PortalLauncherTempletVo;
import cn.com.hiveview.launcherapi.module.portal.condition.*;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalBeanCurdDao;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PortalHomeService {


    @Autowired
    private PortalScreenRecommendListService  portalService;
    @Autowired
    private VipUserService vipUserService;
    @Autowired
    private PortalStartInstructionListService portalStartInstructionListService;
    @Autowired
    private PortalLauncherTempletInfoService portalLauncherTempletInfoService;
    @Autowired
    private LogoLicenseManageListService logoLicenseManageListService;
    @Autowired
    private PortalBeanCurdDao portalBeanCurdDao;
    @Autowired
    private RedisService redisService;
    private static Integer portalHomeArray [] = {1,2,3,4,5,6,7,8,9,10};

    /**
     * 获取launcher首页汇总数据
     * 1、logo
     * 2、大麦VIP状态
     * 3、爱奇艺VIP状态
     * 4、首屏大图数据
     * 5、横竖图数据
     * 6、豆腐块数据
     * 7、开机自启动数据
     * @param condition
     * @return
     */
    public Object getPortalList(PortalHomeCondition condition) throws Exception {

        Map result = new HashMap();
        try{
            PortalTempletControllerTypeCondition controllerCondition = new PortalTempletControllerTypeCondition();
            controllerCondition.setMac(condition.getMac());
            controllerCondition.setSn(condition.getSn());
            controllerCondition.setEquipmentNo(condition.getModel());
            controllerCondition.setRomversion(condition.getRomversion());
            controllerCondition.setIp(condition.getIp());
            controllerCondition.setVersion(condition.getVersion());
            PortalLauncherTempletVo vo =portalLauncherTempletInfoService.getLauncherTempletInfo(controllerCondition);//获取模板

            System.out.println(vo.getTempletId());

            String keyList = "R_K_LAUNCHER_CATCH_PORTAL_HOME"+"_"+ vo.getLogoId()+"_"+condition.getUserId()+"_"+ condition.getTempletId() +"_"+ condition.getVersion()
                    +"_"+vo.getBigImageId() +"_"+ vo.getSmallImageId()+"_"+condition.getPage()+"_"+condition.getRows()+"_"+ vo.getBlockId() +"_"+ vo.getAutoId();
            if(redisService.exists(keyList)){
                String val = redisService.get(keyList);
                result = JSONObject.parseObject(val, Map.class);
            }else{
                Integer index = new Random().nextInt(portalHomeArray.length);
                synchronized (portalHomeArray[index]) {
                    if (redisService.exists(keyList)){
                        String val = redisService.get(keyList);
                        result  = JSON.parseObject(val, Map.class);
                    }else{

                        //1.logo
                        LogoLicenseManageListCondition logoCondition = new LogoLicenseManageListCondition();
                        logoCondition.setLogoId(vo.getLogoId());
                        LogoLicenseManageListVo logoResult = logoLicenseManageListService.getLogo(logoCondition);
                        result.put("logo", logoResult);

                        // 2、大麦VIP状
                        //Map<String, String> domyVipMap = new HashMap<String, String>();//变动态
                        Map<String, String> domyVipMap = vipUserService.getDomyVipUserInfo(condition.getUserId(),condition.getTempletId());
                        domyVipMap.put("cp","大麦尊享会员" );
                        List<Map<String, String>> vipList = new ArrayList<Map<String, String>>();
                        vipList.add(domyVipMap);
                        // 3、爱奇艺VIP状态
                        Map<String, String> qiyiVipMap = vipUserService.getQiyiVipUserInfo(condition.getUserId(),condition.getVersion());
                        qiyiVipMap.put("cp","奇异果会员");
                        vipList.add(qiyiVipMap);
                        result.put("vip", vipList);

                        // 4、首屏大图数据
                        PortalScreenRecommendListCondition bigPicCondition = new PortalScreenRecommendListCondition();
                        bigPicCondition.setRecommendId(vo.getBigImageId());
                        bigPicCondition.setRecommendType(0);
                        result.put("bigPic", portalService.getList(bigPicCondition) );

                        // 5、横竖图数据
                        PortalScreenRecommendListCondition portalListCondition = new PortalScreenRecommendListCondition();
                        portalListCondition.setRecommendId(vo.getSmallImageId());
                        portalListCondition.setRecommendType(1);
                        portalListCondition.setPage(condition.getPage());
                        portalListCondition.setRows(condition.getRows());
                        portalListCondition.setPageIndex(condition.getPage());
                        portalListCondition.setPageSize(condition.getRows());
                        result.put("portalList", portalService.getList(portalListCondition));


                        // 6、豆腐块数据
                        List<LauncherHomeApiVo> launcherHome = portalBeanCurdDao.getLauncherHome(vo.getBlockId());
                        result.put("launcherHome", launcherHome);

                        // 7、开机自启动数据
                        PortalStartInstructionCondition startInstructionCondition = new PortalStartInstructionCondition();
                        startInstructionCondition.setId(vo.getAutoId());
                        result.put("start", portalStartInstructionListService.getList(startInstructionCondition));

                        if(result!=null){
                            redisService.setEx(keyList,getRandomTime(),JSON.toJSONString(result));
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;

    }

    public int getRandomTime(){
        return 5400 + new Random().nextInt(1800);
    }
}
