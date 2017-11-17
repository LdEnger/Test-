package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalBeanCurdImgListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalBeanCurdImgListCondition;
import cn.com.hiveview.launcherapi.module.portal.service.PortalBeanCurdImgListService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;


/**
 * Created by user on 2017/7/26.
 */
@Controller
@RequestMapping("/portalBeanCurdImgListController")
public class PortalBeanCurdImgListController {

    @Autowired
   private   PortalBeanCurdImgListService portalBeanCurdImgListService;

    @RequestMapping("/getPageList")
    @ResponseBody
    public ScriptPage<PortalBeanCurdImgListVo> getPageList(@RequestBody String  getStr){
        try {
            PortalBeanCurdImgListCondition getCondition = JSON.parseObject(getStr,PortalBeanCurdImgListCondition.class);
             return  this.portalBeanCurdImgListService.getPageList(getCondition);
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
            PortalBeanCurdImgListCondition upCondition = JSON.parseObject(upStr,PortalBeanCurdImgListCondition.class);
            if(null == upCondition){
                return  JsonMessage.create(-1l, "反序列化失败", "");
            }
            if(portalBeanCurdImgListService.update(upCondition) > 0){
                return  JsonMessage.create(0, "", "");
            }else {
                return  JsonMessage.create(-1L, "数据库更新失败", "");
            }
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
            PortalBeanCurdImgListCondition delCondition = JSON.parseObject(delStr,PortalBeanCurdImgListCondition.class);
            if(null == delCondition){
                return JsonMessage.create(-1l, "反序列化失败", "");
            }
            if(this.portalBeanCurdImgListService.delete(delCondition) > 0){
                return  JsonMessage.create(0, "", "");
            }else {
                return  JsonMessage.create(-1L, "数据库删除失败", "");
            }
        }catch (Exception e){
            e.printStackTrace();
            return JsonMessage.create(-1L, e.getMessage(), "");
        }
    }
    @ResponseBody
    @RequestMapping("/getImgList")
    public PortalBeanCurdImgListVo getImgList(@RequestBody String getStr){
        try {
            PortalBeanCurdImgListCondition getCondition = JSON.parseObject(getStr,PortalBeanCurdImgListCondition.class);
            return this.portalBeanCurdImgListService.getImgList(getCondition);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }
    @RequestMapping("/save")
    @ResponseBody
    public Object save(@RequestBody String saStr){
        try {
            if (StringUtils.isBlank(saStr)) {
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalBeanCurdImgListCondition insertBean = JSON.parseObject(saStr, PortalBeanCurdImgListCondition.class);
            if (null == insertBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalBeanCurdImgListService.save(insertBean)>0) {
                return JsonMessage.create(0, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库插入失败", "");
            }
        } catch (Exception ex) {
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }
}
