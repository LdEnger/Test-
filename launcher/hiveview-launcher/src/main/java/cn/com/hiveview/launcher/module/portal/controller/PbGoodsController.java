package cn.com.hiveview.launcher.module.portal.controller;

import cn.com.hiveview.launcher.module.portal.condition.PbGoodsCondition;
import cn.com.hiveview.launcher.module.portal.service.PbGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * Created by admin on 2017/12/4.
 */
@Controller
@RequestMapping("/portal/pbGoods")
public class PbGoodsController {
    @Autowired
    PbGoodsService pbGoodsService;

    @RequestMapping(value = "/getPageList")
    @ResponseBody
    public Object getPageList(PbGoodsCondition condition) {
        try {
            return  pbGoodsService.getPageList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
