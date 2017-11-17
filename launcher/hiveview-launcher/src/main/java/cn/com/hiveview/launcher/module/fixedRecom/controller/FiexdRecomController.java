package cn.com.hiveview.launcher.module.fixedRecom.controller;

import cn.com.hiveview.launcher.module.fixedRecom.condition.FixedRecomListCondition;
import cn.com.hiveview.launcher.module.fixedRecom.service.FixedRecomSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2017/7/17.
 */
@Controller
@RequestMapping(value = "/fiexdRecomList")
public class FiexdRecomController {

    @RequestMapping(value =  "list")
    public  String getFiexdRecomList(){return  "fixedRecom/fixedRecom";}
    @Autowired
    private FixedRecomSevice fixedRecomSevice;

    @RequestMapping(value = "/getPageList")
    @ResponseBody
    public Object getPageList(FixedRecomListCondition condition){
        try{
            return fixedRecomSevice.getPageList(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }


    @RequestMapping(value = "/getFixedRecomListOne")
    @ResponseBody
    public Object getFixedRecomListOne(FixedRecomListCondition condition){
        try{
            return fixedRecomSevice.getFixedRecomListOne(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
    @RequestMapping(value = "/save")
    @ResponseBody
    public  Object save(FixedRecomListCondition condition){
        try{
            return  fixedRecomSevice.save(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public  Object update(FixedRecomListCondition condition){
        try {
            return fixedRecomSevice.update(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
    @RequestMapping(value =  "/delete")
    @ResponseBody
    public  Object delete(FixedRecomListCondition condition){
        try {
            return fixedRecomSevice.delete(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    @RequestMapping(value = "/getTypeList")
    @ResponseBody
    public  Object getTypeList(FixedRecomListCondition condition){
        try {
            return  fixedRecomSevice.getTypeList(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
    @RequestMapping(value = "/updateEffective")
    @ResponseBody
    public  Object updateEffective(FixedRecomListCondition condition){
        try {
            return fixedRecomSevice.updateEffective(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
}

