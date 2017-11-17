package cn.com.hiveview.launcher.module.logoLicenseManage.controller;

import cn.com.hiveview.launcher.module.logoLicenseManage.condition.LogoLicenseManageCondition;
import cn.com.hiveview.launcher.module.logoLicenseManage.service.LogoLicenseManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2017/7/10.
 */

@Controller
@RequestMapping("/logoManage")
public class LogoLicenseManageController {

    @RequestMapping(value = "list")
    public String getLogoLicenseManageList() {
        return "logoManage/logoLicenseManageList";
    }

    @Autowired
    private LogoLicenseManageService logoLicenseManageService;

    @RequestMapping(value = "/getPageList")
    @ResponseBody
    public Object getPageList(LogoLicenseManageCondition condition) {
        try {
            return  logoLicenseManageService.getLogoManageList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public Object save(LogoLicenseManageCondition condition) {
        try {
            return  logoLicenseManageService.insert(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(LogoLicenseManageCondition condition) {
        try {
            return  logoLicenseManageService.update(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object del(LogoLicenseManageCondition condition) {
        try {
            return  logoLicenseManageService.del(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/getLogo")
    @ResponseBody
    public Object getLogo(LogoLicenseManageCondition condition) {
        try {
            return  logoLicenseManageService.getLogo(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
