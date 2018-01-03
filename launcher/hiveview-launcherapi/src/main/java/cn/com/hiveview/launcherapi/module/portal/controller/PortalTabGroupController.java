package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.entity.module.portal.PortalTabPageEntity;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalTabGroupCondition;
import cn.com.hiveview.launcherapi.module.portal.service.PortalTabGroupService;
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

/**
 * Created by xiach on 2017/10/10.
 */
@Controller
@RequestMapping("portalTabGroup")
public class PortalTabGroupController {


    @Autowired
    private PortalTabGroupService portalTabGroupService;
    @Autowired
    private DBHelper holder;
    @RequestMapping(value = { "/getPageList" })
    @ResponseBody
    public Object getPageList(@RequestBody String getStr) {
        try {
            PortalTabGroupCondition getBean = JSON.parseObject(getStr, PortalTabGroupCondition.class);
            return JSON.toJSONString(portalTabGroupService.getPageList(getBean), SerializerFeature.WriteMapNullValue);
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
            List<PortalTabGroupCondition> insertBean = JSON.parseArray(indexStr, PortalTabGroupCondition.class);
            if (0 == insertBean.size()) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            Integer result=portalTabGroupService.add(insertBean);
            if (result== -1) {
                return JsonMessage.create(1, "", "");
            }else if(result==-2){
                return JsonMessage.create(2, "选择数据不符合要求", "");
            }else if(result==-3){
                return JsonMessage.create(3, "tab中已经存在一个有视频的group", "");
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
            PortalTabGroupCondition delBean = JSON.parseObject(indexStr, PortalTabGroupCondition.class);
            if (null == delBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalTabGroupService.delete(delBean)>0) {
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
            PortalTabGroupCondition updateBean = JSON.parseObject(indexStr, PortalTabGroupCondition.class);
            if (null == updateBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalTabGroupService.update(updateBean)>0) {
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
            PortalTabGroupCondition getBean = JSON.parseObject(getStr, PortalTabGroupCondition.class);
            return portalTabGroupService.getMinContent(getBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @RequestMapping(value = { "/getMaxContent" })
    @ResponseBody
    public Object getMaxContent(@RequestBody String getStr) {
        try {
            PortalTabGroupCondition getBean = JSON.parseObject(getStr, PortalTabGroupCondition.class);
            return portalTabGroupService.getMaxContent(getBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/getMaxSeq")
    @ResponseBody
    public Object getMaxSeq(@RequestBody String getStr) {
        try {
            PortalTabGroupCondition getBean = JSON.parseObject(getStr, PortalTabGroupCondition.class);
            return JSON.toJSONString(portalTabGroupService.getMaxSeq(getBean), SerializerFeature.WriteMapNullValue);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @RequestMapping(value = { "/getList/{tabId}/{page}/{size}/{version}" })
    @ResponseBody
    public Object getList(@PathVariable Integer tabId, @PathVariable Integer page, @PathVariable Integer size, @PathVariable String version) {
        try {
            PortalTabGroupCondition condition = new PortalTabGroupCondition();
            condition.setVersion(version);
            condition.setBelongTabId(tabId);
            condition.setPage(page);
            condition.setRows(size);
            condition.setPageIndex(condition.getPage());
            condition.setPageSize(condition.getRows());
            holder.setDBType(DBHelper.DB_TYPE_R);
            List<PortalTabPageEntity>  result = portalTabGroupService.getList(condition);
            holder.clearDBType();
            return JsonMessage.create(0, "",result);

        } catch (Exception ex) {
            ex.printStackTrace();
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }
    @RequestMapping(value = "/getCount")
    @ResponseBody
    public Object getCount(@RequestBody String getStr) {
        try {
            PortalTabGroupCondition getBean = JSON.parseObject(getStr, PortalTabGroupCondition.class);
            return JSON.toJSONString(portalTabGroupService.getCount(getBean), SerializerFeature.WriteMapNullValue);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/addOne")
    @ResponseBody
    public Object insertOne(@RequestBody String indexStr) {
        try {
            if (StringUtils.isBlank(indexStr)) {
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalTabGroupCondition insertBean = JSON.parseObject(indexStr, PortalTabGroupCondition.class);
            if (insertBean==null) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            Integer result=portalTabGroupService.addOne(insertBean);
            if (result== -1) {
                return JsonMessage.create(1, "", "");
            }else if(result==-2){
                return JsonMessage.create(2, "tab中已经关联这个group", "");
            }else if(result==-3){
                return JsonMessage.create(3, "tab中已经存在一个有视频的group", "");
            }else if(result==-4){
                return JsonMessage.create(4, "tab中已经存在一个未知行参数的Group", "");
            }else if(result==-5){
                return JsonMessage.create(5, "纵向Group独占一个tab", "");
            }else if (result>0) {
                return JsonMessage.create(0, "添加成功", "");
            } else {
                return JsonMessage.create(-1L, "数据库插入失败", "");
            }
        } catch (Exception ex) {
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }


    @RequestMapping(value = "/checkVideo")
    @ResponseBody
    public Object checkVideo(@RequestBody String getStr) {
        try {
            PortalTabGroupCondition getBean = JSON.parseObject(getStr, PortalTabGroupCondition.class);
            return JSON.toJSONString(portalTabGroupService.checkVideo(getBean), SerializerFeature.WriteMapNullValue);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
