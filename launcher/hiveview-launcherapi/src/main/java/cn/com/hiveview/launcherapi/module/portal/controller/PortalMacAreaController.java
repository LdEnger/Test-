package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalMacAreaList;
import cn.com.hiveview.entity.module.portal.PortalSysAppIconsList;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalMacAreaCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalSysAppIconsListCondition;
import cn.com.hiveview.launcherapi.module.portal.service.PortalMacAreaListService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2017/7/14.
 */
@Controller
@RequestMapping("/MacAreaList")
public class PortalMacAreaController {
    @Autowired
    private PortalMacAreaListService portalMacAreaList;

    @RequestMapping(value = { "/getPageList" })
    @ResponseBody
    public ScriptPage<PortalMacAreaList> getPageList(@RequestBody String getStr) {
        try {
            //System.out.println("apiService"+getStr);
            PortalMacAreaCondition getBean = JSON.parseObject(getStr, PortalMacAreaCondition.class);
            return portalMacAreaList.getPageList(getBean);
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
            PortalMacAreaCondition insertBean = JSON.parseObject(indexStr, PortalMacAreaCondition.class);
            if (null == insertBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            Integer result=portalMacAreaList.add(insertBean);
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
            PortalMacAreaCondition delBean = JSON.parseObject(indexStr, PortalMacAreaCondition.class);
            if (null == delBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalMacAreaList.delete(delBean)>0) {
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
            PortalMacAreaCondition updateBean = JSON.parseObject(indexStr, PortalMacAreaCondition.class);
            if (null == updateBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            Integer result=portalMacAreaList.update(updateBean);
            if(result==-1) {
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

    @RequestMapping(value = { "/getList" })
    @ResponseBody
    public List<PortalMacAreaList> getList(@RequestBody String getStr) {
        try {
            //System.out.println("apiService"+getStr);
            PortalMacAreaCondition getBean = JSON.parseObject(getStr, PortalMacAreaCondition.class);
            List<PortalMacAreaList> rows = portalMacAreaList.getList(getBean);
            return rows.size()==0?null:rows;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
