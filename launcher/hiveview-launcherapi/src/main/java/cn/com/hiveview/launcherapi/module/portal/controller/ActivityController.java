package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.ActivityList;
import cn.com.hiveview.launcherapi.module.portal.condition.ActivityCondition;
import cn.com.hiveview.launcherapi.module.portal.service.ActivityService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 大麦活动
 * Created by admin on 2017/7/31.
 */
@Controller
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @RequestMapping(value = { "/getPageList" })
    @ResponseBody
    public ScriptPage<ActivityList> getPageList(@RequestBody String getStr) {
        try {
            ActivityCondition getBean = JSON.parseObject(getStr, ActivityCondition.class);
            return activityService.getPageList(getBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
