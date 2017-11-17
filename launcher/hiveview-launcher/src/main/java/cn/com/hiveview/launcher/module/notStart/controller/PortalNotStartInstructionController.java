package cn.com.hiveview.launcher.module.notStart.controller;

import cn.com.hiveview.launcher.module.notStart.condition.PortalNotStartInstructionListCondition;
import cn.com.hiveview.launcher.module.notStart.service.PortalNotStartInstructionService;
import cn.com.hiveview.launcher.module.start.condition.StartInstructionCondition;
import cn.com.hiveview.launcher.module.start.service.StartInstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2017/10/13.
 */
@Controller
@RequestMapping("/PortalNotStartInstruction")
public class PortalNotStartInstructionController {
    @Autowired
    private PortalNotStartInstructionService portalNotStartInstructionService;

    @RequestMapping(value = "list")
    public String getNotStart() {
        return "notStart/notStart";
    }
    @RequestMapping(value = "/getPage")
    @ResponseBody
    public Object getPage(PortalNotStartInstructionListCondition condition) {
        try {
            return  portalNotStartInstructionService.getPage(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/save")
    @ResponseBody
    public Object save(PortalNotStartInstructionListCondition condition) {
        try {
            return  portalNotStartInstructionService.save(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(PortalNotStartInstructionListCondition condition) {
        try {
            return  portalNotStartInstructionService.update(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/updateEffice")
    @ResponseBody
    public Object updateEffice(PortalNotStartInstructionListCondition condition) {
        try {
            return  portalNotStartInstructionService.updateEffice(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(PortalNotStartInstructionListCondition condition) {
        try {
            return  portalNotStartInstructionService.delete(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/getPageList")
    @ResponseBody
    public Object getPageList(PortalNotStartInstructionListCondition condition) {
        try {
            return  portalNotStartInstructionService.getPageList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
