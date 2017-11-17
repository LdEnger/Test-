package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.ActivityFreeVipList;
import cn.com.hiveview.entity.module.portal.ActivityList;
import cn.com.hiveview.entity.module.portal.CommonActivityList;
import cn.com.hiveview.launcherapi.module.portal.condition.ActivityCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.ActivityFreeVipCondition;
import cn.com.hiveview.launcherapi.module.portal.service.ActivityFreeVipService;
import cn.com.hiveview.launcherapi.module.portal.service.ActivityService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 抽奖活动
 * Created by admin on 2017/7/31.
 */
@Controller
@RequestMapping("/activityFreeVip")
public class ActivityFreeVipController {
    @Autowired
    private ActivityFreeVipService activityFreeVipService;
    @RequestMapping(value = { "/getPageList" })
    @ResponseBody
    public ScriptPage<ActivityFreeVipList> getPageList(@RequestBody String getStr) {
        try {
            ActivityFreeVipCondition getBean = JSON.parseObject(getStr, ActivityFreeVipCondition.class);
            return activityFreeVipService.getPageList(getBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @RequestMapping(value = { "/getList" })
    @ResponseBody
    public List<CommonActivityList> getList() {
        try {
            return activityFreeVipService.getList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
