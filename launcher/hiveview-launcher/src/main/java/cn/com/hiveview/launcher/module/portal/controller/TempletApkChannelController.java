package cn.com.hiveview.launcher.module.portal.controller;

import cn.com.hiveview.launcher.module.portal.condition.TempletApkChannelCondition;
import cn.com.hiveview.launcher.module.portal.condition.TempletApkCondition;
import cn.com.hiveview.launcher.module.portal.service.TempletApkChannelService;
import cn.com.hiveview.launcher.module.portal.service.TempletApkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2017/8/1.
 */
@Controller
@RequestMapping("/portal/templetApkChannel")
public class TempletApkChannelController {
    @Autowired
    private TempletApkChannelService templetApkChannelService;
    @RequestMapping(value = "getChannelList")
    @ResponseBody
    public Object getPageList(TempletApkChannelCondition condition) {
        try {
            return  templetApkChannelService.getChannelList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "getChannelTypeList")
    @ResponseBody
    public Object getChannelTypeList(TempletApkChannelCondition condition) {
        try {
            return  templetApkChannelService.getChannelTypeList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
