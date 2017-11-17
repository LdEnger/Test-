package cn.com.hiveview.launcher.module.beanCurd.controller;

import cn.com.hiveview.launcher.module.beanCurd.condition.PotalBeanCurdListCondition;
import cn.com.hiveview.launcher.module.beanCurd.service.PortalBeanCurdListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2017/7/24.
 */
@Controller
@RequestMapping(value = "/portalBeanCurdList")
public class PortalBeanCurdListController {

    @RequestMapping(value =  "list")
    public  String getPortalBeanCurdList(){
        return "beanCurd/beanCurd";
    }
    @Autowired
    private PortalBeanCurdListService portalBeanCurdListService;

    @RequestMapping(value = "/getPageList")
    @ResponseBody
    public  Object getPageList(PotalBeanCurdListCondition potalBeanCurdListCondition){
        try {
            return  this.portalBeanCurdListService.getPageList(potalBeanCurdListCondition);
        }catch (Exception e){
            e.printStackTrace();

        }
        return  null;
    }

    @RequestMapping("/updateIs")
    @ResponseBody
    public  Object updateIs(PotalBeanCurdListCondition potalBeanCurdListCondition){
        try {
            return  this.portalBeanCurdListService.updateIs(potalBeanCurdListCondition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(PotalBeanCurdListCondition potalBeanCurdListCondition){
        try {
            return  this.portalBeanCurdListService.delete(potalBeanCurdListCondition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/getCurdList")
    @ResponseBody
    public Object getCurdList(PotalBeanCurdListCondition potalBeanCurdListCondition){
        try {
            return  this.portalBeanCurdListService.getCurdList(potalBeanCurdListCondition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
    @ResponseBody
    @RequestMapping("/save")
    public  Object save(PotalBeanCurdListCondition potalBeanCurdListCondition){
        try {
            return this.portalBeanCurdListService.save(potalBeanCurdListCondition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    @ResponseBody
    @RequestMapping("/update")
    public  Object update(PotalBeanCurdListCondition potalBeanCurdListCondition){
        try {
            return this.portalBeanCurdListService.update(potalBeanCurdListCondition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
}

