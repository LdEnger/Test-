package cn.com.hiveview.launcher.module.customRecomTemplete.Controller;

import cn.com.hiveview.launcher.module.customRecomTemplete.condition.CustomRecomBackupsCondition;
import cn.com.hiveview.launcher.module.customRecomTemplete.condition.CustomRecomContentCondition;
import cn.com.hiveview.launcher.module.customRecomTemplete.service.CustomRecomBackupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2017/8/28.
 */
@Controller
@RequestMapping(value =  "/customRecomBackupsList")
public class CustomRecomBackupsController {

    @RequestMapping(value = "list")
    public  String getCustomRecomTempleteList(){
        return "customRecomTemplete/recomBackups";
    }

    @Autowired
    private CustomRecomBackupsService customRecomBackupsService;


    @RequestMapping(value = "/getPageList")
    @ResponseBody
    public  Object getPageList(CustomRecomBackupsCondition customRecomTempleteCondition){
        try {
            return  customRecomBackupsService.getList(customRecomTempleteCondition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    @RequestMapping(value =  "/save")
    @ResponseBody
    public  Object save(CustomRecomBackupsCondition condition){
        try {
            return  customRecomBackupsService.save(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value =  "/update")
    @ResponseBody
    public  Object update(CustomRecomBackupsCondition condition){
        try {
            return  customRecomBackupsService.update(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value =  "/delete")
    @ResponseBody
    public  Object delete(CustomRecomBackupsCondition condition){
        try {
            return  customRecomBackupsService.delete(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value =  "/getCount")
    @ResponseBody
    public  Object getCount(CustomRecomBackupsCondition condition){
        try {
            return  customRecomBackupsService.getCount(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value =  "/get")
    @ResponseBody
    public  Object get(CustomRecomContentCondition condition){
        try {
            return  customRecomBackupsService.get(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/getMinMapping")
    @ResponseBody
    public Object getMinMapping(CustomRecomBackupsCondition condition){
        try{
            return customRecomBackupsService.getMinMapping(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    @RequestMapping(value = "/getMaxMapping")
    @ResponseBody
    public Object getMaxMapping(CustomRecomBackupsCondition condition){
        try{
            return customRecomBackupsService.getMaxMapping(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
    @RequestMapping(value = "/getMaxSeq")
    @ResponseBody
    public Object getMaxSeq(CustomRecomBackupsCondition condition){
        try{
            return customRecomBackupsService.getMaxSeq(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }


    @RequestMapping(value = "/selectRowCol")
    @ResponseBody
    public Object selectRowCol(CustomRecomBackupsCondition condition){
        try{
            return customRecomBackupsService.selectRowCol(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
}
