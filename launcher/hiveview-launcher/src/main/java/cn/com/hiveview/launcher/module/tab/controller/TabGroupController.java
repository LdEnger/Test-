package cn.com.hiveview.launcher.module.tab.controller;

import cn.com.hiveview.launcher.module.acrossVertical.condition.ContentCondition;
import cn.com.hiveview.launcher.module.tab.condition.TabCondition;
import cn.com.hiveview.launcher.module.tab.condition.TabGroupCondition;
import cn.com.hiveview.launcher.module.tab.service.TabGroupService;
import cn.com.hiveview.launcher.module.tab.service.TabService;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by xiach on 2017/10/10.
 */
@Controller
@RequestMapping("tabGroup")
public class TabGroupController {
    @Autowired
    private TabGroupService tabGroupService;

    @RequestMapping(value = "/getPageList")
    @ResponseBody
    public Object getPageList(TabGroupCondition condition) {
        try {

            return  tabGroupService.getPageList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object save(HttpServletRequest request, TabGroupCondition condition) {
        try {
            String add_params = request.getParameter("add_params");
            List<TabGroupCondition> tabGroupConditions = JSONArray.parseArray(add_params, TabGroupCondition.class);
            return  tabGroupService.add(tabGroupConditions);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(TabGroupCondition condition) {
        try {
            return  tabGroupService.update(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object del(TabGroupCondition condition) {
        try {
            return  tabGroupService.delete(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/getMaxContent")
    @ResponseBody
    public Object getMaxContent(TabGroupCondition condition) {
        try {
            return  tabGroupService.getMaxContent(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/getMinContent")
    @ResponseBody
    public Object getMinContent(TabGroupCondition condition) {
        try {
            return  tabGroupService.getMinContent(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/getMaxSeq")
    @ResponseBody
    public Object getMaxSeq(TabGroupCondition condition) {
        try {
            return  tabGroupService.getMaxSeq(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/getCount")
    @ResponseBody
    public Object getCount(TabGroupCondition condition) {
        try {
            return  tabGroupService.getCount(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/addOne")
    @ResponseBody
    public Object addOne(TabGroupCondition condition) {
        try {
            return  tabGroupService.addOne(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/checkVideo")
    @ResponseBody
    public Object checkVideo(TabGroupCondition condition) {
        try {
            return  tabGroupService.checkVideo(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
