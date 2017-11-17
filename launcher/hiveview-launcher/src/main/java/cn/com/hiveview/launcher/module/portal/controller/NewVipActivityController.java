package cn.com.hiveview.launcher.module.portal.controller;

import cn.com.hiveview.launcher.module.portal.condition.NewVipActivityCondition;
import cn.com.hiveview.launcher.module.portal.service.NewVipActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2017/10/10.
 */
@Controller
@RequestMapping("/activity")
public class NewVipActivityController {
    @Autowired
    private NewVipActivityService newVipActivityService;
    @RequestMapping(value = "getPageList")
    @ResponseBody
    public Object getPageList(NewVipActivityCondition condition) {
        try {
            return  newVipActivityService.getActivityList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
