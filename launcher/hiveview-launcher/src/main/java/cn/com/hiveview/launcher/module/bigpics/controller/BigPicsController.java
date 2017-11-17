package cn.com.hiveview.launcher.module.bigpics.controller;

import cn.com.hiveview.launcher.module.bigpics.condition.BigPicsCondition;
import cn.com.hiveview.launcher.module.bigpics.condition.BigPicsCondition;
import cn.com.hiveview.launcher.module.bigpics.service.BigPicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2017/7/11.
 */
@Controller
@RequestMapping(value = "/bigPics")
public class BigPicsController {

    @RequestMapping(value = "list")
    public String getBigPicsList(){
        return "bigPics/bigPicsList";
    }
    @Autowired
    private BigPicsService bigPicsService;

    @RequestMapping(value = "getList")
    @ResponseBody
    public  Object getPageList(BigPicsCondition condition){
        try{
            return  bigPicsService.getPageList(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public  Object save(BigPicsCondition condition){
        try{
            return  bigPicsService.save(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public  Object update(BigPicsCondition condition){
        try{
            return  bigPicsService.update(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public Object del(BigPicsCondition condition){
        try{
            return bigPicsService.del(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }


    @RequestMapping(value = "updateBig")
    @ResponseBody
    public  Object updateBig(BigPicsCondition condition){
        try{
            return  bigPicsService.updateBig(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
