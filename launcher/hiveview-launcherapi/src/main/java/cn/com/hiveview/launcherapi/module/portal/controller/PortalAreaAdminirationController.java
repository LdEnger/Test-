package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalAreaAdministrationListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalAreaAdminirationListCondition;
import cn.com.hiveview.launcherapi.module.portal.service.PortalAreaAdminirationService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2017/10/6.
 */
@Controller
@RequestMapping("/PortalAreaAdminirationController")
public class PortalAreaAdminirationController {

    @Autowired
    private PortalAreaAdminirationService portalAreaAdminirationService;

    @RequestMapping("/getPage")
    @ResponseBody
    public ScriptPage<PortalAreaAdministrationListVo> getPage(@RequestBody String getStr){
            try {
                PortalAreaAdminirationListCondition getCondition = JSON.parseObject(getStr,PortalAreaAdminirationListCondition.class);
                return portalAreaAdminirationService.getPage(getCondition);
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
    }

    @RequestMapping("/delelte")
    @ResponseBody
    public Object delete(@RequestBody String delStr){
        try {
            if(StringUtils.isBlank(delStr)){
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalAreaAdminirationListCondition delCodition = JSON.parseObject(delStr,PortalAreaAdminirationListCondition.class);
            if(null == delCodition){
                return JsonMessage.create(-1l, "反序列化失败", "");
            }
            Integer str = portalAreaAdminirationService.delete(delCodition);
            if(str == -1){
                return  JsonMessage.create(1, "", "");
            }else
            if(str > 0){
                return  JsonMessage.create(0, "", "");
            }else {
                return  JsonMessage.create(-1L, "数据库删除失败", "");
            }
        }catch (Exception e){
            e.printStackTrace();
            return JsonMessage.create(-1L, e.getMessage(), "");
        }
    }

    @ResponseBody
    @RequestMapping("/save")
    public Object save(@RequestBody String saStr){
        try {
            if(StringUtils.isBlank(saStr)){
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalAreaAdminirationListCondition saCondition = JSON.parseObject(saStr,PortalAreaAdminirationListCondition.class);
            if(null == saCondition){
                return JsonMessage.create(-1l, "反序列化失败", "");
            }
            if(portalAreaAdminirationService.save(saCondition) > 0){
                return  JsonMessage.create(0, "", "");
            }else {
                return JsonMessage.create(-1L, "数据库插入失败", "");
            }
        }catch (Exception e){
            e.printStackTrace();
            return JsonMessage.create(-1L, e.getMessage(), "");
        }
    }

    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody String upStr){
        try {
            if(StringUtils.isBlank(upStr)){
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalAreaAdminirationListCondition upCondition = JSON.parseObject(upStr,PortalAreaAdminirationListCondition.class);
            if(null == upCondition){
                return JsonMessage.create(-1l, "反序列化失败", "");
            }
            if (portalAreaAdminirationService.update(upCondition) > 0 ){
                return  JsonMessage.create(0, "", "");
            }else {
                return JsonMessage.create(-1L, "数据库更新失败", "");
            }
        }catch (Exception e){
            e.printStackTrace();
            return JsonMessage.create(-1L, e.getMessage(), "");
        }

    }
}
