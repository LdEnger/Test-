package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalAreaAdministrationListVo;
import cn.com.hiveview.entity.module.portal.PortalAreaContentListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalAreaAdminirationListCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalAreaContentListCondition;
import cn.com.hiveview.launcherapi.module.portal.service.PortalAreaContentService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2017/10/8.
 */
@Controller
@RequestMapping("/PortalAreaContenController")
public class PortalAreaContenController {

    @Autowired
    private PortalAreaContentService portalAreaContentService;

    @RequestMapping("/getPage")
    @ResponseBody
    public ScriptPage<PortalAreaContentListVo> getPage(@RequestBody String getStr){
        try {
            PortalAreaContentListCondition getCondition = JSON.parseObject(getStr,PortalAreaContentListCondition.class);
            return portalAreaContentService.getPage(getCondition);
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
            PortalAreaContentListCondition delCodition = JSON.parseObject(delStr,PortalAreaContentListCondition.class);
            if(null == delCodition){
                return JsonMessage.create(-1l, "反序列化失败", "");
            }
            if(portalAreaContentService.delete(delCodition) > 0){
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
            PortalAreaContentListCondition saCondition = JSON.parseObject(saStr,PortalAreaContentListCondition.class);
            if(null == saCondition){
                return JsonMessage.create(-1l, "反序列化失败", "");
            }
            if(portalAreaContentService.save(saCondition) > 0){
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
            PortalAreaContentListCondition upCondition = JSON.parseObject(upStr,PortalAreaContentListCondition.class);
            if(null == upCondition){
                return JsonMessage.create(-1l, "反序列化失败", "");
            }
            if (portalAreaContentService.update(upCondition) > 0 ){
                return  JsonMessage.create(0, "", "");
            }else {
                return JsonMessage.create(-1L, "数据库更新失败", "");
            }
        }catch (Exception e){
            e.printStackTrace();
            return JsonMessage.create(-1L, e.getMessage(), "");
        }

    }

    @ResponseBody
    @RequestMapping("/getAreaMinSeq")
    public Object getAreaMinSeq(@RequestBody String getStr){
        try {

        PortalAreaContentListCondition getConditon = JSON.parseObject(getStr,PortalAreaContentListCondition.class);
        return portalAreaContentService.getAreaMinSeq(getConditon);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @ResponseBody
    @RequestMapping("/getAreaMaxSeq")
    public Object getAreaMaxSeq(@RequestBody String getStr){
        try {
            PortalAreaContentListCondition getCondition = JSON.parseObject(getStr,PortalAreaContentListCondition.class);
            return portalAreaContentService.getAreaMaxSeq(getCondition);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
