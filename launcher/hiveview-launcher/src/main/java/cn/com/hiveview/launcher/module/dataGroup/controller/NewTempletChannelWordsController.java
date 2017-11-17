package cn.com.hiveview.launcher.module.dataGroup.controller;

import cn.com.hiveview.launcher.module.dataGroup.conditon.NewTempletChannelWordsListCondition;
import cn.com.hiveview.launcher.module.dataGroup.conditon.PoralDataGroupListCondition;
import cn.com.hiveview.launcher.module.dataGroup.service.NewTempletChannelWordsService;
import cn.com.hiveview.launcher.module.dataGroup.service.PortalDataGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2017/10/10.
 */
@Controller
@RequestMapping("/NewTempletChannelWordsController")
public class NewTempletChannelWordsController {

    @Autowired
    private NewTempletChannelWordsService newTempletChannelWordsService;

    @RequestMapping(value = "/getPage")
    @ResponseBody
    public Object getPage(NewTempletChannelWordsListCondition condition){
        try{
            return newTempletChannelWordsService.getPage(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    @RequestMapping(value = "/getPageList")
    @ResponseBody
    public Object getPageList(NewTempletChannelWordsListCondition condition){
        try{
            return newTempletChannelWordsService.getPageList(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
}
