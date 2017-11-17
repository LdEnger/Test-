package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalJumpInstructionCondition;
import cn.com.hiveview.launcherapi.module.portal.service.PortalJumpInstructionService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xiach on 2017/10/8.
 */
@Controller
@RequestMapping("portalJumpInstruction")
public class PortalJumpInstructionController {

    @Autowired
    private PortalJumpInstructionService portalJumpInstructionService;

    @RequestMapping(value = { "/getPageList" })
    @ResponseBody
    public Object getPageList(@RequestBody String getStr) {
        try {
            PortalJumpInstructionCondition getBean = JSON.parseObject(getStr, PortalJumpInstructionCondition.class);
            return JSON.toJSONString(portalJumpInstructionService.getPageList(getBean), SerializerFeature.WriteMapNullValue);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = { "/getOne" })
    @ResponseBody
    public Object getOne(@RequestBody String getStr) {
        try {

            PortalJumpInstructionCondition getBean = JSON.parseObject(getStr, PortalJumpInstructionCondition.class);
            return JSON.toJSONString(portalJumpInstructionService.getPageList(getBean), SerializerFeature.WriteMapNullValue);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = { "/getActionById" })
    @ResponseBody
    public Object getActionById(@RequestBody String getStr) {
        try {

            PortalJumpInstructionCondition getBean = JSON.parseObject(getStr, PortalJumpInstructionCondition.class);
            return JSON.toJSONString(portalJumpInstructionService.getActionById(getBean), SerializerFeature.WriteMapNullValue);
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
            PortalJumpInstructionCondition insertBean = JSON.parseObject(indexStr, PortalJumpInstructionCondition.class);
            if (null == insertBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            Integer result=portalJumpInstructionService.add(insertBean);
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
            PortalJumpInstructionCondition delBean = JSON.parseObject(indexStr, PortalJumpInstructionCondition.class);
            if (null == delBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            Integer deleteCount = portalJumpInstructionService.delete(delBean);
            if (deleteCount>0) {
                return JsonMessage.create(0, "", "");
            } else if(deleteCount==-2){
                return JsonMessage.create(2, "有关联", "");
            }else if(deleteCount==-3){
                return JsonMessage.create(3, "备份有关联", "");
            }else {
                return JsonMessage.create(-1L, "数据库删除失败", "");
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
            PortalJumpInstructionCondition updateBean = JSON.parseObject(indexStr, PortalJumpInstructionCondition.class);
            if (null == updateBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalJumpInstructionService.update(updateBean)>0) {
                return JsonMessage.create(0, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库更新失败", "");
            }
        } catch (Exception ex) {
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }
}
