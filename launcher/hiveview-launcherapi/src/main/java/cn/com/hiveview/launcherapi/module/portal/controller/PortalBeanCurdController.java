package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalTempletControllerTypeCondition;
import cn.com.hiveview.launcherapi.module.portal.service.PortalBeanCurdService;
import cn.com.hiveview.launcherapi.module.portal.util.IpUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by user on 2017/7/18.
 */
@Controller
@RequestMapping("/portalBeanCurd")
public class PortalBeanCurdController {

    @Autowired
    PortalBeanCurdService portalBeanCurdService;

    /*获取豆腐块信息接口*/
    @RequestMapping(value = { "/launcherHome/{mac}/{sn}/{model}/{romversion}/{version}" }, method = RequestMethod.GET)
    @ResponseBody
    public Object getPortalList(HttpServletRequest request, @PathVariable String mac, @PathVariable String sn, @PathVariable String model,
                                @PathVariable String romversion,@PathVariable String version) {
        try {
            if (StringUtils.isBlank(mac+"")) {
                return JsonMessage.create(-1l, "mac不能为空", "");
            }
            if (StringUtils.isBlank(sn+"")) {
                return JsonMessage.create(-1l, "sn不能为空", "");
            }
            if (StringUtils.isBlank(model+"")) {
                return JsonMessage.create(-1l, "model不能为空", "");
            }
            if (StringUtils.isBlank(romversion+"")) {
                return JsonMessage.create(-1l, "romversion不能为空", "");
            }
            if (StringUtils.isBlank(version+"")) {
                return JsonMessage.create(-1l, "version不能为空", "");
            }
            mac = mac.replaceAll(":", "").replaceAll("-", "").toUpperCase();
            sn = sn.toUpperCase();
            String Ip = IpUtil.getRemoteAddrIp(request);
            //赋参数
            PortalTempletControllerTypeCondition condition = new PortalTempletControllerTypeCondition();
            condition.setMac(mac);
            condition.setSn(sn);
            condition.setIp(Ip);
            condition.setEquipmentNo(model);
            condition.setRomversion(romversion);
            condition.setVersion(version);
           /* condition.setPage(page);
            condition.setRows(size);
            condition.setPageIndex(condition.getPage());
            condition.setPageSize(condition.getRows());*/
            return JsonMessage.create(0, "",portalBeanCurdService.launcherHome(condition));
        } catch (Exception ex) {
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }
}
