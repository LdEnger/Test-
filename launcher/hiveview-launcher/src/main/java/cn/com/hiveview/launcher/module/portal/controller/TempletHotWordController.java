package cn.com.hiveview.launcher.module.portal.controller;

import cn.com.hiveview.launcher.module.portal.condition.TempletHotWordCondition;
import cn.com.hiveview.launcher.module.portal.service.TempletHotWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2017/8/1.
 */
@Controller
@RequestMapping("/portal/templetHotWord")
public class TempletHotWordController {
    @Autowired
    private TempletHotWordService templetHotWordService;
    @RequestMapping(value = "getList")
    @ResponseBody
    public Object getPageList(TempletHotWordCondition condition) {
        try {
            return  templetHotWordService.getList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
