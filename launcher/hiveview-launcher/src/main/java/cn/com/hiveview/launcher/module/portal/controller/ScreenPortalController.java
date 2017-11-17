package cn.com.hiveview.launcher.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.launcher.module.portal.condition.ScreenRecommendListCondition;
import cn.com.hiveview.launcher.module.portal.service.ScreenPortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/portal")
public class ScreenPortalController {

    @Autowired
    private ScreenPortalService screenPortalService;

    @RequestMapping(value = "list")
    public String getScreenPortalList() {
        return "portal/screenPortalList";
    }
    @RequestMapping(value = "getPageList")
    @ResponseBody
    public Object getPageList(ScreenRecommendListCondition condition) {
        try {
            condition.setRecommendType(0);
            return  screenPortalService.getScreenPortalList(condition);
        } catch (Exception ex) {
                ex.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/save")
    @ResponseBody
    public Object save(ScreenRecommendListCondition condition) {
        try {
           return  screenPortalService.insert(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ScreenRecommendListCondition condition) {
        try {
            return  screenPortalService.update(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/del")
    @ResponseBody
    public Object del(ScreenRecommendListCondition condition) {
        try {
            return  screenPortalService.del(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
