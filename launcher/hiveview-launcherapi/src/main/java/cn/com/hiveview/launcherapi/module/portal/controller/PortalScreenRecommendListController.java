package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalScreenRecommendList;
import cn.com.hiveview.entity.module.portal.PortalScreenRecommendListApiVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalScreenRecommendListCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalTempletControllerTypeCondition;
import cn.com.hiveview.launcherapi.module.portal.service.PortalScreenRecommendListService;
import cn.com.hiveview.launcherapi.module.portal.service.RedisService;
import cn.com.hiveview.launcherapi.module.portal.util.IpUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by admin on 2017/6/29.
 */
@Controller
@RequestMapping("/screenRecommendList")
public class PortalScreenRecommendListController {
    @Autowired
    private PortalScreenRecommendListService  portalService;
    @RequestMapping(value = { "/getList" })
    @ResponseBody
    public Object getList() {
        try {
            PortalScreenRecommendListCondition condition = new PortalScreenRecommendListCondition();
            return JsonMessage.create(0, "",portalService.getList(condition));
        } catch (Exception ex) {
            ex.printStackTrace();
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }
    /**
     * @description 首屏横竖图接口
     * @param templetId
     * @param recommendId
     * @param page
     * @param size
     * @param version
     * @return
     */
    @RequestMapping(value = { "/getPortalList/{templetId}/{recommendId}/{recommendType}/{page}/{size}/{version}" }, method = RequestMethod.GET)
    @ResponseBody
    public Object getPortalList(@PathVariable Integer templetId, @PathVariable Integer recommendId,@PathVariable Integer recommendType, @PathVariable Integer page,@PathVariable Integer size,@PathVariable String version) {
        try {
            if (StringUtils.isBlank(recommendId+"")) {
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            //赋参数
            PortalScreenRecommendListCondition condition = new PortalScreenRecommendListCondition();
            condition.setTempletId(templetId);
            condition.setRecommendId(recommendId);
            condition.setRecommendType(recommendType);
            condition.setPage(page);
            condition.setRows(size);
            condition.setPageIndex(condition.getPage());
            condition.setPageSize(condition.getRows());
            return JsonMessage.create(0, "",portalService.getList(condition));
        } catch (Exception ex) {
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }
    @RequestMapping(value = { "/getList/{mac}/{sn}/{model}/{romVersion}/{version}" })
    @ResponseBody
    public Object getBigList(HttpServletRequest request, @PathVariable String mac, @PathVariable String sn, @PathVariable String model, @PathVariable String romVersion,@PathVariable String version) {
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
                if (StringUtils.isBlank(romVersion+"")) {
                    return JsonMessage.create(-1l, "参数不能为空", "");
                }
                mac = mac.replaceAll(":", "").replaceAll("-", "").toUpperCase();
                sn = sn.toUpperCase();
                String Ip = IpUtil.getRemoteAddrIp(request);
                //赋参数
                PortalTempletControllerTypeCondition condition = new PortalTempletControllerTypeCondition();
                condition.setMac(mac);
                condition.setSn(sn);
                condition.setEquipmentNo(model);
                condition.setRomversion(romVersion);
                condition.setVersion(version);
                condition.setIp(Ip);
                condition.setType(0);
            return JsonMessage.create(0, "", this.portalService.getListByTempletInfo(condition));
        } catch (Exception ex) {
            ex.printStackTrace();
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }

    @RequestMapping(value = { "/getPortalList/{mac}/{sn}/{model}/{romVersion}/{version}/{page}/{size}" }, method = RequestMethod.GET)
    @ResponseBody
    public Object getList(HttpServletRequest request, @PathVariable String mac, @PathVariable String sn, @PathVariable String model, @PathVariable String romVersion, @PathVariable String version, @PathVariable Integer page, @PathVariable Integer size) {
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
            if (StringUtils.isBlank(romVersion+"")) {
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            mac = mac.replaceAll(":", "").replaceAll("-", "").toUpperCase();
            sn = sn.toUpperCase();
            String Ip = IpUtil.getRemoteAddrIp(request);
            //赋参数
            PortalTempletControllerTypeCondition condition = new PortalTempletControllerTypeCondition();
            condition.setMac(mac);
            condition.setSn(sn);
            condition.setEquipmentNo(model);
            condition.setRomversion(romVersion);
            condition.setVersion(version);
            condition.setIp(Ip);
            condition.setPage(page);
            condition.setRows(size);
            condition.setPageIndex(condition.getPage());
            condition.setPageSize(condition.getRows());
            condition.setType(1);
            return JsonMessage.create(0, "",this.portalService.getListByTempletInfo(condition));
        } catch (Exception ex) {
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }
    /**
     * @description 页面查询
     * @param getStr
     * @return
     */
    @RequestMapping(value = { "/getPageList" })
    @ResponseBody
    public ScriptPage<PortalScreenRecommendList> getPageList(@RequestBody String getStr) {
        try {
            PortalScreenRecommendListCondition getBean = JSON.parseObject(getStr, PortalScreenRecommendListCondition.class);
            return portalService.getPageList(getBean);
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
            PortalScreenRecommendListCondition insertBean = JSON.parseObject(indexStr, PortalScreenRecommendListCondition.class);
            if (null == insertBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalService.add(insertBean)>0) {
                return JsonMessage.create(0, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库插入失败", "");
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
            PortalScreenRecommendListCondition delBean = JSON.parseObject(indexStr, PortalScreenRecommendListCondition.class);
            if (null == delBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalService.delete(delBean)>0) {
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
            PortalScreenRecommendListCondition updateBean = JSON.parseObject(indexStr, PortalScreenRecommendListCondition.class);
            if (null == updateBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            Integer result = portalService.update(updateBean);
            /*if (result== -1) {
                return JsonMessage.create(1, "", "");
            }else */
            if (result>0) {
                return JsonMessage.create(0, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库更新失败", "");
            }
        } catch (Exception ex) {
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }
    @RequestMapping(value = { "/getComboboxList" })
    @ResponseBody
    public List<PortalScreenRecommendList> getComboboxList(@RequestBody String getStr) {
        try {
            PortalScreenRecommendListCondition getBean = JSON.parseObject(getStr, PortalScreenRecommendListCondition.class);
            return portalService.getComboboxList(getBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
