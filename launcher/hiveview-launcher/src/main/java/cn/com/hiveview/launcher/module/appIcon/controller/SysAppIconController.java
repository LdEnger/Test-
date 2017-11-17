package cn.com.hiveview.launcher.module.appIcon.controller;

import cn.com.hiveview.launcher.module.appIcon.condition.SysAppIconListCondition;
import cn.com.hiveview.launcher.module.portal.condition.ScreenRecommendListCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.com.hiveview.launcher.module.appIcon.service.SysAppIconService;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/7/10.
 */
@Controller
@RequestMapping("/appIcon")
public class SysAppIconController {

    @Autowired
    private SysAppIconService sysAppIconService;

    @RequestMapping(value = "list")
    public String getsysAppIconList() {
        return "appIcon/sysAppIconList";
    }

    @RequestMapping(value = "getPageList")
    @ResponseBody
    public Object getPageList(SysAppIconListCondition condition) {
        try {

            return  sysAppIconService.getSysAppIconList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/save")
    @ResponseBody
    public Object save(SysAppIconListCondition condition) {
        try {
            return  sysAppIconService.insert(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(SysAppIconListCondition condition) {
        try {
            return  sysAppIconService.update(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/del")
    @ResponseBody
    public Object del(SysAppIconListCondition condition) {
        try {
            return  sysAppIconService.del(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
