package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalLauncherTempletCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalLauncherTempletContentCondition;
import cn.com.hiveview.launcherapi.module.portal.service.PortalLauncherTempletContentService;
import cn.com.hiveview.launcherapi.module.portal.service.PortalLauncherTempletService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/7/27.
 */
@Controller
@RequestMapping("/templetContentList")
public class PortalLauncherTempletContentController {
    @Autowired
    private PortalLauncherTempletContentService portalLauncherTempletContentService;
    @RequestMapping(value = { "/getPageList" })
    @ResponseBody
    public Object getPageList(@RequestBody String getStr) {
        try {
            PortalLauncherTempletContentCondition getBean = JSON.parseObject(getStr, PortalLauncherTempletContentCondition.class);
            return JSON.toJSONString(portalLauncherTempletContentService.getPageList(getBean), SerializerFeature.WriteMapNullValue);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @RequestMapping(value = { "/getMinContent" })
    @ResponseBody
    public Object getMinContent(@RequestBody String getStr) {
        try {
            PortalLauncherTempletContentCondition getBean = JSON.parseObject(getStr, PortalLauncherTempletContentCondition.class);
            return portalLauncherTempletContentService.getMinContent(getBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @RequestMapping(value = { "/getMaxContent" })
    @ResponseBody
    public Object getMaxContent(@RequestBody String getStr) {
        try {
            PortalLauncherTempletContentCondition getBean = JSON.parseObject(getStr, PortalLauncherTempletContentCondition.class);
            return portalLauncherTempletContentService.getMaxContent(getBean);
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
            PortalLauncherTempletContentCondition insertBean = JSON.parseObject(indexStr, PortalLauncherTempletContentCondition.class);
            if (null == insertBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalLauncherTempletContentService.add(insertBean)>0) {
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
            PortalLauncherTempletContentCondition delBean = JSON.parseObject(indexStr, PortalLauncherTempletContentCondition.class);
            if (null == delBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalLauncherTempletContentService.delete(delBean)>0) {
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
            PortalLauncherTempletContentCondition updateBean = JSON.parseObject(indexStr, PortalLauncherTempletContentCondition.class);
            if (null == updateBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalLauncherTempletContentService.update(updateBean)>0) {
                return JsonMessage.create(0, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库更新失败", "");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }
    @RequestMapping(value = "/getMaxSeq")
    @ResponseBody
    public Object getMaxSeq(@RequestBody String indexStr) {
        try {
            PortalLauncherTempletContentCondition getBean = JSON.parseObject(indexStr, PortalLauncherTempletContentCondition.class);
            return JSON.toJSONString(portalLauncherTempletContentService.getMaxSeq(getBean), SerializerFeature.WriteMapNullValue);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
