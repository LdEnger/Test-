package cn.com.hiveview.launcher.module.portal.controller;
import cn.com.hiveview.launcher.module.portal.condition.ContentPortalCondition;
import cn.com.hiveview.launcher.module.portal.service.ContentPortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2017/7/10.
 */
@Controller
@RequestMapping("/content")
public class ContentPortalController {
    @Autowired
    private ContentPortalService contentPortalService;

//    @RequestMapping(value = "list")
//    public String getContentPortalList() {
//        return "portal/contentPortalList";
//    }
    @RequestMapping(value = "getPageList")
    @ResponseBody
    public Object getPageList(ContentPortalCondition condition) {
        try {
            return  contentPortalService.getContentPortalList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/save")
    @ResponseBody
    public Object save(ContentPortalCondition condition) {
        try {
            return  contentPortalService.insert(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ContentPortalCondition condition) {
        try {
            return  contentPortalService.update(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/del")
    @ResponseBody
    public Object del(ContentPortalCondition condition) {
        try {
            return  contentPortalService.del(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
