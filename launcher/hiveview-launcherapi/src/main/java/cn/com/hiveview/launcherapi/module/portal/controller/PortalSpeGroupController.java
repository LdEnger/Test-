package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.launcherapi.module.portal.condition.PortalSpeGroupCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalTabCondition;
import cn.com.hiveview.launcherapi.module.portal.service.PortalSpeGroupService;
import cn.com.hiveview.launcherapi.module.portal.service.PortalTabService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xiach on 2017/10/10.
 */
@Controller
@RequestMapping("portalSpeGroup")
public class PortalSpeGroupController {
    @Autowired
    private PortalSpeGroupService portalSpeGroupService;

    @RequestMapping(value = { "/getPageList" })
    @ResponseBody
    public Object getPageList(@RequestBody String getStr) {
        try {
            PortalSpeGroupCondition getBean = JSON.parseObject(getStr, PortalSpeGroupCondition.class);
            return JSON.toJSONString(portalSpeGroupService.getPageList(getBean), SerializerFeature.WriteMapNullValue);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
