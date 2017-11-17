package cn.com.hiveview.launcher.module.launcher.controller;

import cn.com.hiveview.launcher.module.launcher.condition.TempletTabCondition;
import cn.com.hiveview.launcher.module.launcher.service.TempletTabService;
import cn.com.hiveview.launcher.module.tab.condition.TabGroupCondition;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017/7/27.
 */
@Controller
@RequestMapping("templetTab")
public class TempletTabController {
    @Autowired
    private TempletTabService templetTabService;

    @RequestMapping(value = "getPageList")
    @ResponseBody
    public Object getPageList(TempletTabCondition condition) {
        try {
            return  templetTabService.getPageList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "getMinTab")
    @ResponseBody
    public Object getMinTab(TempletTabCondition condition) {
        try {
            return  templetTabService.getMinTab(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "getMaxTab")
    @ResponseBody
    public Object getMaxTab(TempletTabCondition condition) {
        try {
            return  templetTabService.getMaxTab(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "getTopSeq")
    @ResponseBody
    public Object getTopSeq(TempletTabCondition condition) {
        try {
            return  templetTabService.getTopSeq(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public Object save(HttpServletRequest request, TempletTabCondition condition) {
        try {
            String add_params = request.getParameter("add_params");
            List<TempletTabCondition> tabGroupConditions = JSONArray.parseArray(add_params, TempletTabCondition.class);
            return templetTabService.insert(tabGroupConditions);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/moveTop")
    @ResponseBody
    public Object moveTop(TempletTabCondition condition) {
        try {
            return  templetTabService.moveTop(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/moveUp")
    @ResponseBody
    public Object moveUp(TempletTabCondition condition) {
        try {
            return  templetTabService.moveUp(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/moveDown")
    @ResponseBody
    public Object moveDown(TempletTabCondition condition) {
        try {
            return  templetTabService.moveDown(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(TempletTabCondition condition) {
        try {
            return  templetTabService.update(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/del")
    @ResponseBody
    public Object del(TempletTabCondition condition) {
        try {
            return  templetTabService.del(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/getMaxSeq")
    @ResponseBody
    public Object getMaxSeq(TempletTabCondition condition) {
        try {
            return  templetTabService.getMaxSeq(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/preview")
    @ResponseBody
    public Object preview(TabGroupCondition condition) {
        try {
            return  templetTabService.preview(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
