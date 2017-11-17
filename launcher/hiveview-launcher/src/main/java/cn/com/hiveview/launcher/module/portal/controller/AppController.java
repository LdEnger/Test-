package cn.com.hiveview.launcher.module.portal.controller;

import cn.com.hiveview.launcher.module.portal.condition.ActivityFreeVipCondition;
import cn.com.hiveview.launcher.module.portal.condition.AppCondition;
import cn.com.hiveview.launcher.module.portal.service.ActivityFreeVipService;
import cn.com.hiveview.launcher.module.portal.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2017/7/31.
 */
@Controller
@RequestMapping("/app")
public class AppController {
    @Autowired
    private AppService appService;
    @RequestMapping(value = "getPageList")
    @ResponseBody
    public Object getPageList(AppCondition condition) {
        try {
            return  appService.getPageList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
