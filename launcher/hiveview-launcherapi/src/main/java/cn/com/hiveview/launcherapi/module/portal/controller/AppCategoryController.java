package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.portal.AppCategoryList;
import cn.com.hiveview.launcherapi.module.portal.condition.AppCategoryCondition;
import cn.com.hiveview.launcherapi.module.portal.service.AppCategoryService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by admin on 2017/7/31.
 */
@Controller
@RequestMapping("/appCategory")
public class AppCategoryController {
    @Autowired
    private AppCategoryService appCategoryService;
    @RequestMapping(value = { "/getList" })
    @ResponseBody
    public List<AppCategoryList> getList(@RequestBody String getStr) {
        try {
            AppCategoryCondition getBean = JSON.parseObject(getStr, AppCategoryCondition.class);
            return appCategoryService.getList(getBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
