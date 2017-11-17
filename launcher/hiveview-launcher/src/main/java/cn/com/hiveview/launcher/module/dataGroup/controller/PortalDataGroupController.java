package cn.com.hiveview.launcher.module.dataGroup.controller;

import cn.com.hiveview.launcher.module.dataGroup.conditon.AppTagListCondition;
import cn.com.hiveview.launcher.module.dataGroup.conditon.PoralDataGroupListCondition;
import cn.com.hiveview.launcher.module.dataGroup.service.PortalDataGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2017/10/9.
 */
@Controller
@RequestMapping("/PortalDataGroupController")
public class PortalDataGroupController {

    @RequestMapping(value =  "list")
    public  String getDataGroupList(){return  "dataGroup/dataGroup";}

    @Autowired
    private PortalDataGroupService portalDataGroupService;

    @RequestMapping(value = "/getPage")
    @ResponseBody
    public Object getPage(PoralDataGroupListCondition condition){
        try{
            return portalDataGroupService.getPage(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(PoralDataGroupListCondition condition){
        try{
            return portalDataGroupService.delete(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
    @RequestMapping(value = "/save")
    @ResponseBody
    public  Object save(PoralDataGroupListCondition condition){
        try{
            return  portalDataGroupService.save(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public  Object update(PoralDataGroupListCondition condition){
        try {
            return portalDataGroupService.update(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    @RequestMapping(value = "/updateEffective")
    @ResponseBody
    public  Object updateEffective(PoralDataGroupListCondition condition){
        try {
            return portalDataGroupService.updateEffective(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    @RequestMapping("/getAppPage")
    @ResponseBody
    public Object getAppPage(AppTagListCondition condition){
        try {
            return portalDataGroupService.getAppPage(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/getGroupVo")
    @ResponseBody
    public Object getGroupVo(PoralDataGroupListCondition condition){
        try {
            return portalDataGroupService.getGroupVo(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



}
