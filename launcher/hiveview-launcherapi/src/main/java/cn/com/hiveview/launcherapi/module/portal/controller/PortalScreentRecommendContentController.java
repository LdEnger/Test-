package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalScreentRecommendContent;
import cn.com.hiveview.entity.module.portal.PortalSysAppIconsList;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalLauncherTempletContentCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalScreentRecommendContentCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalSysAppIconsListCondition;
import cn.com.hiveview.launcherapi.module.portal.service.PortalScreentRecommendContentService;
import cn.com.hiveview.launcherapi.module.portal.service.PortalSysAppIconsListService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Interner;
import com.google.common.collect.Interners;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2017/7/10.
 */
@Controller
@RequestMapping("/screentRecommendContent")
public class PortalScreentRecommendContentController {

    @Autowired
    private PortalScreentRecommendContentService portalScreentRecommendContent;
    @RequestMapping(value = { "/getPageList" })
    @ResponseBody
    public ScriptPage<PortalScreentRecommendContent> getPageList(@RequestBody String getStr) {
        try {
            PortalScreentRecommendContentCondition getBean = JSON.parseObject(getStr, PortalScreentRecommendContentCondition.class);
            return portalScreentRecommendContent.getPageList(getBean);
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
            PortalScreentRecommendContentCondition insertBean = JSON.parseObject(indexStr, PortalScreentRecommendContentCondition.class);
            if (null == insertBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            //去重
            String syncStr =insertBean.getContentType()+insertBean.getApkName()+insertBean.getContentId();
            Interner<String> pool = Interners.newWeakInterner();
            synchronized (pool.intern(syncStr)) {
                Integer resultInt = portalScreentRecommendContent.add(insertBean);
                if (resultInt>0) {
                    return JsonMessage.create(0, "", "");
                } else if(resultInt == -2){
                    return JsonMessage.create(-2, "此数据已经存在！", "");
                }else {
                    return JsonMessage.create(-1L, "数据库插入失败", "");
                }
            }
        } catch (Exception ex) {
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
            PortalScreentRecommendContentCondition delBean = JSON.parseObject(indexStr,PortalScreentRecommendContentCondition.class);
            if (null == delBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalScreentRecommendContent.delete(delBean)>0) {
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
            PortalScreentRecommendContentCondition updateBean = JSON.parseObject(indexStr, PortalScreentRecommendContentCondition.class);
            if (null == updateBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalScreentRecommendContent.update(updateBean)>0) {
                return JsonMessage.create(0, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库更新失败", "");
            }
        } catch (Exception ex) {
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }

    @RequestMapping(value = { "/getMinContent" })
    @ResponseBody
    public Object getMinContent(@RequestBody String getStr) {
        try {
            PortalScreentRecommendContentCondition getBean = JSON.parseObject(getStr, PortalScreentRecommendContentCondition.class);
            return portalScreentRecommendContent.getMinContent(getBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @RequestMapping(value = { "/getMaxContent" })
    @ResponseBody
    public Object getMaxContent(@RequestBody String getStr) {
        try {
            PortalScreentRecommendContentCondition getBean = JSON.parseObject(getStr, PortalScreentRecommendContentCondition.class);
            return portalScreentRecommendContent.getMaxContent(getBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @RequestMapping(value = "/getMaxSeq")
    @ResponseBody
    public Object getMaxSeq(@RequestBody String indexStr) {
        try {
            PortalScreentRecommendContentCondition getBean = JSON.parseObject(indexStr, PortalScreentRecommendContentCondition.class);
            return JSON.toJSONString(portalScreentRecommendContent.getMaxSeq(getBean), SerializerFeature.WriteMapNullValue);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @RequestMapping(value = "/getMinSeq")
    @ResponseBody
    public Object getMinSeq(@RequestBody String indexStr) {
        try {
            PortalScreentRecommendContentCondition getBean = JSON.parseObject(indexStr, PortalScreentRecommendContentCondition.class);
            return JSON.toJSONString(portalScreentRecommendContent.getMinSeq(getBean), SerializerFeature.WriteMapNullValue);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
