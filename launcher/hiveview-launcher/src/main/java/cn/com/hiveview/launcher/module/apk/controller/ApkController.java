package cn.com.hiveview.launcher.module.apk.controller;

import cn.com.hiveview.launcher.module.apk.condition.ApkCondition;
import cn.com.hiveview.launcher.module.apk.service.ApkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/7/19.
 */
@Controller
@RequestMapping("/apk")
public class ApkController {
    @Autowired
    private ApkService apkService;

    @RequestMapping(value = "list")
    public String getMacAreaList() {
        return "apk/apkList";
    }

    @RequestMapping(value = "getPageList")
    @ResponseBody
    public Object getPageList(ApkCondition condition) {
        try {
            System.out.println(condition);
            return  apkService.getPageList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "getComboboxList")
    @ResponseBody
    public Object getComboboxList(ApkCondition condition) {
        try {
            return  apkService.getComboboxList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public Object save(ApkCondition condition) {
        try {
            return apkService.insert(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ApkCondition condition) {
        try {
            return  apkService.update(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/del")
    @ResponseBody
    public Object del(ApkCondition condition) {
        try {
            return  apkService.del(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
