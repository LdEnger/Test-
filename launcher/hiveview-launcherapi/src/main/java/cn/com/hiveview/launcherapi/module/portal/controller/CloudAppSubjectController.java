package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.CloudAppSubjectList;
import cn.com.hiveview.entity.module.portal.CompositeSubjectList;
import cn.com.hiveview.launcherapi.module.portal.condition.CloudAppSubjectCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.CompositeSubjectCondition;
import cn.com.hiveview.launcherapi.module.portal.service.CloudAppSubjectService;
import cn.com.hiveview.launcherapi.module.portal.service.CompositeSubjectService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 应用专题
 * Created by admin on 2017/7/31.
 */
@Controller
@RequestMapping("/cloudAppSubject")
public class CloudAppSubjectController {
    @Autowired
    private CloudAppSubjectService cloudAppSubjectService;
    @RequestMapping(value = { "/getPageList" })
    @ResponseBody
    public ScriptPage<CloudAppSubjectList> getPageList(@RequestBody String getStr) {
        try {
            CloudAppSubjectCondition getBean = JSON.parseObject(getStr, CloudAppSubjectCondition.class);
            return cloudAppSubjectService.getPageList(getBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
