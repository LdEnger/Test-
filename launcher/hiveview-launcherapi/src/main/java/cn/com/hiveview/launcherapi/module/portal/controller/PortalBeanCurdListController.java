package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalBeanCurdEditListVo;
import cn.com.hiveview.entity.module.portal.PortalBeanCurdListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalBeanCurdListCondition;
import cn.com.hiveview.launcherapi.module.portal.service.PortalBeanCurdListService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by user on 2017/7/24.
 */
@Controller
@RequestMapping("/portalBeanCurdList")
public class PortalBeanCurdListController {

    @Autowired
    private PortalBeanCurdListService portalBeanCurdListService;

    @RequestMapping("/getPageList")
    @ResponseBody
    public ScriptPage<PortalBeanCurdEditListVo> getPageList(@RequestBody String str){
        try {
            PortalBeanCurdListCondition getCodition = JSON.parseObject(str ,PortalBeanCurdListCondition.class);
            return  this.portalBeanCurdListService.getPageList(getCodition);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }

    @RequestMapping("/updateIs")
    @ResponseBody
    public Object updateIs(@RequestBody String updateStr){
        try {
            if(StringUtils.isBlank(updateStr)){
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalBeanCurdListCondition upCondition = JSON.parseObject(updateStr,PortalBeanCurdListCondition.class);
            if(null == upCondition){
                return  JsonMessage.create(-1l, "反序列化失败", "");
            }
            if(portalBeanCurdListService.updateIs(upCondition) > 0 ){
                return  JsonMessage.create(0, "", "");
            }else {
                return  JsonMessage.create(-1L, "数据库更新失败", "");
            }
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public  Object update(@RequestBody String  updateStr){
        try {
            if(StringUtils.isBlank(updateStr)){
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalBeanCurdListCondition upCondition = JSON.parseObject(updateStr,PortalBeanCurdListCondition.class);
            if(null == upCondition){
                return  JsonMessage.create(-1l, "反序列化失败", "");
            }
            if(portalBeanCurdListService.update(upCondition) > 0){
                return  JsonMessage.create(0, "", "");
            }else {
                return  JsonMessage.create(-1L, "数据库更新失败", "");
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/save")
    @ResponseBody
    public  Object save(@RequestBody String saveStr){
        try{
            if (StringUtils.isBlank(saveStr)){
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalBeanCurdListCondition saveCondition = JSON.parseObject(saveStr,PortalBeanCurdListCondition.class);
            if(null == saveCondition){
                return  JsonMessage.create(-1l, "反序列化失败", "");
            }
            if(portalBeanCurdListService.save(saveCondition) > 0){
                return  JsonMessage.create(0, "", "");
            }else {
                return  JsonMessage.create(-1L, "数据库添加失败", "");
            }
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }


    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(@RequestBody String delStr){
        try {
            if(StringUtils.isBlank(delStr)){
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalBeanCurdListCondition delCondition = JSON.parseObject(delStr,PortalBeanCurdListCondition.class);
            if(null == delCondition){
                return JsonMessage.create(-1l, "反序列化失败", "");
            }
            Integer str = portalBeanCurdListService.delete(delCondition);
            if(str == -1){
               return  JsonMessage.create(1, "", "");
            }else
            if(str > 0){
                return  JsonMessage.create(0, "", "");
            }else {
                return  JsonMessage.create(-1L, "数据库删除失败", "");
            }
        }catch (Exception e){
            e.printStackTrace();
            return JsonMessage.create(-1L, e.getMessage(), "");
        }
    }

    @RequestMapping("/getCurdList")
    @ResponseBody
    public List<PortalBeanCurdListVo> getCurdList(@RequestBody String getStr){
        try {
            PortalBeanCurdListCondition getCondion = JSON.parseObject(getStr,PortalBeanCurdListCondition.class);
            return portalBeanCurdListService.getCurdList(getCondion);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
