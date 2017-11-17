package cn.com.hiveview.launcher.module.dataGroup.controller;

import cn.com.hiveview.launcher.module.dataGroup.conditon.NewTempletApkListCondition;
import cn.com.hiveview.launcher.module.dataGroup.service.NewTempletApkService;
import cn.com.hiveview.launcher.module.dataGroup.service.NewTempletChannelWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2017/10/11.
 */
@Controller
@RequestMapping("/NewTempletApkController")
public class NewTempletApkController {

    @Autowired
    private NewTempletApkService newTempletApkService;

    @RequestMapping(value = "/getPage")
    @ResponseBody
    public Object getPage(NewTempletApkListCondition condition){
        try{
            return newTempletApkService.getPage(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }




}
