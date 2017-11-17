package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.LogoLicenseManageListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.LogoLicenseManageListCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalTempletControllerTypeCondition;
import cn.com.hiveview.launcherapi.module.portal.service.LogoLicenseManageListService;
import cn.com.hiveview.launcherapi.module.portal.util.DBHelper;
import cn.com.hiveview.launcherapi.module.portal.util.IpUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by user on 2017/7/5.
 */
@Controller
@RequestMapping("/logoManageList")
public class LogoLicenseManageListController {

    @Autowired
    private LogoLicenseManageListService logoLicenseManageListService;
    @Autowired
    private DBHelper holder;
    @RequestMapping(value = { "/getPageList" })
    @ResponseBody
    public ScriptPage<LogoLicenseManageListVo> getPageList(@RequestBody String getStr) {
        try {
            LogoLicenseManageListCondition getBean = JSON.parseObject(getStr, LogoLicenseManageListCondition.class);
            return logoLicenseManageListService.getList(getBean);
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
            LogoLicenseManageListCondition insertBean = JSON.parseObject(indexStr, LogoLicenseManageListCondition.class);
            if (null == insertBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (logoLicenseManageListService.save(insertBean)>0) {
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
            LogoLicenseManageListCondition delBean = JSON.parseObject(indexStr, LogoLicenseManageListCondition.class);
            if (null == delBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            Integer result=logoLicenseManageListService.delete(delBean);
            if (result== -1) {
                return JsonMessage.create(1, "", "");
            }else if (result>0) {
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
            LogoLicenseManageListCondition updateBean = JSON.parseObject(indexStr, LogoLicenseManageListCondition.class);
            if (null == updateBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (logoLicenseManageListService.update(updateBean)>0) {
                return JsonMessage.create(0, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库更新失败", "");
            }
        } catch (Exception ex) {
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }

    @RequestMapping(value = "/getLogo")
    @ResponseBody
    public LogoLicenseManageListVo getLogo(@RequestBody String indexStr) {
        try {
            LogoLicenseManageListCondition getBean = JSON.parseObject(indexStr, LogoLicenseManageListCondition.class);
            return logoLicenseManageListService.getLogo(getBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/getLogoList")
    @ResponseBody
    public List<LogoLicenseManageListVo> getLogoList(@RequestBody String indexStr) {
        try {
            LogoLicenseManageListCondition getBean = JSON.parseObject(indexStr, LogoLicenseManageListCondition.class);
            return logoLicenseManageListService.getLogoList(getBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = { "/getLogoLicenseList/{mac}/{sn}/{model}/{romversion}/{page}/{size}/{version}" }, method = RequestMethod.GET)
    @ResponseBody
    public Object getLogoLicenseList(HttpServletRequest request, @PathVariable String mac, @PathVariable String sn, @PathVariable String model,
                                @PathVariable String romversion, @PathVariable Integer page, @PathVariable Integer size,
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
            holder.setDBType(DBHelper.DB_TYPE_R);
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
            LogoLicenseManageListVo result = logoLicenseManageListService.getLogoLicenseList(condition);
            holder.clearDBType();
            return JsonMessage.create(0, "",result);
        } catch (Exception ex) {
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }


}
