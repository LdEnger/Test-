package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.CustomRecomLayoutVo;
import cn.com.hiveview.entity.module.portal.CustomRecomTempleteListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.CustomRecomLayoutCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.CustomRecomTempleteCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalTabGroupCondition;
import cn.com.hiveview.launcherapi.module.portal.service.CustomRecomLayoutService;
import cn.com.hiveview.launcherapi.module.portal.service.CustomRecomTempleteListService;
import cn.com.hiveview.launcherapi.module.portal.service.PortalCustomRecomContentService;
import cn.com.hiveview.launcherapi.module.portal.service.PortalTabGroupService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by user on 2017/7/20.
 */
@Controller
@RequestMapping("/customRecomTempleteList")
public class CustomRecomTempleteController {

    @Autowired
    private CustomRecomTempleteListService customRecomTempleteListService;
    @Autowired
    private PortalCustomRecomContentService portalCustomRecomContentService;
    @Autowired
    private PortalTabGroupService portalTabGroupService;
    @Autowired
    private CustomRecomLayoutService customRecomLayoutService;
    @RequestMapping("/getPageList")
    @ResponseBody
    public ScriptPage<CustomRecomTempleteListVo> getPageList(@RequestBody String getStr){
        try{
            CustomRecomTempleteCondition getCondition = JSON.parseObject(getStr,CustomRecomTempleteCondition.class);
            return  this.customRecomTempleteListService.getList(getCondition);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/getRolCow")
    @ResponseBody
    public List<CustomRecomLayoutVo> getRolCow(@RequestBody String getStr){
        try{
            CustomRecomLayoutCondition getCondition = JSON.parseObject(getStr,CustomRecomLayoutCondition.class);
            return  this.customRecomLayoutService.getList(getCondition);
        }catch (Exception e){
            e.printStackTrace();
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
            CustomRecomTempleteCondition getCodition = JSON.parseObject(indexStr,CustomRecomTempleteCondition.class);
            if(getCodition == null){
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if(customRecomTempleteListService.save(getCodition) > 0){
                return  JsonMessage.create(0, "", "success");
            }else {
                return JsonMessage.create(-1L, "数据库插入失败", "");
            }
        }catch (Exception e){
            e.printStackTrace();
            return JsonMessage.create(-1L, e.getMessage(), "");
        }
    }
    @RequestMapping("/saveTemplete")
    @ResponseBody
    public Object saveTemplete(@RequestBody String indexStr ){
        try {
            if(StringUtils.isBlank(indexStr)){
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            CustomRecomTempleteCondition getCodition = JSON.parseObject(indexStr,CustomRecomTempleteCondition.class);
            if(getCodition == null){
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if(customRecomTempleteListService.saveTemplete(getCodition) > 0){
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
            CustomRecomTempleteCondition updateCodition = JSON.parseObject(indexStr,CustomRecomTempleteCondition.class);
            if(null == updateCodition){
                return  JsonMessage.create(-1l, "反序列化失败", "");
            }
            if(customRecomTempleteListService.update(updateCodition) > 0){
                return  JsonMessage.create(0, "", "success");
            }else {
                return  JsonMessage.create(-1L, "数据库更新失败", "");
            }
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }
    @RequestMapping("/updateTempleteLayout")
    @ResponseBody
    public Object updateTempleteLayout(@RequestBody String indexStr){
        try {
            if(StringUtils.isBlank(indexStr)){
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            CustomRecomTempleteCondition updateCodition = JSON.parseObject(indexStr,CustomRecomTempleteCondition.class);
            if(null == updateCodition){
                return  JsonMessage.create(-1l, "反序列化失败", "");
            }
            if(customRecomTempleteListService.updateTempleteLayout(updateCodition) > 0){
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
            CustomRecomTempleteCondition deleteCondition = JSON.parseObject(indexStr,CustomRecomTempleteCondition.class);
            if(null == deleteCondition){
                return  JsonMessage.create(-1l, "反序列化失败", "");
            }
            if(customRecomTempleteListService.delete(deleteCondition) > 0){
                return  JsonMessage.create(0, "", "success");
            }else {
                return  JsonMessage.create(-1L, "数据库删除失败", "");
            }
        }catch (Exception e){
            e.printStackTrace();
            return JsonMessage.create(-1L, e.getMessage(), "");
        }
    }

    @RequestMapping("/getCustomRecomTemplete")
    @ResponseBody
    public CustomRecomTempleteListVo getCustomRecomTemplete(@RequestBody String indexStr){
        try {

            CustomRecomTempleteCondition condition = JSON.parseObject(indexStr,CustomRecomTempleteCondition.class);
            return customRecomTempleteListService.get(condition);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/getTempleteById")
    @ResponseBody
    public Object getTempleteById(@RequestBody String indexStr){
        try {
            if(StringUtils.isBlank(indexStr)){
                return  JsonMessage.create(-1l, "参数不能为空", "");
            }
            CustomRecomTempleteCondition condition = JSON.parseObject(indexStr,CustomRecomTempleteCondition.class);
            if(null == condition){
                return  JsonMessage.create(-1l, "反序列化失败", "");
            }
            return JsonMessage.create(0, "success",  this.customRecomTempleteListService.getTempleteById(condition.getTempleteId()));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/getLayout")
    @ResponseBody
    public Object getLayout(@RequestBody String indexStr){
        try {
            if(StringUtils.isBlank(indexStr)){
                return  JsonMessage.create(-1l, "参数不能为空", "");
            }
            CustomRecomTempleteCondition condition = JSON.parseObject(indexStr,CustomRecomTempleteCondition.class);
            if(null == condition){
                return  JsonMessage.create(-1l, "反序列化失败", "");
            }
            return  this.customRecomTempleteListService.getTempleteJSONById(condition.getTempleteId());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/setFatherSelect")
    @ResponseBody
    public Object setFatherSelect(@RequestBody String indexStr){
        try {
            if(StringUtils.isBlank(indexStr)){
                return  JsonMessage.create(-1l, "参数不能为空", "");
            }
            CustomRecomTempleteCondition condition = JSON.parseObject(indexStr,CustomRecomTempleteCondition.class);
            if(null == condition){
                return  JsonMessage.create(-1l, "反序列化失败", "");
            }
            return  this.customRecomTempleteListService.setTemplete(condition.getTempleteLeve());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/getContent")
    @ResponseBody
    public Object getContent(@RequestBody String indexStr){
        try {
            if(StringUtils.isBlank(indexStr)){
                return  JsonMessage.create(-1l, "参数不能为空", "");
            }
            CustomRecomTempleteCondition condition = JSON.parseObject(indexStr,CustomRecomTempleteCondition.class);
            if(null == condition){
                return  JsonMessage.create(-1l, "反序列化失败", "");
            }
            return  this.customRecomTempleteListService.getContent(condition);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/updateIsEffective")
    @ResponseBody
    public Object updateIsEffective(@RequestBody String indexStr){
        try {
            if(StringUtils.isBlank(indexStr)){
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            CustomRecomTempleteCondition updateCodition = JSON.parseObject(indexStr,CustomRecomTempleteCondition.class);
            if(null == updateCodition){
                return  JsonMessage.create(-1l, "反序列化失败", "");
            }
            if(portalCustomRecomContentService.getTabCount(updateCodition) > 0){
                PortalTabGroupCondition portalTabGroupCondition = new PortalTabGroupCondition();
                portalTabGroupCondition.setBelongGroupId(updateCodition.getTempleteId());
                portalTabGroupCondition.setGroupType(1);
                Integer  a =portalTabGroupService.delete(portalTabGroupCondition);
                customRecomTempleteListService.updateIsEffective(updateCodition);
                return  JsonMessage.create(0, "", "success");
            }else{
            if(customRecomTempleteListService.updateIsEffective(updateCodition) > 0){
                return  JsonMessage.create(0, "", "success");
            }else {
                return  JsonMessage.create(-1L, "数据库更新失败", "");
            }}
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }

    @RequestMapping("/selectFatherId")
    @ResponseBody
    public Object selectFatherId(@RequestBody String indexStr){
        try {
            if(StringUtils.isBlank(indexStr)){
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            CustomRecomTempleteCondition selCodition = JSON.parseObject(indexStr,CustomRecomTempleteCondition.class);
            if(null == selCodition){
                return  JsonMessage.create(-1l, "反序列化失败", "");
            }
            return customRecomTempleteListService.selectFatherId(selCodition);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }
    @RequestMapping("/selectByTempleteId")
    @ResponseBody
    public Object selectByTempleteId(@RequestBody String indexStr){
        try {
            if(StringUtils.isBlank(indexStr)){
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            CustomRecomTempleteCondition selCodition = JSON.parseObject(indexStr,CustomRecomTempleteCondition.class);
            if(null == selCodition){
                return  JsonMessage.create(-1l, "反序列化失败", "");
            }
            return   this.customRecomTempleteListService.getByTempleteId(selCodition);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }
    @RequestMapping("/getFirstList")
    @ResponseBody
    public Object getFirstList(@RequestBody String indexStr){
        try {
            if(StringUtils.isBlank(indexStr)){
                return  JsonMessage.create(-1l, "参数不能为空", "");
            }
            CustomRecomTempleteCondition condition = JSON.parseObject(indexStr,CustomRecomTempleteCondition.class);
            if(null == condition){
                return  JsonMessage.create(-1l, "反序列化失败", "");
            }
            return  this.customRecomTempleteListService.getFirstList(condition);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
