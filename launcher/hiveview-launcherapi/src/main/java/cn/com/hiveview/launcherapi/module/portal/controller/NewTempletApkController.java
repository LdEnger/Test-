package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.NewTempletApkListVo;
import cn.com.hiveview.entity.module.portal.NewTempletChannelWordsListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.NewTempletApkCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.NewTmpletChannelWordsCondition;
import cn.com.hiveview.launcherapi.module.portal.service.NewTempletAkpService;
import cn.com.hiveview.launcherapi.module.portal.service.NewTempletChannelWordsService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by user on 2017/10/11.
 */
@Controller
@RequestMapping("/NewTempletApkController")
public class NewTempletApkController {
    @Autowired
    private NewTempletAkpService newTempletAkpService;

    @RequestMapping("/getPage")
    @ResponseBody
    public List<NewTempletApkListVo> getPage(@RequestBody String getStr){
        try {
            NewTempletApkCondition getCondition = JSON.parseObject(getStr,NewTempletApkCondition.class);
            return newTempletAkpService.getPage(getCondition);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }




}
