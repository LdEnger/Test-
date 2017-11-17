package cn.com.hiveview.launcher.module.tab.controller;

import cn.com.hiveview.launcher.module.tab.condition.TabCondition;
import cn.com.hiveview.launcher.module.tab.service.TabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xiach on 2017/10/9.
 */
@Controller
@RequestMapping("/tab")
public class TabController {
    @Autowired
    private TabService tabService;


    @RequestMapping(value = "/list")
    public String getTabList() {
        return "/tab/tabList";
    }

    @RequestMapping(value = "/getPageList")
    @ResponseBody
    public Object getPageList(TabCondition condition) {
        try {

            return  tabService.getPageList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object save(TabCondition condition) {
        try {
            return  tabService.add(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(TabCondition condition) {
        try {
            return  tabService.update(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object del(TabCondition condition) {
        try {
            return  tabService.delete(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
