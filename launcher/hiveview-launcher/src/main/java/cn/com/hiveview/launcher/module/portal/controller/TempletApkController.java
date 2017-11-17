package cn.com.hiveview.launcher.module.portal.controller;

import cn.com.hiveview.launcher.module.portal.condition.TempletApkCondition;
import cn.com.hiveview.launcher.module.portal.service.TempletApkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2017/8/1.
 */
@Controller
@RequestMapping("/portal/templetApk")
public class TempletApkController {
    @Autowired
    private TempletApkService templetApkService;
    @RequestMapping(value = "getList")
    @ResponseBody
    public Object getPageList(TempletApkCondition condition) {
        try {
            return  templetApkService.getList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
