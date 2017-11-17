package cn.com.hiveview.launcher.module.portal.controller;

import cn.com.hiveview.launcher.module.portal.condition.CloudAppSubjectCondition;
import cn.com.hiveview.launcher.module.portal.service.CloudAppSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2017/7/31.
 */
@Controller
@RequestMapping("/cloudAppSubject")
public class CloudAppSubjectController {
    @Autowired
    private CloudAppSubjectService cloudAppSubjectService;
    @RequestMapping(value = "getPageList")
    @ResponseBody
    public Object getPageList(CloudAppSubjectCondition condition) {
        try {
            return  cloudAppSubjectService.getCloudAppSubjectList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
