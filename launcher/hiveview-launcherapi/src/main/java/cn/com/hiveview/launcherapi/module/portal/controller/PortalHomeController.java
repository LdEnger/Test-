package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalHomeCondition;
import cn.com.hiveview.launcherapi.module.portal.service.PortalHomeService;
import cn.com.hiveview.launcherapi.module.portal.util.IpUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/portalHome")
public class PortalHomeController {
    @Autowired
    private PortalHomeService portalHomeService;
    /**
     * 获取launcher首页汇总数据
     * 1、logo
     * 2、大麦VIP状态
     * 3、爱奇艺VIP状态
     * 4、首屏大图数据
     * 5、横竖图数据
     * 6、豆腐块数据
     * 7、开机自启动数据
     * @param templateId
     * @param page
     * @param size
     * @param version
     * @return
     */
    @RequestMapping(value = { "/getPortalHome/{mac}/{sn}/{model}/{userId}/{templateId}/{romversion}/{page}/{size}/{version}" }, method = RequestMethod.GET)
    @ResponseBody
    public Object getPortalList(HttpServletRequest request, @PathVariable String mac, @PathVariable String sn, @PathVariable String model,
                                @PathVariable String userId, @PathVariable Integer templateId, @PathVariable String romversion,
                                @PathVariable Integer page, @PathVariable Integer size, @PathVariable String version) {
        try {
            // TODO 验证参数非空
            if (StringUtils.isBlank(mac+"")) {
                return JsonMessage.create(-1l, "mac不能为空", "");
            }
            if (StringUtils.isBlank(sn+"")) {
                return JsonMessage.create(-1l, "sn不能为空", "");
            }
            if (StringUtils.isBlank(model+"")) {
                return JsonMessage.create(-1l, "model不能为空", "");
            }
            if (StringUtils.isBlank(templateId+"")) {
                return JsonMessage.create(-1l, "templateId不能为空", "");
            }
            if (StringUtils.isBlank(romversion+"")) {
                return JsonMessage.create(-1l, "romversion不能为空", "");
            }
            if (StringUtils.isBlank(version+"")) {
                return JsonMessage.create(-1l, "version不能为空", "");
            }
            if (StringUtils.isBlank(userId+"")) {
                userId = "-1";
            }
            mac = mac.replaceAll(":", "").replaceAll("-", "").toUpperCase();
            sn = sn.toUpperCase();
            String Ip = IpUtil.getRemoteAddrIp(request);
            //System.out.println("request");
            //System.out.println(Ip);
            //赋参数
            PortalHomeCondition condition = new PortalHomeCondition();
            condition.setIp(Ip);
            condition.setMac(mac);
            condition.setSn(sn);
            condition.setModel(model);
            condition.setTempletId(templateId);
            condition.setRomversion(romversion);
            condition.setPage(page);
            condition.setRows(size);
            condition.setPageIndex(condition.getPage());
            condition.setPageSize(condition.getRows());
            condition.setUserId(userId);
            condition.setVersion(version);
            return JsonMessage.create(0, "",portalHomeService.getPortalList(condition));
        } catch (Exception ex) {
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }
}
