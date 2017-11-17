package cn.com.hiveview.launcher.module.launcher.controller;

import cn.com.hiveview.launcher.module.launcher.condition.TempletContentCondition;
import cn.com.hiveview.launcher.module.launcher.service.TempletContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/7/27.
 */
@Controller
@RequestMapping("templetContent")
public class TempletContentController {
    @Autowired
    private TempletContentService templetContentService;

    @RequestMapping(value = "getPageList")
    @ResponseBody
    public Object getPageList(TempletContentCondition condition) {
        try {
            return  templetContentService.getPageList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "getMinContent")
    @ResponseBody
    public Object getMinContent(TempletContentCondition condition) {
        try {
            return  templetContentService.getMinContent(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "getMaxContent")
    @ResponseBody
    public Object getMaxContent(TempletContentCondition condition) {
        try {
            return  templetContentService.getMaxContent(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public Object save(TempletContentCondition condition) {
        try {
            return templetContentService.insert(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(TempletContentCondition condition) {
        try {
            return  templetContentService.update(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/del")
    @ResponseBody
    public Object del(TempletContentCondition condition) {
        try {
            return  templetContentService.del(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/getMaxSeq")
    @ResponseBody
    public Object getMaxSeq(TempletContentCondition condition) {
        try {
            return  templetContentService.getMaxSeq(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
