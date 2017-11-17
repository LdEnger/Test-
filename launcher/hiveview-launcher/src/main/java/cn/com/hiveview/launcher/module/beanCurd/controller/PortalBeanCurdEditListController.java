package cn.com.hiveview.launcher.module.beanCurd.controller;

import cn.com.hiveview.launcher.module.beanCurd.condition.PotalBeanCurdEditListCondition;
import cn.com.hiveview.launcher.module.beanCurd.condition.PotalBeanCurdListCondition;
import cn.com.hiveview.launcher.module.beanCurd.service.PortalBeanCurdEditListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2017/7/25.
 */
@Controller
@RequestMapping("/portalBeanCurdEditListController")
public class PortalBeanCurdEditListController {

    @RequestMapping(value =  "list")
    public  String getPortalBeanCurdList(){
        return "beanCurd/beanCurdEdit";
    }

    @Autowired
    private PortalBeanCurdEditListService portalBeanCurdEditListService;

    @RequestMapping("/getPageList")
    @ResponseBody
    public  Object getPageList(PotalBeanCurdEditListCondition potalBeanCurdEditListCondition){
        try {
            return  this.portalBeanCurdEditListService.getPageList(potalBeanCurdEditListCondition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
    @RequestMapping("/getList")
    @ResponseBody
    public Object getList(PotalBeanCurdEditListCondition potalBeanCurdEditListCondition){
        try {
            return  this.portalBeanCurdEditListService.getList(potalBeanCurdEditListCondition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }


    @RequestMapping("/delete")
    @ResponseBody
    public Object  delete(PotalBeanCurdEditListCondition potalBeanCurdEditListCondition){
        try {
            return  this.portalBeanCurdEditListService.delete(potalBeanCurdEditListCondition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/update")
    @ResponseBody
    public  Object update(PotalBeanCurdEditListCondition potalBeanCurdEditListCondition){
        try {
            return  this.portalBeanCurdEditListService.update(potalBeanCurdEditListCondition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    @RequestMapping("/save")
    @ResponseBody
    public Object save(PotalBeanCurdEditListCondition  potalBeanCurdEditListCondition){
        try {
            return  this.portalBeanCurdEditListService.save(potalBeanCurdEditListCondition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
    @RequestMapping("/getEditList")
    @ResponseBody
    public Object getEditList(PotalBeanCurdEditListCondition potalBeanCurdEditListCondition){
        try {
            return  this.portalBeanCurdEditListService.getEditList(potalBeanCurdEditListCondition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    @RequestMapping("/getEdit")
    @ResponseBody
    public Object getEdit(PotalBeanCurdEditListCondition potalBeanCurdEditListCondition){
        try {
            return  this.portalBeanCurdEditListService.getEdit(potalBeanCurdEditListCondition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    @RequestMapping("/getEditCount")
    @ResponseBody
    public Object getEditCount(PotalBeanCurdEditListCondition potalBeanCurdEditListCondition){
        try {
            return  this.portalBeanCurdEditListService.getEditCount(potalBeanCurdEditListCondition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
