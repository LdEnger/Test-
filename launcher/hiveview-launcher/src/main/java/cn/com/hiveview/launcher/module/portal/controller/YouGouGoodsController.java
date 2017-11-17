package cn.com.hiveview.launcher.module.portal.controller;

import cn.com.hiveview.launcher.module.portal.condition.YouGouGoodsCondition;
import cn.com.hiveview.launcher.module.portal.condition.YouGouSpecialsCondition;
import cn.com.hiveview.launcher.module.portal.service.YouGouGoodsService;
import cn.com.hiveview.launcher.module.portal.service.YouGouSpecialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2017/10/15.
 */
@Controller
@RequestMapping("/portal/youGouGoods")
public class YouGouGoodsController {
    @Autowired
    private YouGouGoodsService youGouGoodsService;
    @RequestMapping(value = "getPageList")
    @ResponseBody
    public Object getPageList(YouGouGoodsCondition condition) {
        try {
            return  youGouGoodsService.getYouGouGoodsList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "getTopCategoryList")
    @ResponseBody
    public Object getTopCategoryList(YouGouGoodsCondition condition) {
        try {
            return  youGouGoodsService.getTopCategoryList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "getSubCategoryList")
    @ResponseBody
    public Object getSubCategoryList(YouGouGoodsCondition condition) {
        try {
            return  youGouGoodsService.getSubCategoryList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
