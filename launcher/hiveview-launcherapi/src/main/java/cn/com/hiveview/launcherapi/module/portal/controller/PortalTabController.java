package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalTabCondition;
import cn.com.hiveview.launcherapi.module.portal.service.PortalTabService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xiach on 2017/10/9.
 */
@Controller
@RequestMapping("portalTab")
public class PortalTabController {

    @Autowired
    private PortalTabService portalTabService;

    @RequestMapping(value = { "/getPageList" })
    @ResponseBody
    public Object getPageList(@RequestBody String getStr) {
        try {
            PortalTabCondition getBean = JSON.parseObject(getStr, PortalTabCondition.class);
            return JSON.toJSONString(portalTabService.getPageList(getBean), SerializerFeature.WriteMapNullValue);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public Object insert(@RequestBody String indexStr) {
        try {
            if (StringUtils.isBlank(indexStr)) {
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalTabCondition insertBean = JSON.parseObject(indexStr, PortalTabCondition.class);
            if (null == insertBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            Integer result=portalTabService.add(insertBean);
            if (result== -1) {
                return JsonMessage.create(1, "", "");
            }else if (result>0) {
                return JsonMessage.create(0, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库插入失败", "");
            }
        } catch (Exception ex) {
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object del(@RequestBody String indexStr) {
        try {
            if (StringUtils.isBlank(indexStr)) {
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalTabCondition delBean = JSON.parseObject(indexStr, PortalTabCondition.class);
            if (null == delBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            Integer deleteCount = portalTabService.delete(delBean);
            if (deleteCount>0) {
                return JsonMessage.create(0, "", "");
            }else if(deleteCount==-2){
                return JsonMessage.create(-2L, "launcher有关联数据，不可删除！", "");
            }else if(deleteCount==-3){
                return JsonMessage.create(-3L, "推荐位有关联数据，不可删除！", "");
            }else{
                return JsonMessage.create(-1L, "数据库删除失败！", "");
            }
        } catch (Exception ex) {
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@RequestBody String indexStr) {
        try {
            if (StringUtils.isBlank(indexStr)) {
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalTabCondition updateBean = JSON.parseObject(indexStr, PortalTabCondition.class);
            if (null == updateBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            Integer updateCount = portalTabService.update(updateBean);
            if (updateCount>0) {
                return JsonMessage.create(0, "", "");
            }  else if(updateCount==-2){
                return JsonMessage.create(-2L, "launcher有关联数据，不可下线！", "");
            }else if(updateCount==-3){
                return JsonMessage.create(-3L, "推荐位有关联数据，不可下线！", "");
            }else{
                return JsonMessage.create(-1L, "数据库更新失败", "");
            }
        } catch (Exception ex) {
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }


}

