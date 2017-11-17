package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalScreenBigPicsListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalScreenBigPicsListCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.UnbundlingCondition;
import cn.com.hiveview.launcherapi.module.portal.service.PortalScreenBigPicsListService;
import cn.com.hiveview.launcherapi.module.portal.service.UnbundlingService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2017/7/11.
 */
@Controller
@RequestMapping("/screenBigPicsList")
public class PortalScreenBigPicsListController {


    @Autowired
    private PortalScreenBigPicsListService portalScreenBigPicsListService;

    @RequestMapping(value = { "/getList" })
    @ResponseBody
    public ScriptPage<PortalScreenBigPicsListVo> getPageList(@RequestBody String getStr) {
        try {
            PortalScreenBigPicsListCondition getBean = JSON.parseObject(getStr, PortalScreenBigPicsListCondition.class);
            return portalScreenBigPicsListService.getList(getBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @RequestMapping(value = "/save")
    @ResponseBody
    public Object save(@RequestBody String indexStr) {
        try {
            if (StringUtils.isBlank(indexStr)) {
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalScreenBigPicsListCondition insertBean = JSON.parseObject(indexStr, PortalScreenBigPicsListCondition.class);
            if (null == insertBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalScreenBigPicsListService.save(insertBean)>0) {
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
            PortalScreenBigPicsListCondition delBean = JSON.parseObject(indexStr, PortalScreenBigPicsListCondition.class);
            if (null == delBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            Integer result = portalScreenBigPicsListService.delete(delBean);
            if(result == -1){
                return JsonMessage.create(1, "", "");
            }else if (result > 0) {
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
            PortalScreenBigPicsListCondition updateBean = JSON.parseObject(indexStr, PortalScreenBigPicsListCondition.class);
            if (null == updateBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalScreenBigPicsListService.update(updateBean)>0) {
                return JsonMessage.create(0, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库更新失败", "");
            }
        } catch (Exception ex) {
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }


    @RequestMapping(value = "/updateBig")
    @ResponseBody
    public Object updateBig(@RequestBody String indexStr) {
        try {
            if (StringUtils.isBlank(indexStr)) {
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalScreenBigPicsListCondition updateBean = JSON.parseObject(indexStr, PortalScreenBigPicsListCondition.class);
            if (null == updateBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalScreenBigPicsListService.updateBig(updateBean)>0) {
                return JsonMessage.create(0, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库更新失败", "");
            }
        } catch (Exception ex) {
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }



}
