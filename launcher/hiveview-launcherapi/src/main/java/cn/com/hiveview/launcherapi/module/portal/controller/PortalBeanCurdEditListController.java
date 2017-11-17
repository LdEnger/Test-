package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalBeanCurdEditListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalBeanCurdEditListCondition;
import cn.com.hiveview.launcherapi.module.portal.service.PortalBeanCurdEditListService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by user on 2017/7/25.
 */
@Controller
@RequestMapping(value = "/portalBeanCurdEditListController")
public class PortalBeanCurdEditListController {

    @Autowired
    private PortalBeanCurdEditListService portalBeanCurdEditListService;

    @RequestMapping("/getPageList")
    @ResponseBody
    public ScriptPage<PortalBeanCurdEditListVo> getPageList(@RequestBody String getStr){
        try {
            PortalBeanCurdEditListCondition getCondition = JSON.parseObject(getStr,PortalBeanCurdEditListCondition.class);
            return  this.portalBeanCurdEditListService.getPageList(getCondition);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }
    @RequestMapping("/getList")
    @ResponseBody
    public  ScriptPage<PortalBeanCurdEditListVo> getList(@RequestBody String getStr){
        try {
            PortalBeanCurdEditListCondition getCondition = JSON.parseObject(getStr,PortalBeanCurdEditListCondition.class);
            return  this.portalBeanCurdEditListService.getList(getCondition);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }

    @RequestMapping("/getEditList")
    @ResponseBody
    public List<PortalBeanCurdEditListVo> getEditList(@RequestBody String getStr){
        try {
            PortalBeanCurdEditListCondition codition = JSON.parseObject(getStr,PortalBeanCurdEditListCondition.class);
            return this.portalBeanCurdEditListService.getEditList(codition);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }
    @RequestMapping("/getEdit")
    @ResponseBody
    public PortalBeanCurdEditListVo getEdit(@RequestBody String getStr){
        try {
            PortalBeanCurdEditListCondition getCodition = JSON.parseObject(getStr,PortalBeanCurdEditListCondition.class);
            return this.portalBeanCurdEditListService.getEdit(getCodition);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(@RequestBody String delStr){
        try {
            if(StringUtils.isBlank(delStr)){
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalBeanCurdEditListCondition delCondition = JSON.parseObject(delStr,PortalBeanCurdEditListCondition.class);
            if( null == delCondition){
                return JsonMessage.create(-1l, "反序列化失败", "");
            }
            if(portalBeanCurdEditListService.delete(delCondition) > 0){
                return  JsonMessage.create(0, "", "");
            }else {
                return  JsonMessage.create(-1L, "数据库删除失败", "");
            }
        }catch (Exception e){
            e.printStackTrace();
            return JsonMessage.create(-1L, e.getMessage(), "");
        }
    }

    @RequestMapping("/save")
    @ResponseBody
    public  Object save(@RequestBody String saveStr){
        try {
            if(StringUtils.isBlank(saveStr)){
                return  JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalBeanCurdEditListCondition saveCondition = JSON.parseObject(saveStr,PortalBeanCurdEditListCondition.class);
            if(null == saveCondition){
                return JsonMessage.create(-1l, "反序列化失败", "");
            }
            if(portalBeanCurdEditListService.save(saveCondition) > 0){
                return  JsonMessage.create(0, "", "");
            }else {
                return JsonMessage.create(-1L, "数据库插入失败", "");
            }
        }catch (Exception e){
            e.printStackTrace();
            return JsonMessage.create(-1L, e.getMessage(), "");
        }
    }
    @RequestMapping("/getEditCount")
    @ResponseBody
    public Object getEditCount(@RequestBody String getStr,PortalBeanCurdEditListCondition p){
        Integer result = 0;
        try {
            result = this.portalBeanCurdEditListService.getEditCount(p);
            if(result > 0){
                return JsonMessage.create(0, "重复数据", "");
            }else {
                return JsonMessage.create(-11, "", "");
            }
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }

    }
    @RequestMapping("/update")
    @ResponseBody
    public Object update(@RequestBody String upStr){
        try {
            if(StringUtils.isBlank(upStr)){
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalBeanCurdEditListCondition upCondition = JSON.parseObject(upStr,PortalBeanCurdEditListCondition.class);
            if(null == upCondition){
                return JsonMessage.create(-1l, "反序列化失败", "");
            }
            if (portalBeanCurdEditListService.update(upCondition) > 0 ){
                return  JsonMessage.create(0, "", "");
            }else {
                return JsonMessage.create(-1L, "数据库更新失败", "");
            }
        }catch (Exception e){
            e.printStackTrace();
            return JsonMessage.create(-1L, e.getMessage(), "");
        }

    }
}
