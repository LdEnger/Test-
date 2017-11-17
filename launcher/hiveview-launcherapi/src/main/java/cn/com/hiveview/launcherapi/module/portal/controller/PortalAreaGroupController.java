package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalAreaGroupList;
import cn.com.hiveview.entity.module.portal.PortalMacAreaList;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalAreaGroupCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalMacAreaCondition;
import cn.com.hiveview.launcherapi.module.portal.service.PortalAreaGroupListService;
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
@RequestMapping("/AreaGroupList")
public class PortalAreaGroupController {
    @Autowired
    private PortalAreaGroupListService portalAreaGroupList;

    @RequestMapping(value = { "/getPageList" })
    @ResponseBody
    public ScriptPage<PortalAreaGroupList> getPageList(@RequestBody String getStr) {
        try {
            PortalAreaGroupCondition getBean = JSON.parseObject(getStr, PortalAreaGroupCondition.class);
            return portalAreaGroupList.getPageList(getBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @RequestMapping(value = { "/getList" })
    @ResponseBody
    public List<PortalAreaGroupList> getList(@RequestBody String getStr) {
        try {
            PortalAreaGroupCondition getBean = JSON.parseObject(getStr, PortalAreaGroupCondition.class);
            return portalAreaGroupList.getList(getBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = { "/getAreaGroupByCode" })
    @ResponseBody
    public PortalAreaGroupList getAreaGroupByCode(@RequestBody String getStr){
        try {
            PortalAreaGroupCondition getBean = JSON.parseObject(getStr, PortalAreaGroupCondition.class);
            return portalAreaGroupList.getAreaGroupByCode(getBean);
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
            PortalAreaGroupCondition insertBean = JSON.parseObject(indexStr, PortalAreaGroupCondition.class);
            if (null == insertBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            Integer result=portalAreaGroupList.add(insertBean);
            if (result==-1) {
                return JsonMessage.create(1, "", "");
            } else if (result==-2) {
                return JsonMessage.create(2, "", "");
            } else if (result>0) {
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
            PortalAreaGroupCondition delBean = JSON.parseObject(indexStr, PortalAreaGroupCondition.class);
            if (null == delBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            Integer result=portalAreaGroupList.delete(delBean);
            if (result==-1) {
                return JsonMessage.create(1, "", "");
            } else if (result>0) {
                return JsonMessage.create(0, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库删除失败", "");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }


    @RequestMapping(value = "/delByAreaId")
    @ResponseBody
    public Object delByAreaId(@RequestBody String indexStr) {
        try {
            if (StringUtils.isBlank(indexStr)) {
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalAreaGroupCondition delBean = JSON.parseObject(indexStr, PortalAreaGroupCondition.class);
            if (null == delBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalAreaGroupList.delete(delBean)>0) {
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
            PortalAreaGroupCondition updateBean = JSON.parseObject(indexStr, PortalAreaGroupCondition.class);
            if (null == updateBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            Integer result=portalAreaGroupList.update(updateBean);
            if (result==-2) {
                return JsonMessage.create(2, "", "");
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
}
