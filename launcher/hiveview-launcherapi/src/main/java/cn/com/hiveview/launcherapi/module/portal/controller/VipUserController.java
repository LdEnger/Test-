package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.launcherapi.module.portal.service.VipUserService;
import cn.com.hiveview.launcherapi.module.portal.util.DBHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/7/18.
 */
@Controller
@RequestMapping("/vipUser")
public class VipUserController {
    @Autowired
    private VipUserService vipUserService;
    @Autowired
    private DBHelper holder;

    @RequestMapping(value = {"/getVipList/{mac}/{sn}/{model}/{rowVersion}/{version}/{userId}"})
    @ResponseBody
    public Object getVipList(@PathVariable String mac, @PathVariable String sn, @PathVariable String model,  @PathVariable String rowVersion ,  @PathVariable String version,@PathVariable String userId) {
        Map result = new HashMap();
        List<Map> vipList = new ArrayList<Map>();
        try {
            holder.setDBType(DBHelper.DB_TYPE_R);
            Map domyVipMap = vipUserService.getDomyVipUserInfo(userId,1);
            if(null!=domyVipMap)
            vipList.add(domyVipMap);
            Map qiyiVipMap = vipUserService.getQiyiVipUserInfo(userId,version);
            if(null!=qiyiVipMap)
            vipList.add(qiyiVipMap);
            result.put("vip", vipList);
            holder.clearDBType();
            return JsonMessage.create(0, "", result);
        } catch (Exception ex) {
            ex.printStackTrace();
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }
}