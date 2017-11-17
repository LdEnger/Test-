package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.portal.NewTempletApkChannelListVo;
import cn.com.hiveview.entity.module.portal.NewTempletApkListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.NewTempletApkChannelCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.NewTempletApkCondition;
import cn.com.hiveview.launcherapi.module.portal.service.NewTempletApkChannelService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by user on 2017/10/12.
 */
@Controller
@RequestMapping("/NewTempletApkChannelController")
public class NewTempletApkChannelController {

    @Autowired
    private NewTempletApkChannelService newTempletApkChannelService;

    @RequestMapping("/getChannelPage")
    @ResponseBody
    public List<NewTempletApkChannelListVo> getChannelPage(@RequestBody String getStr){
        try {
            NewTempletApkChannelCondition getCondition = JSON.parseObject(getStr,NewTempletApkChannelCondition.class);
            return newTempletApkChannelService.getChannelPage(getCondition);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
