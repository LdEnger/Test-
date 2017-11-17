package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalScreentRecommendContentCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalVipLogoCondition;
import cn.com.hiveview.launcherapi.module.portal.service.PortalVipLogoService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xiach on 2017/10/6.
 */
@Controller
@RequestMapping("portalVipLogo")
public class PortalVipLogoController {

    @Autowired
    private PortalVipLogoService portalVipLogoService;

    @RequestMapping(value = { "/getPageList" })
    @ResponseBody
    public Object getPageList(@RequestBody String getStr) {
        try {
            PortalVipLogoCondition getBean = JSON.parseObject(getStr, PortalVipLogoCondition.class);
            return JSON.toJSONString(portalVipLogoService.getPageList(getBean), SerializerFeature.WriteMapNullValue);
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
            PortalVipLogoCondition insertBean = JSON.parseObject(indexStr, PortalVipLogoCondition.class);
            if (null == insertBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            Integer result=portalVipLogoService.add(insertBean);
            if (result==-2) {
                return JsonMessage.create(2, "已存在logoId", "");
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
            PortalVipLogoCondition delBean = JSON.parseObject(indexStr, PortalVipLogoCondition.class);
            if (null == delBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalVipLogoService.delete(delBean)>0) {
                return JsonMessage.create(0, "", "");
            } else {
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
            PortalVipLogoCondition updateBean = JSON.parseObject(indexStr, PortalVipLogoCondition.class);
            if (null == updateBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalVipLogoService.update(updateBean)>0) {
                return JsonMessage.create(0, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库更新失败", "");
            }
        } catch (Exception ex) {
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }

    @RequestMapping(value = "/getMaxSeq")
    @ResponseBody
    public Object getMaxSeq() {
        try {
            return JSON.toJSONString(portalVipLogoService.getMaxSeq(), SerializerFeature.WriteMapNullValue);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @RequestMapping(value = "/getMinSeq")
    @ResponseBody
    public Object getMinSeq() {
        try {
            return JSON.toJSONString(portalVipLogoService.getMinSeq(), SerializerFeature.WriteMapNullValue);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = { "/getMinContent" })
    @ResponseBody
    public Object getMinContent(@RequestBody String getStr) {
        try {
            PortalVipLogoCondition getBean = JSON.parseObject(getStr, PortalVipLogoCondition.class);
            return portalVipLogoService.getMinContent(getBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @RequestMapping(value = { "/getMaxContent" })
    @ResponseBody
    public Object getMaxContent(@RequestBody String getStr) {
        try {
            PortalVipLogoCondition getBean = JSON.parseObject(getStr, PortalVipLogoCondition.class);
            return portalVipLogoService.getMaxContent(getBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }




}
