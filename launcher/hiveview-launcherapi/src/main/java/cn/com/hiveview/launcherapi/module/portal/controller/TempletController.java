package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.portal.TempletList;
import cn.com.hiveview.launcherapi.module.portal.condition.TempletCondition;
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
@RequestMapping("/templet")
public class TempletController {

    @Autowired
    private TempletService templetService;

    @RequestMapping(value = {"/getList"})
    @ResponseBody
    public List<TempletList> getPageList(@RequestBody String getStr){
        try{
            TempletCondition getBean = JSON.parseObject(getStr,TempletCondition.class);
            return  templetService.getList(getBean);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }
}
