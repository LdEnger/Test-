package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.NewTempletChannelWordsListVo;
import cn.com.hiveview.entity.module.portal.PortalAreaContentListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.NewTmpletChannelWordsCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalAreaContentListCondition;
import cn.com.hiveview.launcherapi.module.portal.service.NewTempletChannelWordsService;
import cn.com.hiveview.launcherapi.module.portal.service.PortalAreaContentService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2017/10/10.
 */
@Controller
@RequestMapping("/NewTempletChannel")
public class NewTempletChannelWordsController {

    @Autowired
    private NewTempletChannelWordsService portalAreaContentService;

    @RequestMapping("/getPage")
    @ResponseBody
    public ScriptPage<NewTempletChannelWordsListVo> getPage(@RequestBody String getStr){
        try {
            NewTmpletChannelWordsCondition getCondition = JSON.parseObject(getStr,NewTmpletChannelWordsCondition.class);
            return portalAreaContentService.getPage(getCondition);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/getPageList")
    @ResponseBody
    public ScriptPage<NewTempletChannelWordsListVo> getPageList(@RequestBody String getStr){
        try {
            NewTmpletChannelWordsCondition getCondition = JSON.parseObject(getStr,NewTmpletChannelWordsCondition.class);
            return portalAreaContentService.getPageList(getCondition);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
