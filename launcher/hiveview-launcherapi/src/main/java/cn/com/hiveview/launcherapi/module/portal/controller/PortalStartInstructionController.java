package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalStartInstructionList;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalStartInstructionCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalTempletControllerTypeCondition;
import cn.com.hiveview.launcherapi.module.portal.service.PortalStartInstructionListService;
import cn.com.hiveview.launcherapi.module.portal.util.DBHelper;
import cn.com.hiveview.launcherapi.module.portal.util.IpUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/12.
 */
@Controller
@RequestMapping("/startInstructionList")
public class PortalStartInstructionController {
    @Autowired
    private PortalStartInstructionListService portalStartInstructionList;
    @Autowired
    private DBHelper holder;
    @RequestMapping(value = { "/getPageList" })
    @ResponseBody
    public ScriptPage<PortalStartInstructionList> getPageList(@RequestBody String getStr) {
        try {
            PortalStartInstructionCondition getBean = JSON.parseObject(getStr, PortalStartInstructionCondition.class);
            return portalStartInstructionList.getPageList(getBean);
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
            PortalStartInstructionCondition insertBean = JSON.parseObject(indexStr, PortalStartInstructionCondition.class);
            if (null == insertBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            //判重，如果重复拿到result为-1，返回一个1
            Integer result=portalStartInstructionList.add(insertBean);
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
            PortalStartInstructionCondition delBean = JSON.parseObject(indexStr, PortalStartInstructionCondition.class);
            if (null == delBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            Integer result=portalStartInstructionList.delete(delBean);
            //-1为被launcher关联的数据，不可删除,-2为被豆腐块关联得数据，不可删除
            if (result== -1) {
                return JsonMessage.create(1, "", "");
            }else if (result==-2) {
                return JsonMessage.create(2, "", "");
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
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@RequestBody String indexStr) {
        try {
            if (StringUtils.isBlank(indexStr)) {
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalStartInstructionCondition updateBean = JSON.parseObject(indexStr, PortalStartInstructionCondition.class);
            if (null == updateBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            Integer result=portalStartInstructionList.update(updateBean);
            //result==-1是更新时有重复数据,-2是被豆腐块关联，-3是被launcher关联
            if (result==-1) {
                return JsonMessage.create(1, "", "");
            }else if (result==-2) {
                return JsonMessage.create(2, "", "");
            } else if (result==-3) {
                return JsonMessage.create(3, "", "");
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
    @RequestMapping(value = { "/getComboboxList" })
    @ResponseBody
    public List<PortalStartInstructionList> getComboboxList(@RequestBody String getStr) {
        try {
            PortalStartInstructionCondition getBean = JSON.parseObject(getStr, PortalStartInstructionCondition.class);
            return portalStartInstructionList.getComboboxList(getBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @RequestMapping(value = { "/getList/{mac}/{sn}/{model}/{rowVersion}/{version}" })
    @ResponseBody
    public Object getList(HttpServletRequest request, @PathVariable String mac, @PathVariable String sn, @PathVariable String model,@PathVariable String rowVersion,@PathVariable String version) {
        Map result = new HashMap();
        try {
            if (StringUtils.isBlank(mac+"")) {
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            if (StringUtils.isBlank(sn+"")) {
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            if (StringUtils.isBlank(model+"")) {
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            if (StringUtils.isBlank(rowVersion+"")) {
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            holder.setDBType(DBHelper.DB_TYPE_R);
            mac = mac.replaceAll(":", "").replaceAll("-", "").toUpperCase();
            sn = sn.toUpperCase();
            String Ip = IpUtil.getRemoteAddrIp(request);
            //赋参数
            PortalTempletControllerTypeCondition condition = new PortalTempletControllerTypeCondition();
            condition.setMac(mac);
            condition.setSn(sn);
            condition.setEquipmentNo(model);
            condition.setRomversion(rowVersion);
            condition.setVersion(version);
            condition.setIp(Ip);
            result.put("start", this.portalStartInstructionList.getListByTempletInfo(condition));
            holder.clearDBType();
            return JsonMessage.create(0, "",result);
        } catch (Exception ex) {
            ex.printStackTrace();
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }
}
