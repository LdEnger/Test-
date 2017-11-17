package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalTabCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.YouGouSpecialsCondition;
import cn.com.hiveview.launcherapi.module.portal.service.YouGouSpecialsService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/yougou/specials")
public class YouGouSpecialsController {
    @Autowired
    private YouGouSpecialsService youGouSpecialsService;

    @RequestMapping(value = {"/getList"}, method = RequestMethod.POST)
    @ResponseBody
    public Object getList(@RequestBody String str) {
        try {
            YouGouSpecialsCondition condition = JSON.parseObject(str, YouGouSpecialsCondition.class);
            return youGouSpecialsService.getList(condition);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonMessage.create(-1, "异常", null);
        }
    }

    @RequestMapping(value = { "/getPageList" })
    @ResponseBody
    public Object getPageList(@RequestBody String getStr) {
        try {
            YouGouSpecialsCondition getBean = JSON.parseObject(getStr, YouGouSpecialsCondition.class);
            return JSON.toJSONString(youGouSpecialsService.getPageList(getBean), SerializerFeature.WriteMapNullValue);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
