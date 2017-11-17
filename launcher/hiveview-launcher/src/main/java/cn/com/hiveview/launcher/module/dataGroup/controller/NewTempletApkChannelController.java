package cn.com.hiveview.launcher.module.dataGroup.controller;

import cn.com.hiveview.launcher.module.dataGroup.conditon.AppCategoryCondition;
import cn.com.hiveview.launcher.module.dataGroup.conditon.NewTempletAkpChannelCondition;
import cn.com.hiveview.launcher.module.dataGroup.service.NewTempletApkChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2017/10/12.
 */
@Controller
@RequestMapping("/NewTempletApkChannelController")
public class NewTempletApkChannelController {

    @Autowired
    private NewTempletApkChannelService newTempletApkChannelService;

    @RequestMapping(value = "/getChannelPage")
    @ResponseBody
    public Object getChannelPage(NewTempletAkpChannelCondition condition){
        try{
            return newTempletApkChannelService.getChannelPage(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }


    @RequestMapping("/getApp")
    @ResponseBody
    public Object getApp(AppCategoryCondition condition){
        try {
            return newTempletApkChannelService.getApp(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
