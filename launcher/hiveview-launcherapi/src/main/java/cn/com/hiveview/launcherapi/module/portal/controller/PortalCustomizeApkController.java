package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalCustomizeApkCondition;
import cn.com.hiveview.launcherapi.module.portal.service.PortalCustomizeApkListService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/7/20.
 */
@Controller
@RequestMapping("/customizeApkList")
public class PortalCustomizeApkController {
    @Autowired
    private PortalCustomizeApkListService portalCustomizeApkList;
    @RequestMapping(value = { "/getPageList" })
    @ResponseBody
    public Object getPageList(@RequestBody String getStr) {
        try {
            PortalCustomizeApkCondition getBean = JSON.parseObject(getStr, PortalCustomizeApkCondition.class);
            return JSON.toJSONString(portalCustomizeApkList.getPageList(getBean), SerializerFeature.WriteMapNullValue);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @RequestMapping(value = { "/getComboboxList" })
    @ResponseBody
    public Object getComboboxList(@RequestBody String getStr) {
        try {
            PortalCustomizeApkCondition getBean = JSON.parseObject(getStr, PortalCustomizeApkCondition.class);
            return JSON.toJSONString(portalCustomizeApkList.getComboboxList(getBean), SerializerFeature.WriteMapNullValue);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @RequestMapping(value = "/insert")
    @ResponseBody
    public Object insert(@RequestBody String indexStr) {
        try {
            if (StringUtils.isBlank(indexStr)) {
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalCustomizeApkCondition insertBean = JSON.parseObject(indexStr, PortalCustomizeApkCondition.class);
            if (null == insertBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalCustomizeApkList.add(insertBean)>0) {
                return JsonMessage.create(0, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库插入失败", "");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }

    @RequestMapping(value = "/del")
    @ResponseBody
    public Object del(@RequestBody String indexStr) {
        try {
            if (StringUtils.isBlank(indexStr)) {
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalCustomizeApkCondition delBean = JSON.parseObject(indexStr, PortalCustomizeApkCondition.class);
            if (null == delBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            Integer result=portalCustomizeApkList.delete(delBean);
            //判断是否被关联，-1为被豆腐块关联
            if(result==-1){
                return JsonMessage.create(1, "", "");
            }else if (result>0) {
                return JsonMessage.create(0, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库删除失败", "");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
            PortalCustomizeApkCondition updateBean = JSON.parseObject(indexStr, PortalCustomizeApkCondition.class);
            if (null == updateBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalCustomizeApkList.update(updateBean)>0) {
                return JsonMessage.create(0, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库更新失败", "");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }
}
