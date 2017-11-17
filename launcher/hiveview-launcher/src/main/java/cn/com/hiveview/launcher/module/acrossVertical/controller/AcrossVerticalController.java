package cn.com.hiveview.launcher.module.acrossVertical.controller;


import cn.com.hiveview.launcher.module.acrossVertical.condition.AcrossVerticalListCondition;
import cn.com.hiveview.launcher.module.acrossVertical.service.AcrossVerticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/7/24.
 */
@Controller
@RequestMapping("/acrossVertical")
public class AcrossVerticalController {

    @RequestMapping(value = "list")
    public String getMacAreaList() {
        return "acrossVertical/acrossVerticalList";
    }

    @Autowired
    private AcrossVerticalService acrossVerticalService;


    @RequestMapping(value = "getPageList")
    @ResponseBody
    public Object getPageList(AcrossVerticalListCondition condition) {
        try {
            condition.setRecommendType(1);
            return  acrossVerticalService.getScreenPortalList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/save")
    @ResponseBody
    public Object save(AcrossVerticalListCondition condition) {
        try {
            return  acrossVerticalService.insert(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(AcrossVerticalListCondition condition) {
        try {
            return  acrossVerticalService.update(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/del")
    @ResponseBody
    public Object del(AcrossVerticalListCondition condition) {
        try {
            return  acrossVerticalService.del(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
