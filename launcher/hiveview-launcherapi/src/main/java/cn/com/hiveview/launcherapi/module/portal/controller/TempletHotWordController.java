package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.portal.TempletHotWordList;
import cn.com.hiveview.launcherapi.module.portal.condition.TempletHotWordCondition;
import cn.com.hiveview.launcherapi.module.portal.service.TempletHotWordService;
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
@RequestMapping("/templetHotWord")
public class TempletHotWordController {
    @Autowired
    private TempletHotWordService templetHotWordService;

    @RequestMapping(value = {"/getList"})
    @ResponseBody
    public List<TempletHotWordList> getPageList(@RequestBody String getStr){
        try{
            TempletHotWordCondition getBean = JSON.parseObject(getStr,TempletHotWordCondition.class);
            return  templetHotWordService.getList(getBean);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }
}
