package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.AppVo;
import cn.com.hiveview.launcherapi.module.portal.condition.ActivityFreeVipCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.AppCondition;
import cn.com.hiveview.launcherapi.module.portal.service.AppService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by user on 2017/7/27.
 */
@Controller
@RequestMapping("/app")
public class AppController {

    @Autowired
    private AppService appService;

    @RequestMapping(value = { "/getAppList" })
    @ResponseBody
    public List<AppVo> getAppList(@RequestBody String getStr) {
        try {
            AppCondition getBean = JSON.parseObject(getStr, AppCondition.class);
            return appService.getList(getBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @RequestMapping(value = { "/getPageList" })
    @ResponseBody
    public ScriptPage<AppVo> getPageList(@RequestBody String getStr) {
        try {
            AppCondition getBean = JSON.parseObject(getStr, AppCondition.class);
            return appService.getPageList(getBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
