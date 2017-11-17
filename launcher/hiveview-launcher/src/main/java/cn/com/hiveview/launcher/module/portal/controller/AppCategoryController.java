package cn.com.hiveview.launcher.module.portal.controller;
import cn.com.hiveview.launcher.module.portal.condition.AppCategoryCondition;
import cn.com.hiveview.launcher.module.portal.service.AppCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2017/7/31.
 */
@Controller
@RequestMapping("appCategory")
public class AppCategoryController {
    @Autowired
    private AppCategoryService appCategoryService;
    @RequestMapping(value = "/getList")
    @ResponseBody
    public Object getPageList(AppCategoryCondition condition) {
        try {
            return  appCategoryService.getAppCategoryList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
