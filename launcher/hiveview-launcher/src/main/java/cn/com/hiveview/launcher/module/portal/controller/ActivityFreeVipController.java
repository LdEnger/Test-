package cn.com.hiveview.launcher.module.portal.controller;

import cn.com.hiveview.launcher.module.portal.condition.ActivityFreeVipCondition;
import cn.com.hiveview.launcher.module.portal.service.ActivityFreeVipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2017/7/31.
 */
@Controller
@RequestMapping("/activityFreeVip")
public class ActivityFreeVipController {
    @Autowired
    private ActivityFreeVipService activityFreeVipService;
    @RequestMapping(value = "getPageList")
    @ResponseBody
    public Object getPageList(ActivityFreeVipCondition condition) {
        try {
            return  activityFreeVipService.getActivityFreeVipList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "getComboboxList")
    @ResponseBody
    public Object getComboboxList() {
        try {
            return  activityFreeVipService.getActivityFreeVipList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
