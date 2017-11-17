package cn.com.hiveview.launcher.module.Area.controller;

import cn.com.hiveview.launcher.module.Area.condition.PortalAreaAdminirationCondition;
import cn.com.hiveview.launcher.module.Area.service.PortalAreaAdminirationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2017/10/6.
 */
@Controller
@RequestMapping("/areaController")
public class PortalAreaAdminirationController {

    @RequestMapping("list")
    public String getPortalAreaList(){return "area/areaList";}

    @Autowired
    private PortalAreaAdminirationService portalAreaAdminirationService;

    @ResponseBody
    @RequestMapping("/getPage")
    public Object getPage(PortalAreaAdminirationCondition condition){
        try {
            return this.portalAreaAdminirationService.getPage(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/save")
    public Object save(PortalAreaAdminirationCondition condition){
        try {
            return this.portalAreaAdminirationService.save(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(PortalAreaAdminirationCondition condition){
        try {
            return this.portalAreaAdminirationService.delete(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/update")
    public Object update(PortalAreaAdminirationCondition condition){
        try {
            return  this.portalAreaAdminirationService.update(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

}
