package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.LogoLicenseManageListVo;
import cn.com.hiveview.entity.module.portal.PortalLauncherTempletList;
import cn.com.hiveview.entity.module.portal.PortalLauncherTempletVo;
import cn.com.hiveview.launcherapi.module.portal.condition.LogoLicenseManageListCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalLauncherTempletCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalTempletControllerTypeCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.LogoLicenseManageListDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalLauncherTempletDao;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;
import redis.clients.jedis.ShardedJedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by user on 2017/7/5.
 */
@Service
public class LogoLicenseManageListService {

    @Autowired
    private ShardedJedisPool shardedJedisPool;
    private static String fixKey = "userid_:";
    @Autowired
    private LogoLicenseManageListDao logoLicenseManageListDao;
    @Autowired
    private PortalLauncherTempletDao launDao;
    @Autowired
    private PortalLauncherTempletInfoService portalLauncherTempletInfoService;
    @Autowired
    private RedisService redisService;
    private static Integer logoLicenseArray [] = {1,2,3,4,5,6,7,8,9,10};

    public ScriptPage<LogoLicenseManageListVo> getList(LogoLicenseManageListCondition condition) throws Exception {
        ScriptPage<LogoLicenseManageListVo> scriptPage = new ScriptPage<LogoLicenseManageListVo>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<LogoLicenseManageListVo> rows = this.logoLicenseManageListDao.getList(condition);
        int total = this.logoLicenseManageListDao.getCount(condition);
        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        return scriptPage;
    }

    public Integer save(LogoLicenseManageListCondition condition) throws Exception {
        return this.logoLicenseManageListDao.save(condition);
    }
    public Integer delete(LogoLicenseManageListCondition condition) throws Exception {
        PortalLauncherTempletCondition portalLauncherTempletCondition=new PortalLauncherTempletCondition();
        portalLauncherTempletCondition.setLogoId(condition.getLogoId());
        if(launDao.getCount(portalLauncherTempletCondition)>0){
            return -1;
        }
        return this.logoLicenseManageListDao.delete(condition);
    }
    public Integer update(LogoLicenseManageListCondition condition) throws Exception {
        int result=this.logoLicenseManageListDao.update(condition);
        if(result==1){
            //当是要下线时，把launcher关联此条数据的logoId全部变为null
            if(condition.getIsOnline()==0){
                PortalLauncherTempletCondition lCondition=new PortalLauncherTempletCondition();
                lCondition.setLogoId(condition.getLogoId());
                List<PortalLauncherTempletList> rows=launDao.getComboboxList(lCondition);
                for(int i=0;i<rows.size();i++){
                    rows.get(i).setLogoId(null);
                    PortalLauncherTempletCondition templet=new PortalLauncherTempletCondition();
                    templet.setLogoId(rows.get(i).getLogoId());
                    templet.setIsHide(rows.get(i).getIsHide());
                    templet.setTempletName(rows.get(i).getTempletName());
                    templet.setCityNames(rows.get(i).getCityNames());
                    templet.setAutoId(rows.get(i).getAutoId());
                    templet.setId(rows.get(i).getId());
                    templet.setType(rows.get(i).getType());
                    launDao.updateLogoId(templet);
                }
            }
            return result;
        }

        return result;
    }

    public LogoLicenseManageListVo getLogo(LogoLicenseManageListCondition condition) throws Exception{
        return this.logoLicenseManageListDao.getLogo(condition);
    }

   /* public static void main(String[] args) {
        PortalLauncherTempletVo result = new PortalLauncherTempletVo();
        result.setTempletId(15);
        result.setCityNames("00");
        System.out.println(JSON.toJSONString(result));
        String test = "{\"cityNames\":\"00\",\"templetId\":15}";
        PortalLauncherTempletVo result1 = JSON.parseObject(test,PortalLauncherTempletVo.class);
        System.out.println(result1.getCityNames());
    }*/

    public LogoLicenseManageListVo getLogoLicenseList(PortalTempletControllerTypeCondition condition){

        LogoLicenseManageListVo result = new LogoLicenseManageListVo();
        try{
            PortalLauncherTempletVo vo =portalLauncherTempletInfoService.getLauncherTempletInfo(condition);
            if(vo ==null){
                return result;
            }
            String keyList = "R_K_LAUNCHER_CATCH_LOGO_LICENSE_LIST"+"_"+vo.getLogoId();
            if(redisService.exists(keyList)){
                String val = redisService.get(keyList);
                result = JSONObject.parseObject(val, LogoLicenseManageListVo.class);
            }else{
                Integer index = new Random().nextInt(logoLicenseArray.length);
                synchronized (logoLicenseArray[index]) {
                    if (redisService.exists(keyList)) {
                        String val = redisService.get(keyList);
                        result = JSONObject.parseObject(val, LogoLicenseManageListVo.class);
                    } else {
                        LogoLicenseManageListCondition logoCondition = new LogoLicenseManageListCondition();
                        logoCondition.setLogoId(vo.getLogoId());
                        logoCondition.setPageIndex(condition.getPageIndex());
                        logoCondition.setPageSize(condition.getPageSize());
                        result = logoLicenseManageListDao.getLogo(logoCondition);
                        if (result != null) {
                            redisService.setEx(keyList, getRandomTime(), JSON.toJSONString(result));
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    public List<LogoLicenseManageListVo> getLogoList(LogoLicenseManageListCondition condition){
        return logoLicenseManageListDao.getLogoList(condition);
    }


    public int getRandomTime(){
        return 5400 + new Random().nextInt(1800);
    }




}
