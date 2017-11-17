package cn.com.hiveview.launcher.module.customRecomTemplete.Controller;

import cn.com.hiveview.launcher.module.customRecomTemplete.condition.CustomRecomTempleteCondition;
import cn.com.hiveview.launcher.module.customRecomTemplete.service.CustomRecomTempleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2017/7/20.
 */
@Controller
@RequestMapping(value =  "/customRecomTempleteList")
public class CustomRecomTempleteController {

    @RequestMapping(value = "list")
    public  String getCustomRecomTempleteList(){
        return "customRecomTemplete/recomTemplete";
    }

    @Autowired
    private CustomRecomTempleteService customRecomTempleteService;

    @RequestMapping(value = "/getPageList")
    @ResponseBody
    public  Object getPageList(CustomRecomTempleteCondition customRecomTempleteCondition){
        try {
            return  customRecomTempleteService.getList(customRecomTempleteCondition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public  Object save(CustomRecomTempleteCondition customRecomTempleteCondition){
        try {
            return customRecomTempleteService.save(customRecomTempleteCondition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
    @RequestMapping(value = "/saveTemplete")
    @ResponseBody
    public  Object saveTemplete(CustomRecomTempleteCondition customRecomTempleteCondition){
        try {
            return customRecomTempleteService.saveTemplete(customRecomTempleteCondition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
    @RequestMapping(value = "/delete")
    @ResponseBody
    public  Object delete(CustomRecomTempleteCondition customRecomTempleteCondition){
        try {
            return customRecomTempleteService.delete(customRecomTempleteCondition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    @RequestMapping(value =  "/update")
    @ResponseBody
    public  Object update(CustomRecomTempleteCondition customRecomTempleteCondition){
        try {
            return  customRecomTempleteService.update(customRecomTempleteCondition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value =  "/updateTempleteLayout")
    @ResponseBody
    public  Object updateTempleteLayout(CustomRecomTempleteCondition customRecomTempleteCondition){
        try {
            return  customRecomTempleteService.updateTempleteLayout(customRecomTempleteCondition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value =  "/getRolCow")
    @ResponseBody
    public  Object getRolCow(CustomRecomTempleteCondition customRecomTempleteCondition){
        try {
            return  customRecomTempleteService.getRolCow(customRecomTempleteCondition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value =  "/getCustomRecomTemplete")
    @ResponseBody
    public  Object getCustomRecomTemplete(CustomRecomTempleteCondition customRecomTempleteCondition){
        try {
            return  customRecomTempleteService.getCustomRecomTemplete(customRecomTempleteCondition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value =  "/getLayout")
    @ResponseBody
    public  Object getLayout(CustomRecomTempleteCondition customRecomTempleteCondition){
        try {
            return  customRecomTempleteService.getLayout(customRecomTempleteCondition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value =  "/setFatherSelect")
    @ResponseBody
    public  Object setFatherSelect(CustomRecomTempleteCondition condition){
        try {
            return  customRecomTempleteService.setFatherSelect(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value =  "/getContent")
    @ResponseBody
    public  Object getContent(CustomRecomTempleteCondition condition){
        try {
            return  customRecomTempleteService.getContent(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value =  "/updateIsEffective")
    @ResponseBody
    public  Object updateIsEffective(CustomRecomTempleteCondition condition){
        try {
            return  customRecomTempleteService.updateIsEffective(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value =  "/selectFatherId")
    @ResponseBody
    public  Object selectFatherId(CustomRecomTempleteCondition condition){
        try {
            return  customRecomTempleteService.selectFatherId(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value =  "/selectByTempleteId")
    @ResponseBody
    public  Object selectByTempleteId(CustomRecomTempleteCondition condition){
        System.out.println(condition);
        try {
            return  customRecomTempleteService.selectByTempleteId(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/getFirstList")
    @ResponseBody
    public  Object getFirstList(CustomRecomTempleteCondition condition){
/*        System.out.println(condition);*/
        try {
            return  customRecomTempleteService.getFirstList(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
