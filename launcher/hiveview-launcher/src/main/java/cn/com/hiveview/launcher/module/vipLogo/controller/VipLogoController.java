package cn.com.hiveview.launcher.module.vipLogo.controller;

import cn.com.hiveview.launcher.module.acrossVertical.condition.ContentCondition;
import cn.com.hiveview.launcher.module.vipLogo.condition.VipLogoCondition;
import cn.com.hiveview.launcher.module.vipLogo.service.VipLogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xiach on 2017/10/6.
 */
@Controller
@RequestMapping("/vipLogo")
public class VipLogoController {

    @Autowired
    private VipLogoService vipLogoService;

    @RequestMapping(value = "/list")
    public String getVipLogoList() {
        return "/vipLogo/vipLogoList";
    }

    @RequestMapping(value = "/getPageList")
    @ResponseBody
    public Object getPageList(VipLogoCondition condition) {
        try {

            return  vipLogoService.getPageList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object save(VipLogoCondition condition) {
        try {
            return  vipLogoService.add(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(VipLogoCondition condition) {
        try {
            return  vipLogoService.update(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object del(VipLogoCondition condition) {
        try {
            return  vipLogoService.delete(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/getMaxSeq")
    @ResponseBody
    public Object getMaxSeq(ContentCondition condition) {
        try {
            return  vipLogoService.getMaxSeq(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/getMinSeq")
    @ResponseBody
    public Object getMinSeq(ContentCondition condition) {
        try {
            return  vipLogoService.getMinSeq(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/getMaxContent")
    @ResponseBody
    public Object getMaxContent(ContentCondition condition) {
        try {
            return  vipLogoService.getMaxContent(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/getMinContent")
    @ResponseBody
    public Object getMinContent(ContentCondition condition) {
        try {
            return  vipLogoService.getMinContent(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
