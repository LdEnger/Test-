package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalTempletControllerTypeCondition;
import cn.com.hiveview.launcherapi.module.portal.service.PortalRecommendListService;
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
 * Created by user on 2017/7/21.
 */
@Controller
@RequestMapping("/portalRecommendList")
public class PortalRecommendListController {

    @Autowired
    private PortalRecommendListService portalRecommendListService;

    @RequestMapping(value = { "/getPortalRecommendList/{mac}/{sn}/{model}/{romversion}/{page}/{size}/{version}" }, method = RequestMethod.GET)
    @ResponseBody
    public Object getPortalRecommendList (HttpServletRequest request, @PathVariable String mac, @PathVariable String sn, @PathVariable String model,
                                     @PathVariable String romversion,@PathVariable Integer page, @PathVariable Integer size,
                                     @PathVariable String version) {
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
            condition.setEquipmentNo(model);
            condition.setRomversion(romversion);
            condition.setVersion(version);
            condition.setIp(Ip);
            condition.setPage(page);
            condition.setRows(size);
            condition.setPageIndex(condition.getPage());
            condition.setPageSize(condition.getRows());
            return JsonMessage.create(0, "",portalRecommendListService.getPortalRecommendList(condition));
        } catch (Exception ex) {
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }
}
