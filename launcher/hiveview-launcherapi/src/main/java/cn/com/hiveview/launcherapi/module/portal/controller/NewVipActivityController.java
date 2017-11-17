package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.launcherapi.module.portal.condition.MergeVideoData;
import cn.com.hiveview.launcherapi.module.portal.condition.NewVipActivityCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.OnlineGoodsCondition;
import cn.com.hiveview.launcherapi.module.portal.service.NewVipActivityService;
import cn.com.hiveview.launcherapi.module.portal.service.OnlineGoodsService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * VIP 活动
 * Created by admin on 2017/10/13.
 */
@Controller
@RequestMapping("/newVipActivity")
public class NewVipActivityController {
    @Autowired
    private NewVipActivityService newVipActivityService;
    @RequestMapping(value = { "/getPageList" })
    @ResponseBody
    public Object getPageList(@RequestBody String getStr) {
        try {
            NewVipActivityCondition getBean = JSON.parseObject(getStr, NewVipActivityCondition.class);
            return JSON.toJSONString(newVipActivityService.getPageList(getBean), SerializerFeature.WriteMapNullValue);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
