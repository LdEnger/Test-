package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.entity.module.portal.PortalSysAppIconsList;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalSysAppIconsListCondition;
import cn.com.hiveview.launcherapi.module.portal.service.PortalSysAppIconsListService;
import cn.com.hiveview.launcherapi.module.portal.service.RedisService;
import cn.com.hiveview.launcherapi.module.portal.util.DBHelper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Random;

/**
 * Created by admin on 2017/7/10.
 */
@Controller
@RequestMapping("/sysAppIconsList")
public class PortalSysAppIconsListController {
    @Autowired
    private PortalSysAppIconsListService portalSysAppIconsList;
    @Autowired
    private RedisService redisService;
    @Autowired
    private DBHelper holder;
    private static Integer lockArray [] = {1,2,3,4,5,6,7,8,9,10};
    @RequestMapping(value = { "/getPageList" })
    @ResponseBody
    public Object getPageList(@RequestBody String getStr) {
        try {
            PortalSysAppIconsListCondition getBean = JSON.parseObject(getStr, PortalSysAppIconsListCondition.class);
            return JSON.toJSONString(portalSysAppIconsList.getPageList(getBean), SerializerFeature.WriteMapNullValue);
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
            PortalSysAppIconsListCondition insertBean = JSON.parseObject(indexStr, PortalSysAppIconsListCondition.class);
            if (null == insertBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            Integer result=portalSysAppIconsList.add(insertBean);
            if (result== -1) {
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
            PortalSysAppIconsListCondition delBean = JSON.parseObject(indexStr, PortalSysAppIconsListCondition.class);
            if (null == delBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalSysAppIconsList.delete(delBean)>0) {
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
            PortalSysAppIconsListCondition updateBean = JSON.parseObject(indexStr, PortalSysAppIconsListCondition.class);
            if (null == updateBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            Integer result=portalSysAppIconsList.update(updateBean);
            //result==-1是更新时有重复数据
            if (result==-1) {
                return JsonMessage.create(1, "", "");
            } else if (result>0) {
                return JsonMessage.create(0, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库更新失败", "");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }

    @RequestMapping(value = { "/getList/{mac}/{sn}/{model}/{rowVersion}/{version}" })
    @ResponseBody
    public Object getList(@PathVariable String mac, @PathVariable String sn, @PathVariable String model, @PathVariable String rowVersion, @PathVariable String version) {
        try {
            List<PortalSysAppIconsList> result =null;
            String key = "sysAppIconsList_getList";
            String val = redisService.get(key);
            if(val != null){
                result  = JSON.parseObject(val, List.class);
            }else{
                Integer index = new Random().nextInt(lockArray.length);
                synchronized (lockArray[index]) {
                    if (redisService.exists(key)){
                        val = redisService.get(key);
                        result  = JSON.parseObject(val, List.class);
                    }else{
                        PortalSysAppIconsListCondition condition = new PortalSysAppIconsListCondition();
                        holder.setDBType(DBHelper.DB_TYPE_R);
                        result = this.portalSysAppIconsList.getList(condition);
                        holder.clearDBType();
                    }
                }
            }
            return JsonMessage.create(0, "",result);
        } catch (Exception ex) {
            ex.printStackTrace();
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }
}
