package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.launcherapi.module.portal.condition.NewContentChanCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalScreenRecommendListCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalSysAppIconsListCondition;
import cn.com.hiveview.launcherapi.module.portal.service.NewContentChanService;
import cn.com.hiveview.launcherapi.module.portal.service.PortalSysAppIconsListService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2017/7/24.
 */
@Controller
@RequestMapping("/newContentChan")
public class NewContentChanController {
    @Autowired
    private NewContentChanService newContentChanService;
    @RequestMapping(value = { "/getPageList" })
    @ResponseBody
    public Object getPageList(@RequestBody String getStr) {
        try {
            NewContentChanCondition getBean = JSON.parseObject(getStr, NewContentChanCondition.class);
            return JSON.toJSONString(newContentChanService.getPageAllList(getBean), SerializerFeature.WriteMapNullValue);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @RequestMapping(value = { "/getList" })
    @ResponseBody
    public Object getList() {
        try {
            NewContentChanCondition condition = new NewContentChanCondition();
            return JsonMessage.create(0, "",newContentChanService.getList(condition));
        } catch (Exception ex) {
            ex.printStackTrace();
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }
}
