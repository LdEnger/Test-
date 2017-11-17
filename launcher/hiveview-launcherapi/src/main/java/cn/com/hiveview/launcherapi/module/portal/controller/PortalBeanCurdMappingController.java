package cn.com.hiveview.launcherapi.module.portal.controller;
import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalBeanCurdMappingListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalBeanCurdMappingListCondition;
import cn.com.hiveview.launcherapi.module.portal.service.PortalBeanCurdMappingService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2017/8/2.
 */
@Controller
@RequestMapping("/portalBeanCurdMappingController")
public class PortalBeanCurdMappingController {

    @Autowired
    private PortalBeanCurdMappingService portalBeanCurdMappingService;

    @RequestMapping("/getPageList")
    @ResponseBody
    public ScriptPage<PortalBeanCurdMappingListVo> getPageList(@RequestBody String getStr) {
        try {
            PortalBeanCurdMappingListCondition portalBeanCurdMappingListCondition1 = JSON.parseObject(getStr, PortalBeanCurdMappingListCondition.class);
            return this.portalBeanCurdMappingService.getPageList(portalBeanCurdMappingListCondition1);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/getMinMapping")
    @ResponseBody
    public Object getMinMapping(@RequestBody String getStr){
        try {
            PortalBeanCurdMappingListCondition condition = JSON.parseObject(getStr,PortalBeanCurdMappingListCondition.class);
            return this.portalBeanCurdMappingService.getMinMapping(condition);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/getMaxMapping")
    @ResponseBody
    public Object getMaxMapping(@RequestBody String getStr){
        try {
            PortalBeanCurdMappingListCondition condition = JSON.parseObject(getStr,PortalBeanCurdMappingListCondition.class);
            return this.portalBeanCurdMappingService.getMaxMapping(condition);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/getMinSeq")
    @ResponseBody
    public Object getMinSeq(@RequestBody String getStr){
        try {
            PortalBeanCurdMappingListCondition condition = JSON.parseObject(getStr,PortalBeanCurdMappingListCondition.class);
            return this.portalBeanCurdMappingService.getMinSeq(condition);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/getMappingCount")
    @ResponseBody
    public Object getMappingCount(@RequestBody String getStr){
        try {
            PortalBeanCurdMappingListCondition condition = JSON.parseObject(getStr,PortalBeanCurdMappingListCondition.class);
            return this.portalBeanCurdMappingService.getMappingCount(condition);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }

    }
    @RequestMapping("/update")
    @ResponseBody
    public Object update(@RequestBody String updateStr) {
        try {
            if (StringUtils.isBlank(updateStr)) {
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalBeanCurdMappingListCondition upCondition = JSON.parseObject(updateStr, PortalBeanCurdMappingListCondition.class);
            if (null == upCondition) {
                return JsonMessage.create(-1l, "反序列化失败", "");
            }
            if (portalBeanCurdMappingService.update(upCondition) > 0) {
                return JsonMessage.create(0, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库更新失败", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/save")
    @ResponseBody
    public  Object save(@RequestBody String saveStr){
        try{
            if (StringUtils.isBlank(saveStr)){
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalBeanCurdMappingListCondition saveCondition = JSON.parseObject(saveStr,PortalBeanCurdMappingListCondition.class);
            if(null == saveCondition){
                return  JsonMessage.create(-1l, "反序列化失败", "");
            }
            if(portalBeanCurdMappingService.save(saveCondition) > 0){
                return  JsonMessage.create(0, "", "");
            }else {
                return  JsonMessage.create(-1L, "数据库添加失败", "");
            }
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }


    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(@RequestBody String delStr){
        try {
            if(StringUtils.isBlank(delStr)){
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalBeanCurdMappingListCondition delCondition = JSON.parseObject(delStr,PortalBeanCurdMappingListCondition.class);
            if(null == delCondition){
                return JsonMessage.create(-1l, "反序列化失败", "");
            }
            if(portalBeanCurdMappingService.delete(delCondition) > 0){
                return  JsonMessage.create(0, "", "");
            }else {
                return  JsonMessage.create(-1L, "数据库删除失败", "");
            }
        }catch (Exception e){
            e.printStackTrace();
            return JsonMessage.create(-1L, e.getMessage(), "");
        }
    }
}