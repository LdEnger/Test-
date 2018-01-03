package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.launcherapi.module.portal.condition.OnlineGoodsCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PbGoodsCondition;
import cn.com.hiveview.launcherapi.module.portal.service.OnlineGoodsService;
import cn.com.hiveview.launcherapi.module.portal.service.PbGoodsService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2017/12/4.
 */
@Controller
@RequestMapping("/pbGoods")
public class PbGoodsController {
    @Autowired
    private PbGoodsService pbGoodsService;
    @RequestMapping(value = { "/getPageList" })
    @ResponseBody
    public Object getPageList(@RequestBody String getStr) {
        try {
           PbGoodsCondition getBean = JSON.parseObject(getStr, PbGoodsCondition.class);
            return JSON.toJSONString(pbGoodsService.getPageList(getBean), SerializerFeature.WriteMapNullValue);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
