package cn.com.hiveview.launcher.module.launcher.controller;

import cn.com.hiveview.launcher.module.launcher.condition.EntranceAreaCondition;
import cn.com.hiveview.launcher.module.launcher.service.EntranceAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2017/8/2.
 */
@Controller
@RequestMapping("/entranceArea")
public class EntranceAreaController {
    @Autowired
    private EntranceAreaService entranceAreaService;

    @RequestMapping(value = "getDiffCityList")
    @ResponseBody
    public Object getDiffCityList(EntranceAreaCondition condition) {
        try {
            return  entranceAreaService.getDiffCityList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "getCityByAreaId")
    @ResponseBody
    public Object getCityByAreaId(EntranceAreaCondition condition) {
        Object rev = null;
        try {
            rev = entranceAreaService.getCityByAreaId(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rev;
    }
    @RequestMapping(value = "getRightCodeName")
    @ResponseBody
    public Object getRightCodeName(EntranceAreaCondition condition) {
        Object rev = null;
        try {
            rev =entranceAreaService.getRightCodeName(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rev;
    }
    @RequestMapping(value = "save")
    @ResponseBody
    public Object save(EntranceAreaCondition condition) {
        try {
            return  entranceAreaService.insert(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "update")
    @ResponseBody
    public Object update(EntranceAreaCondition condition) {
        try {
            return  entranceAreaService.update(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
