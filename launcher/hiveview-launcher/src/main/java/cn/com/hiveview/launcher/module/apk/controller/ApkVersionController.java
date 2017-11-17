package cn.com.hiveview.launcher.module.apk.controller;

import cn.com.hiveview.launcher.module.apk.condition.ApkVersionCondition;
import cn.com.hiveview.launcher.module.apk.service.ApkVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/7/19.
 */
@Controller
@RequestMapping("/apkVersion")
public class ApkVersionController {

    @Autowired
    ApkVersionService apkVersionService;
    @RequestMapping(value = "getPageList")
    @ResponseBody
    public Object getPageList(ApkVersionCondition condition) {
        try {
            System.out.println(condition);
            return  apkVersionService.getPageList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public Object save(ApkVersionCondition condition) {
        try {
            return apkVersionService.insert(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ApkVersionCondition condition) {
        try {
            return  apkVersionService.update(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/del")
    @ResponseBody
    public Object del(ApkVersionCondition condition) {
        try {
            return  apkVersionService.del(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
