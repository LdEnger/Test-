package cn.com.hiveview.launcher.module.acrossVertical.controller;

import cn.com.hiveview.launcher.module.acrossVertical.condition.ContentCondition;
import cn.com.hiveview.launcher.module.acrossVertical.service.ContentService;
import cn.com.hiveview.launcher.module.portal.condition.ContentPortalCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/7/25.
 */
@Controller
@RequestMapping("/content2")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @RequestMapping(value = "getPageList")
    @ResponseBody
    public Object getPageList(ContentCondition condition) {
        try {
            return  contentService.getContentPortalList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/save")
    @ResponseBody
    public Object save(ContentCondition condition) {
        try {
            return  contentService.insert(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ContentCondition condition) {
        try {
            return  contentService.update(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/del")
    @ResponseBody
    public Object del(ContentCondition condition) {
        try {
            return  contentService.del(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/getMaxSeq")
    @ResponseBody
    public Object getMaxSeq(ContentCondition condition) {
        try {
            return  contentService.getMaxSeq(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/getMinSeq")
    @ResponseBody
    public Object getMinSeq(ContentCondition condition) {
        try {
            return  contentService.getMinSeq(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/getMaxContent")
    @ResponseBody
    public Object getMaxContent(ContentCondition condition) {
        try {
            return  contentService.getMaxContent(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/getMinContent")
    @ResponseBody
    public Object getMinContent(ContentCondition condition) {
        try {
            return  contentService.getMinContent(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
