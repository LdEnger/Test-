package cn.com.hiveview.launcher.module.portal.controller;

import cn.com.hiveview.launcher.module.portal.condition.TempletCondition;
import cn.com.hiveview.launcher.module.portal.service.PortalTempletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2017/8/1.
 */
@Controller
@RequestMapping("/portal/templet")
public class PortalTempletController {
    @Autowired
    private PortalTempletService templetService;
    @RequestMapping(value = "getList")
    @ResponseBody
    public Object getPageList(TempletCondition condition) {
        try {
            return  templetService.getList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
