package cn.com.hiveview.launcher.module.macsn.controller;

import cn.com.hiveview.launcher.module.macsn.condition.AreaGroupCondition;
import cn.com.hiveview.launcher.module.macsn.service.AreaGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/7/14.
 */
@Controller
@RequestMapping("/areaGroup")
public class AreaGroupController {

    @Autowired
    private AreaGroupService areaGroupService;

    @RequestMapping(value = "getPageList")
    @ResponseBody
    public Object getPageList(AreaGroupCondition condition) {
        try {
            return  areaGroupService.getAreaGroupList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "getList")
    @ResponseBody
    public Object getList(AreaGroupCondition condition) {
        try {
            return  areaGroupService.getAreaList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "getAreaGroupByCode")
    @ResponseBody
    public Object getAreaGroupByCode(AreaGroupCondition condition) {
        try {
            return  areaGroupService.getAreaGroupByCode(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public Object save(AreaGroupCondition condition) {
        try {
            return  areaGroupService.insert(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(AreaGroupCondition condition) {
        try {
            return  areaGroupService.update(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/del")
    @ResponseBody
    public Object del(AreaGroupCondition condition) {
        try {
            return  areaGroupService.del(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
