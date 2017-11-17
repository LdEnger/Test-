package cn.com.hiveview.launcher.module.tab.controller;

import cn.com.hiveview.launcher.module.tab.condition.SpeGroupCondition;
import cn.com.hiveview.launcher.module.tab.condition.TabGroupCondition;
import cn.com.hiveview.launcher.module.tab.service.SpeGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xiach on 2017/10/10.
 */
@Controller
@RequestMapping("speGroup")
public class SpeGroupController {
    @Autowired
    private SpeGroupService speGroupService;
    @RequestMapping(value = "/getPageList")
    @ResponseBody
    public Object getPageList(SpeGroupCondition condition) {
        try {

            return  speGroupService.getPageList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
