package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.launcherapi.module.portal.condition.OnlineGoodsCondition;
import cn.com.hiveview.launcherapi.module.portal.service.OnlineGoodsService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2017/8/2.
 */
@Controller
@RequestMapping("/onlineGoods")
public class OnlineGoodsController {
    @Autowired
    private OnlineGoodsService onlineGoodsService;
    @RequestMapping(value = { "/getPageList" })
    @ResponseBody
    public Object getPageList(@RequestBody String getStr) {
        try {
            OnlineGoodsCondition getBean = JSON.parseObject(getStr, OnlineGoodsCondition.class);
            return JSON.toJSONString(onlineGoodsService.getPageList(getBean), SerializerFeature.WriteMapNullValue);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
