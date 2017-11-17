package cn.com.hiveview.launcherapi.module.portal.controller;


import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.NavigationVo;
import cn.com.hiveview.entity.module.portal.PortalScreenRecommendList;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalScreenRecommendListCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalTempletControllerTypeCondition;
import cn.com.hiveview.launcherapi.module.portal.service.NavigationService;
import cn.com.hiveview.launcherapi.module.portal.util.DBHelper;
import cn.com.hiveview.launcherapi.module.portal.util.IpUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * Created by admin on 2017/6/29.
 */
@Controller
@RequestMapping("/Navigation")
public class NavigationController {
    @Autowired
    private NavigationService navigationService;
    @Autowired
    private DBHelper holder;
    /**
     * 导航栏接口
     * @param request
     * @param mac
     * @param sn
     * @param userId
     * @param model      硬件型号
     * @param templateId 模板id
     * @param romversion rom版本
     * @param version    接口版本
     * @return
     */
    @RequestMapping(value = { "/getNavigationList/{mac}/{sn}/{userId}/{model}/{templateId}/{romversion}/{version}" }, method = RequestMethod.GET)
    @ResponseBody
    public Object getNavigationList(HttpServletRequest request, @PathVariable String mac, @PathVariable String sn,
                                    @PathVariable String userId,@PathVariable String model, @PathVariable Integer templateId, @PathVariable String romversion,
                                    @PathVariable String version) {
        try {
            if (StringUtils.isBlank(mac + "")) {
                return JsonMessage.create(0, "mac不能为空", navigationService.getNavigationListDeFaultCache());
            }
            if (StringUtils.isBlank(sn+"")) {
                return JsonMessage.create(0, "sn不能为空", navigationService.getNavigationListDeFaultCache());
            }
            if (StringUtils.isBlank(model+"")) {
                return JsonMessage.create(0, "model不能为空", navigationService.getNavigationListDeFaultCache());
            }
            if (StringUtils.isBlank(templateId+"")) {
                return JsonMessage.create(0, "templateId不能为空", navigationService.getNavigationListDeFaultCache());
            }
            if (StringUtils.isBlank(romversion+"")) {
                return JsonMessage.create(0, "romversion不能为空", navigationService.getNavigationListDeFaultCache());
            }
            if (StringUtils.isBlank(version+"")) {
                return JsonMessage.create(0, "version不能为空", navigationService.getNavigationListDeFaultCache());
            }
            mac = mac.replaceAll(":", "").replaceAll("-", "").toUpperCase();
            sn = sn.toUpperCase();
            String Ip = IpUtil.getRemoteAddrIp(request);
            HashMap<String,String> parmMap=new HashMap<String,String>();
            parmMap.put("mac",mac);
            parmMap.put("sn",sn);
            parmMap.put("model",model);
            parmMap.put("templateId",templateId+"");
            parmMap.put("romversion",romversion);
            parmMap.put("ip",Ip);
            holder.setDBType(DBHelper.DB_TYPE_R);
//            return JsonMessage.create(0, "",navigationService.getNavigationList(parmMap));    //非缓存接口
            List<NavigationVo> result = navigationService.getNavigationListCache(parmMap);
            holder.clearDBType();
            return JsonMessage.create(0, "",result); //缓存接口
        } catch (Exception ex) {
            return JsonMessage.create(0, ex.getMessage(), navigationService.getNavigationListDeFaultCache());
        }
    }


    /**
     * @description 刷新导航栏接口缓存
     * @param version
     * @return
     */
    @RequestMapping(value = { "/flushNavigationList/{version}" }, method = RequestMethod.GET)
    @ResponseBody
    public Object flushNavigationList(@PathVariable String version) {
        try {
             navigationService.flushNavigationListCacheALL();
//             navigationService.flushNavigationListCacheByTmpId(10);
             return JsonMessage.create(0, "", "成功");    //非缓存接口
        } catch (Exception ex) {
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }

    /**
     * @description  获取默认tab 背景图接口
     * @param version
     * @return
     */
    @RequestMapping(value = { "/getDefaultTapPic/{version}" }, method = RequestMethod.GET)
    @ResponseBody
    public Object getDefaultTapPic(@PathVariable String version) {
        try {
           holder.setDBType(DBHelper.DB_TYPE_R);
//            return JsonMessage.create(0, "",navigationService.getDefaultTapPic());
            HashMap<String,String> result = navigationService.getDefaultTapPicCache();
            holder.clearDBType();
            return JsonMessage.create(0, "",result);
        } catch (Exception ex) {
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }
    /**
     * @description 刷新默认背景图接口
     * @param version
     * @return
     */
    @RequestMapping(value = { "/flushDefaultTapPic/{version}" }, method = RequestMethod.GET)
    @ResponseBody
    public Object flushDefaultTapPic(@PathVariable String version) {
        try {
            navigationService.flushDefaultTapPicCache();
            return JsonMessage.create(0, "", "成功");    //非缓存接口
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
            return JsonMessage.create(0, "", this.navigationService.getListByTempletInfo(condition));
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
            return JsonMessage.create(0, "",this.navigationService.getListByTempletInfo(condition));
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
            return navigationService.getPageList(getBean);
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
            if (navigationService.add(insertBean)>0) {
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
            if (navigationService.delete(delBean)>0) {
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
            Integer result = navigationService.update(updateBean);
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
            return navigationService.getComboboxList(getBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
