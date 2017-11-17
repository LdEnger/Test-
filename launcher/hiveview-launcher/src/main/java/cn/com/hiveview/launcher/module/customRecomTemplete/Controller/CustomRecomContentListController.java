package cn.com.hiveview.launcher.module.customRecomTemplete.Controller;

import cn.com.hiveview.launcher.module.customRecomTemplete.condition.CustomRecomContentCondition;
import cn.com.hiveview.launcher.module.customRecomTemplete.condition.CustomRecomTempleteCondition;
import cn.com.hiveview.launcher.module.customRecomTemplete.service.CustomRecomContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2017/8/7.
 */
@Controller
@RequestMapping(value =  "/customRecomContentList")
public class CustomRecomContentListController {

    @RequestMapping(value = "list")
    public  String getCustomRecomTempleteList(){
        return "customRecomTemplete/recomContent";
    }

    @Autowired
    private CustomRecomContentService customRecomContentService;

    @RequestMapping(value =  "/save")
    @ResponseBody
    public  Object save(CustomRecomTempleteCondition condition){
        try {

            return  customRecomContentService.save(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value =  "/update")
    @ResponseBody
    public  Object update(CustomRecomTempleteCondition condition){
        try {
            return  customRecomContentService.update(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value =  "/delete")
    @ResponseBody
    public  Object delete(CustomRecomTempleteCondition condition){
        try {
            return  customRecomContentService.delete(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value =  "/getCount")
    @ResponseBody
    public  Object getCount(CustomRecomContentCondition condition){
        try {
            return  customRecomContentService.getCount(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value =  "/saveCopyContent")
    @ResponseBody
    public  Object saveCopyContent(CustomRecomTempleteCondition condition){
        try {
            return  customRecomContentService.saveCopyContent(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
