package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.AppTagListVo;
import cn.com.hiveview.entity.module.portal.CloudAppSubjectList;
import cn.com.hiveview.launcherapi.module.portal.condition.AppTagListConditon;
import cn.com.hiveview.launcherapi.module.portal.condition.CloudAppSubjectCondition;
import cn.com.hiveview.launcherapi.module.portal.service.AppTagService;
import cn.com.hiveview.launcherapi.module.portal.service.CloudAppSubjectService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2017/10/17.
 */
@Controller
@RequestMapping("/AppTag")
public class AppTagController {
    @Autowired
    private AppTagService appTagService;

    @RequestMapping(value = {"/getPage"})
    @ResponseBody
    public ScriptPage<AppTagListVo> getPage(@RequestBody String getStr) {
        try {
            AppTagListConditon getBean = JSON.parseObject(getStr, AppTagListConditon.class);
            return appTagService.getPage(getBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}