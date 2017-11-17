package cn.com.hiveview.launcher.module.portal.controller;

import cn.com.hiveview.launcher.module.portal.condition.NewVipActivityCondition;
import cn.com.hiveview.launcher.module.portal.condition.YouGouSpecialsCondition;
import cn.com.hiveview.launcher.module.portal.service.NewVipActivityService;
import cn.com.hiveview.launcher.module.portal.service.YouGouSpecialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2017/10/14.
 */
@Controller
@RequestMapping("/portal/youGouSpecials")
public class YouGouSpecialsController {
    @Autowired
    private YouGouSpecialsService youGouSpecialsService;
    @RequestMapping(value = "getPageList")
    @ResponseBody
    public Object getPageList(YouGouSpecialsCondition condition) {
        try {
            return  youGouSpecialsService.getYouGouSpecialsList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
