package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.portal.TempletApkList;
import cn.com.hiveview.entity.module.portal.TempletList;
import cn.com.hiveview.launcherapi.module.portal.condition.TempletApkCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.TempletCondition;
import cn.com.hiveview.launcherapi.module.portal.service.TempletApkService;
import cn.com.hiveview.launcherapi.module.portal.service.TempletService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by admin on 2017/8/1.
 */
@Controller
@RequestMapping("/templetApk")
public class TempletApkController {
    @Autowired
    private TempletApkService templetApkService;

    @RequestMapping(value = {"/getList"})
    @ResponseBody
    public List<TempletApkList> getPageList(@RequestBody String getStr){
        try{
            TempletApkCondition getBean = JSON.parseObject(getStr,TempletApkCondition.class);
            return  templetApkService.getList(getBean);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }

}
