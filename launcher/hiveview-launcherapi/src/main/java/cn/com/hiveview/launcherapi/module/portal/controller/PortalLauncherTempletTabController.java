package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalLauncherTempletContentCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalLauncherTempletTabCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalTabGroupCondition;
import cn.com.hiveview.launcherapi.module.portal.service.PortalLauncherTempletContentService;
import cn.com.hiveview.launcherapi.module.portal.service.PortalLauncherTempletTabService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2017/7/27.
 */
@Controller
@RequestMapping("/templetTabList")
public class PortalLauncherTempletTabController {
    @Autowired
    private PortalLauncherTempletTabService portalLauncherTempletTabService;
    @RequestMapping(value = { "/getPageList" })
    @ResponseBody
    public Object getPageList(@RequestBody String getStr) {
        try {
            PortalLauncherTempletTabCondition getBean = JSON.parseObject(getStr, PortalLauncherTempletTabCondition.class);
            return JSON.toJSONString(portalLauncherTempletTabService.getPageList(getBean), SerializerFeature.WriteMapNullValue);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @RequestMapping(value = { "/getMinTab" })
    @ResponseBody
    public Object getMinContent(@RequestBody String getStr) {
        try {
            PortalLauncherTempletTabCondition getBean = JSON.parseObject(getStr, PortalLauncherTempletTabCondition.class);
            return portalLauncherTempletTabService.getMinTab(getBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @RequestMapping(value = { "/getMaxTab" })
    @ResponseBody
    public Object getMaxContent(@RequestBody String getStr) {
        try {
            PortalLauncherTempletTabCondition getBean = JSON.parseObject(getStr, PortalLauncherTempletTabCondition.class);
            return portalLauncherTempletTabService.getMaxTab(getBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @RequestMapping(value = { "/getTopSeq" })
    @ResponseBody
    public Object getTopSeq(@RequestBody String getStr) {
        try {
            PortalLauncherTempletTabCondition getBean = JSON.parseObject(getStr, PortalLauncherTempletTabCondition.class);
            return portalLauncherTempletTabService.getTopSeq(getBean);
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
            List<PortalLauncherTempletTabCondition> insertBean = JSON.parseArray(indexStr, PortalLauncherTempletTabCondition.class);
            if (null == insertBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            int result=portalLauncherTempletTabService.add(insertBean);
            if(result==-1){
                return JsonMessage.create(1, "", "");
            }else if (result>0) {
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
            PortalLauncherTempletTabCondition delBean = JSON.parseObject(indexStr, PortalLauncherTempletTabCondition.class);
            if (null == delBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalLauncherTempletTabService.delete(delBean)>0) {
                return JsonMessage.create(0, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库删除失败", "");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }
    @RequestMapping(value = "/moveTop")
    @ResponseBody
    public Object moveTop(@RequestBody String indexStr) {
        try {
            if (StringUtils.isBlank(indexStr)) {
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalLauncherTempletTabCondition topBean = JSON.parseObject(indexStr, PortalLauncherTempletTabCondition.class);
            if (null == topBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalLauncherTempletTabService.updateMoveTop(topBean)>0) {
                return JsonMessage.create(0, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库更新失败", "");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }
    @RequestMapping(value = "/moveUp")
    @ResponseBody
    public Object moveUp(@RequestBody String indexStr) {
        try {
            if (StringUtils.isBlank(indexStr)) {
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalLauncherTempletTabCondition upBean = JSON.parseObject(indexStr, PortalLauncherTempletTabCondition.class);
            if (null == upBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalLauncherTempletTabService.updateMoveUp(upBean)>0) {
                return JsonMessage.create(0, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库更新失败", "");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }
    @RequestMapping(value = "/moveDown")
    @ResponseBody
    public Object moveDown(@RequestBody String indexStr) {
        try {
            if (StringUtils.isBlank(indexStr)) {
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalLauncherTempletTabCondition downBean = JSON.parseObject(indexStr, PortalLauncherTempletTabCondition.class);
            if (null == downBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalLauncherTempletTabService.updateMoveDown(downBean)>0) {
                return JsonMessage.create(0, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库更新失败", "");
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
            PortalLauncherTempletTabCondition updateBean = JSON.parseObject(indexStr, PortalLauncherTempletTabCondition.class);
            if (null == updateBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalLauncherTempletTabService.update(updateBean)>0) {
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
            PortalLauncherTempletTabCondition getBean = JSON.parseObject(indexStr, PortalLauncherTempletTabCondition.class);
            return JSON.toJSONString(portalLauncherTempletTabService.getMaxSeq(getBean), SerializerFeature.WriteMapNullValue);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/preview")
    @ResponseBody
    public Object preview(@RequestBody String indexStr){
        try {
            if(StringUtils.isBlank(indexStr)){
                return  JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalTabGroupCondition condition = JSON.parseObject(indexStr,PortalTabGroupCondition.class);
            if(null == condition){
                return  JsonMessage.create(-1l, "反序列化失败", "");
            }
            return  this.portalLauncherTempletTabService.preview(condition);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
