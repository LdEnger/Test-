package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.CustomRecomBackupsContentVo;
import cn.com.hiveview.launcherapi.module.portal.condition.CustomRecomBackupsContentCondition;
import cn.com.hiveview.launcherapi.module.portal.service.CustomRecomBackupsContentService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2017/8/25.
 */
@Controller
@RequestMapping("/customRecomBackupsController")
public class CustomRecomBackupsController {

    @Autowired
    private CustomRecomBackupsContentService customRecomBackupsContentService;

    @RequestMapping(value = { "/getPageList" })
    @ResponseBody
    public ScriptPage<CustomRecomBackupsContentVo> getPageList(@RequestBody String getStr) {
        try {
            CustomRecomBackupsContentCondition getBean = JSON.parseObject(getStr, CustomRecomBackupsContentCondition.class);
            return customRecomBackupsContentService.getList(getBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/save")
    @ResponseBody
    public Object save(@RequestBody String indexStr ){
        try {
            if(StringUtils.isBlank(indexStr)){
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            CustomRecomBackupsContentCondition getCodition = JSON.parseObject(indexStr,CustomRecomBackupsContentCondition.class);
            if(getCodition == null){
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if(customRecomBackupsContentService.save(getCodition) > 0){
                return  JsonMessage.create(0, "", "success");
            }else {
                return JsonMessage.create(-1L, "数据库插入失败", "");
            }
        }catch (Exception e){
            e.printStackTrace();
            return JsonMessage.create(-1L, e.getMessage(), "");
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public Object update(@RequestBody String indexStr){
        try {
            if(StringUtils.isBlank(indexStr)){
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            CustomRecomBackupsContentCondition updateCodition = JSON.parseObject(indexStr,CustomRecomBackupsContentCondition.class);
            if(null == updateCodition){
                return  JsonMessage.create(-1l, "反序列化失败", "");
            }
            if(customRecomBackupsContentService.update(updateCodition) > 0){
                return  JsonMessage.create(0, "", "success");
            }else {
                return  JsonMessage.create(-1L, "数据库更新失败", "");
            }
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(@RequestBody String indexStr){
        try {
            if(StringUtils.isBlank(indexStr)){
                return  JsonMessage.create(-1l, "参数不能为空", "");
            }
            CustomRecomBackupsContentCondition deleteCondition = JSON.parseObject(indexStr,CustomRecomBackupsContentCondition.class);
            if(null == deleteCondition){
                return  JsonMessage.create(-1l, "反序列化失败", "");
            }
            if(customRecomBackupsContentService.delete(deleteCondition) > 0){
                return  JsonMessage.create(0, "", "success");
            }else {
                return  JsonMessage.create(-1L, "数据库删除失败", "");
            }



        }catch (Exception e){
            e.printStackTrace();
            return JsonMessage.create(-1L, e.getMessage(), "");
        }
    }

    @RequestMapping("/getCount")
    @ResponseBody
    public Object getCount(@RequestBody String indexStr){
        try {
            if(StringUtils.isBlank(indexStr)){
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            CustomRecomBackupsContentCondition condition = JSON.parseObject(indexStr,CustomRecomBackupsContentCondition.class);
            if(null == condition){
                return  JsonMessage.create(-1l, "反序列化失败", "");
            }
            return customRecomBackupsContentService.getCount(condition);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }

    @RequestMapping("/get")
    @ResponseBody
    public Object get(@RequestBody String indexStr){
        try {
            if(StringUtils.isBlank(indexStr)){
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            CustomRecomBackupsContentCondition condition = JSON.parseObject(indexStr,CustomRecomBackupsContentCondition.class);
            if(null == condition){
                return  JsonMessage.create(-1l, "反序列化失败", "");
            }
            return customRecomBackupsContentService.get(condition);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }
    @RequestMapping("/getMinMapping")
    @ResponseBody
    public Object getMinMapping(@RequestBody String getStr){
        try {
            CustomRecomBackupsContentCondition condition = JSON.parseObject(getStr,CustomRecomBackupsContentCondition.class);
            return this.customRecomBackupsContentService.getMinMapping(condition);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/getMaxMapping")
    @ResponseBody
    public Object getMaxMapping(@RequestBody String getStr){
        try {
            CustomRecomBackupsContentCondition condition = JSON.parseObject(getStr,CustomRecomBackupsContentCondition.class);
            return this.customRecomBackupsContentService.getMaxMapping(condition);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/getMaxSeq")
    @ResponseBody
    public Object getMaxSeq(@RequestBody String getStr){
        try {
            CustomRecomBackupsContentCondition condition = JSON.parseObject(getStr,CustomRecomBackupsContentCondition.class);
            return this.customRecomBackupsContentService.getMaxSeq(condition);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
