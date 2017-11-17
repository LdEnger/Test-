package cn.com.hiveview.launcher.module.Area.controller;

import cn.com.hiveview.launcher.module.Area.condition.PortalAreaAdminirationCondition;
import cn.com.hiveview.launcher.module.Area.condition.PortalAreaContentCondition;
import cn.com.hiveview.launcher.module.Area.service.PortalAreaAdminirationService;
import cn.com.hiveview.launcher.module.Area.service.PortalAreaContenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2017/10/8.
 */
@Controller
@RequestMapping("/PortalAreaContenController")
public class PortalAreaContenController {

    @Autowired
    private PortalAreaContenService portalAreaContenService;

    @ResponseBody
    @RequestMapping("/getPage")
    public Object getPage(PortalAreaAdminirationCondition condition){
        try {
            return this.portalAreaContenService.getPage(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/save")
    public Object save(PortalAreaAdminirationCondition condition){
        try {
            return this.portalAreaContenService.save(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(PortalAreaAdminirationCondition condition){
        try {
            return this.portalAreaContenService.delete(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/update")
    public Object update(PortalAreaAdminirationCondition condition){
        try {
            return  this.portalAreaContenService.update(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    @ResponseBody
    @RequestMapping("/getAreaMinSeq")
    public Object getAreaMinSeq(PortalAreaContentCondition  condition){
        try {
            return  this.portalAreaContenService.update(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
    @ResponseBody
    @RequestMapping("/getAreaMaxSeq")
    public Object getAreaMaxSeq(PortalAreaContentCondition  condition){
        try {
            return  this.portalAreaContenService.update(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

}
