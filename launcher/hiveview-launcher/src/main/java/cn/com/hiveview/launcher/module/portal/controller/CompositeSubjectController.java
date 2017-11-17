package cn.com.hiveview.launcher.module.portal.controller;

import cn.com.hiveview.launcher.module.portal.condition.CompositeSubjectCondition;
import cn.com.hiveview.launcher.module.portal.condition.ContentPortalCondition;
import cn.com.hiveview.launcher.module.portal.service.CompositeSubjectService;
import cn.com.hiveview.launcher.module.portal.service.ContentPortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2017/7/31.
 */
@Controller
@RequestMapping("/compositeSubject")
public class CompositeSubjectController {
    @Autowired
    private CompositeSubjectService compositeSubjectService;
    @RequestMapping(value = "getPageList")
    @ResponseBody
    public Object getPageList(CompositeSubjectCondition condition) {
        try {
            return  compositeSubjectService.getCompositeSubjectList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
