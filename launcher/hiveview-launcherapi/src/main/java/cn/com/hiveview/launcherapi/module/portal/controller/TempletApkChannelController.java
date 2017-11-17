package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.portal.TempletApkChannelVo;
import cn.com.hiveview.launcherapi.module.portal.condition.TempletApkChannelCondition;
import cn.com.hiveview.launcherapi.module.portal.service.TempletApkChannelService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by user on 2017/7/27.
 */
@Controller
@RequestMapping("/channel")
public class TempletApkChannelController {

    @Autowired
    private TempletApkChannelService templetApkChannelService;

    @RequestMapping(value = {"/getChannelList"})
    @ResponseBody
    public List<TempletApkChannelVo> getPageList(@RequestBody String getStr){
        try{
            TempletApkChannelCondition getBean = JSON.parseObject(getStr,TempletApkChannelCondition.class);
            return  templetApkChannelService.getChannelList(getBean);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }
    @RequestMapping(value = {"/getChannelTypeList"})
    @ResponseBody
    public List<TempletApkChannelVo> getChannelTypeList(@RequestBody String getStr){
        try{
            TempletApkChannelCondition getBean = JSON.parseObject(getStr,TempletApkChannelCondition.class);
            return  templetApkChannelService.getChannelTypeList(getBean);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }
}
