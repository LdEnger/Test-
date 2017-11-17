package cn.com.hiveview.launcher.module.launcher.controller;

import cn.com.hiveview.launcher.module.acrossVertical.condition.AcrossVerticalListCondition;
import cn.com.hiveview.launcher.module.beanCurd.condition.PotalBeanCurdListCondition;
import cn.com.hiveview.launcher.module.launcher.condition.TempletCondition;
import cn.com.hiveview.launcher.module.launcher.service.TempletService;
import cn.com.hiveview.launcher.module.logoLicenseManage.condition.LogoLicenseManageCondition;
import cn.com.hiveview.launcher.module.start.condition.StartInstructionCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by chimeilong on 2017/6/29.
 */
@Controller
@RequestMapping("/templet")
public class TempletController {

    @Autowired
    private TempletService templetService;
    @RequestMapping(value = "list")
    public String getAreatempList() {
        return "launcher/launcherList";
    }

    @RequestMapping(value = "getPageList")
    @ResponseBody
    public Object getPageList(TempletCondition condition) {
        try {
            return  templetService.getPageList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "getStartList")
    @ResponseBody
    public Object getStartList(StartInstructionCondition condition) {
        try {
            return  templetService.getStartList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "getLogoList")
    @ResponseBody
    public Object getLogoList(LogoLicenseManageCondition condition) {
        try {
            return  templetService.getLogoList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "getImageList")
    @ResponseBody
    public Object getImageList(AcrossVerticalListCondition condition) {
        try {
            return  templetService.getImageList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "getBlockList")
    @ResponseBody
    public Object getBlockList(PotalBeanCurdListCondition condition) {
        try {
            return  templetService.getBlockList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public Object save(TempletCondition condition) {
        try {
            return templetService.insert(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(TempletCondition condition) {
        try {
            return  templetService.update(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/del")
    @ResponseBody
    public Object del(TempletCondition condition) {
        try {
            return  templetService.del(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/addCopy")
    @ResponseBody
    public Object addCopy(TempletCondition condition) {
        try {
            return templetService.addCopy(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
