package cn.com.hiveview.launcher.module.beanCurd.controller;

import cn.com.hiveview.launcher.module.beanCurd.condition.PortalBeanCurdImgListCondition;
import cn.com.hiveview.launcher.module.beanCurd.service.PortalBeanCurdImgListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2017/7/26.
 */
@Controller
@RequestMapping("/portalBeanCurdImgListController")
public class PortalBeanCurdImgListController {

    @Autowired
    private PortalBeanCurdImgListService portalBeanCurdImgListService;

    @RequestMapping("/getPageList")
    @ResponseBody
    public Object getPageList(PortalBeanCurdImgListCondition portalBeanCurdImgListCondition){
        try {
            return  this.portalBeanCurdImgListService.getPageList(portalBeanCurdImgListCondition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Object update(PortalBeanCurdImgListCondition portalBeanCurdImgListCondition){
        try {
            return this.portalBeanCurdImgListService.update(portalBeanCurdImgListCondition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(PortalBeanCurdImgListCondition  portalBeanCurdImgListCondition){
        try {
            return this.portalBeanCurdImgListService.delete(portalBeanCurdImgListCondition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/getImgList")
    @ResponseBody
    public  Object getImgList(PortalBeanCurdImgListCondition portalBeanCurdImgListCondition){
        try {
            return this.portalBeanCurdImgListService.getImgList(portalBeanCurdImgListCondition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
    @RequestMapping("/save")
    @ResponseBody
    public  Object save(PortalBeanCurdImgListCondition portalBeanCurdImgListCondition){
        try {
            return  this.portalBeanCurdImgListService.save(portalBeanCurdImgListCondition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
}
