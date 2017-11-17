package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalStartInstructionList;
import cn.com.hiveview.entity.module.portal.YouGouGoodsCategory;
import cn.com.hiveview.entity.module.portal.YouGouSpecials;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalStartInstructionCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.YouGouGoodsCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.YouGouSpecialsCondition;
import cn.com.hiveview.launcherapi.module.portal.service.YouGouGoodsService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/yougou/goods")
public class YouGouGoodsController {
    @Autowired
    private YouGouGoodsService youGouGoodsService;

    /**
     *
     * @param getStr
     * @return
     */
    @RequestMapping(value = { "/getComboboxList" })
    @ResponseBody
    public  List<YouGouGoodsCategory> getComboboxList(@RequestBody String getStr) {
        try {
            ScriptPage<YouGouGoodsCategory> scriptPage = new ScriptPage<YouGouGoodsCategory>();
            JsonMessage getBean = JSON.parseObject(getTopCategoryList(getStr).toString(), JsonMessage.class);
            scriptPage= JSON.parseObject(getBean.getData().toString(), ScriptPage.class);
            List<YouGouGoodsCategory> list = (List<YouGouGoodsCategory>) scriptPage.getRows();
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @RequestMapping(value = { "/getSubComboboxList" })
    @ResponseBody
    public  List<YouGouGoodsCategory> getSubComboboxList(@RequestBody String getStr) {
        try {
            ScriptPage<YouGouGoodsCategory> scriptPage = new ScriptPage<YouGouGoodsCategory>();
            JsonMessage getBean = JSON.parseObject(getSubCategoryList(getStr).toString(), JsonMessage.class);
            scriptPage= JSON.parseObject(getBean.getData().toString(), ScriptPage.class);
            List<YouGouGoodsCategory> list = (List<YouGouGoodsCategory>) scriptPage.getRows();
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * 获取一级品牌种类列表接口
     */
    @RequestMapping(value = {"/getTopCategoryList"})
    @ResponseBody
    public Object getTopCategoryList(@RequestBody String str) {
        try {
            YouGouGoodsCondition youGouGoodsCondition = JSON.parseObject(str, YouGouGoodsCondition.class);
            youGouGoodsCondition.setPage(1);
            youGouGoodsCondition.setRows(1000);
            return youGouGoodsService.getTopCategoryList(youGouGoodsCondition);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonMessage.create(-1, "YouGouGoodsController.getTopCategoryList异常", null);
        }
    }

    /**
     * 获取下级商品分类接口 为二三级
     */
    @RequestMapping(value = {"/getSubCategoryList"})
    @ResponseBody
    public Object getSubCategoryList(@RequestBody String str) {
        try {
            YouGouGoodsCondition youGouGoodsCondition = JSON.parseObject(str, YouGouGoodsCondition.class);
            youGouGoodsCondition.setPage(1);
            youGouGoodsCondition.setRows(1000);
            return youGouGoodsService.getSubCategoryList(youGouGoodsCondition);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonMessage.create(-1, "YouGouGoodsController.getSubCategoryList异常", null);
        }
    }

    /**
     * 商品种类下的所有顶级商品列表
     */
    @RequestMapping(value = {"/getList"})
    @ResponseBody
    public Object getList(@RequestBody String str) {
        try {
            YouGouGoodsCondition youGouGoodsCondition = JSON.parseObject(str, YouGouGoodsCondition.class);
            return youGouGoodsService.getList(youGouGoodsCondition);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonMessage.create(-1, "YouGouGoodsController.getList异常", null);
        }
    }
    @RequestMapping(value = { "/getPageList" })
    @ResponseBody
    public Object getPageList(@RequestBody String getStr) {
        try {
            YouGouGoodsCondition getBean = JSON.parseObject(getStr, YouGouGoodsCondition.class);
            return JSON.toJSONString(youGouGoodsService.getPageList(getBean), SerializerFeature.WriteMapNullValue);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
