package cn.com.hiveview.launcher.module.portal.controller;

import cn.com.hiveview.launcher.module.portal.condition.ActivityCondition;
import cn.com.hiveview.launcher.module.portal.condition.ActivityFreeVipCondition;
import cn.com.hiveview.launcher.module.portal.service.ActivityFreeVipService;
import cn.com.hiveview.launcher.module.portal.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2017/10/20.
 */
@Controller
@RequestMapping("/portal/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @RequestMapping(value = "getPageList")
    @ResponseBody
    public Object getPageList(ActivityCondition condition) {
        try {
            return  activityService.getPageList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
