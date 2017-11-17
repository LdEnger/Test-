package cn.com.hiveview.launcher.module.beanCurd.controller;

import cn.com.hiveview.launcher.module.beanCurd.condition.PortalBeanCurdMappingListCondition;
import cn.com.hiveview.launcher.module.beanCurd.service.PortalBeanCurdMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2017/8/2.
 */
@RequestMapping("/portalBeanCurdMappingController")
@Controller
public class PortalBeanCurdMappingController {
    @Autowired
    private PortalBeanCurdMappingService portalBeanCurdMappingService;

    @RequestMapping("/getPageList")
    @ResponseBody
    public Object getPageList(PortalBeanCurdMappingListCondition portalBeanCurdMappingListCondition) {
        try {
            return this.portalBeanCurdMappingService.getPageList(portalBeanCurdMappingListCondition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/save")
    @ResponseBody
    public Object save(PortalBeanCurdMappingListCondition portalBeanCurdMappingListCondition) {
        try {
            return this.portalBeanCurdMappingService.save(portalBeanCurdMappingListCondition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Object update(PortalBeanCurdMappingListCondition portalBeanCurdMappingListCondition) {
        try {
            return this.portalBeanCurdMappingService.update(portalBeanCurdMappingListCondition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(PortalBeanCurdMappingListCondition portalBeanCurdMappingListCondition) {
        try {
            return this.portalBeanCurdMappingService.delete(portalBeanCurdMappingListCondition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/getMinMapping")
    @ResponseBody
    public Object getMinMapping(PortalBeanCurdMappingListCondition portalBeanCurdMappingListCondition){
        try {
            return this.portalBeanCurdMappingService.getMinMapping(portalBeanCurdMappingListCondition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/getMaxMapping")
    @ResponseBody
    public Object getMaxMapping(PortalBeanCurdMappingListCondition portalBeanCurdMappingListCondition){
        try {
            return this.portalBeanCurdMappingService.getMaxMapping(portalBeanCurdMappingListCondition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/getMinSeq")
    @ResponseBody
    public Object getMinSeq(PortalBeanCurdMappingListCondition portalBeanCurdMappingListCondition){
        try {
            return this.portalBeanCurdMappingService.getMinSeq(portalBeanCurdMappingListCondition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping("/getMappingCount")
    @ResponseBody
    public Object getMappingCount(PortalBeanCurdMappingListCondition portalBeanCurdMappingListCondition){
        try {
            return this.portalBeanCurdMappingService.getMappingCount(portalBeanCurdMappingListCondition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}