package cn.com.hiveview.launcher.module.fixedRecom.controller;

import cn.com.hiveview.launcher.module.fixedRecom.condition.AppCondition;
import cn.com.hiveview.launcher.module.fixedRecom.condition.FixedRecomContentCondition;
import cn.com.hiveview.launcher.module.fixedRecom.condition.NewContentChanCondition;
import cn.com.hiveview.launcher.module.fixedRecom.service.FixedRecomContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2017/7/25.
 */
@Controller
@RequestMapping(value = "/fixedRecommendContent")
public class FixedRecomContentController {
    @RequestMapping(value =  "list")
    public  String getFiexdRecomContent(){return  "fixedRecommendContent/fixedRecommendContent";}
    @Autowired
    private FixedRecomContentService fixedRecomContentSevice;

    @RequestMapping(value = "/getPageList")
    @ResponseBody
    public Object getPageList(FixedRecomContentCondition condition){
        try{
            return fixedRecomContentSevice.getPageList(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    @RequestMapping(value = "/getFixedRecommendContentOne")
    @ResponseBody
    public Object getFixedRecommendContentOne(FixedRecomContentCondition condition){
        try{
            return fixedRecomContentSevice.getFixedRecommendContentOne(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
    @RequestMapping(value = "/save")
    @ResponseBody
    public  Object save(FixedRecomContentCondition condition){
        try{
            return  fixedRecomContentSevice.save(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public  Object update(FixedRecomContentCondition condition){
        try {
            return fixedRecomContentSevice.update(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
    @RequestMapping(value =  "/delete")
    @ResponseBody
    public  Object delete(FixedRecomContentCondition condition){
        try {
            return fixedRecomContentSevice.delete(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
    @RequestMapping(value = "/getFixedRecommendContentList")
    @ResponseBody
    public Object getFixedRecommendContentList(FixedRecomContentCondition condition){
        try{
            return fixedRecomContentSevice.getFixedRecommendContentList(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
    @RequestMapping(value = "/getAppPageList")
    @ResponseBody
    public Object getAppPageList(AppCondition condition){
        try{
            return fixedRecomContentSevice.getAppPageList(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
    @RequestMapping(value = "/getNewContentChanPageList")
    @ResponseBody
    public Object getNewContentChanPageList(NewContentChanCondition condition){
        try{
            return fixedRecomContentSevice.getNewContentChanPageList(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    @RequestMapping(value = "/getMinMapping")
    @ResponseBody
    public Object getMinMapping(FixedRecomContentCondition condition){
        try{
            return fixedRecomContentSevice.getMinMapping(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    @RequestMapping(value = "/getMaxMapping")
    @ResponseBody
    public Object getMaxMapping(FixedRecomContentCondition condition){
        try{
            return fixedRecomContentSevice.getMaxMapping(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
    @RequestMapping(value = "/getMinSeq")
    @ResponseBody
    public Object getMinSeq(FixedRecomContentCondition condition){
        try{
            return fixedRecomContentSevice.getMinSeq(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
}
