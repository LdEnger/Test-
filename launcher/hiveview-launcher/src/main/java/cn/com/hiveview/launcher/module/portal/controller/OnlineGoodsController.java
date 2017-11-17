package cn.com.hiveview.launcher.module.portal.controller;

import cn.com.hiveview.launcher.module.portal.condition.OnlineGoodsCondition;
import cn.com.hiveview.launcher.module.portal.service.OnlineGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2017/8/2.
 */
@Controller
@RequestMapping("/portal/onlineGoods")
public class OnlineGoodsController {
    @Autowired
    OnlineGoodsService onlineGoodsService;

    @RequestMapping(value = "/getPageList")
    @ResponseBody
    public Object getPageList(OnlineGoodsCondition condition) {
        try {
            return  onlineGoodsService.getPageList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
