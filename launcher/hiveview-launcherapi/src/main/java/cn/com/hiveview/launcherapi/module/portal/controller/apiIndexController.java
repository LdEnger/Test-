package cn.com.hiveview.launcherapi.module.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by user on 2017/7/13.
 */
@Controller
@RequestMapping("/Launcher/api")
public class apiIndexController {

    @RequestMapping(value = "/cmsIndex")
    public String getCmsApiIndex() {
        return "cmsApiIndex/Index";
    }

    @RequestMapping(value = "/clientIndex")
    public String getClientApiIndex() {
        return "clientApiIndex/Index";
    }

}
