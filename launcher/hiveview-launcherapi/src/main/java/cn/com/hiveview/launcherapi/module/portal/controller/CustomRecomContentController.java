package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.entity.module.portal.CustomRecomTempleteListVo;
import cn.com.hiveview.entity.module.portal.PortalCustomRecomContentVo;
import cn.com.hiveview.launcherapi.module.portal.condition.CustomRecomBackupsContentCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.CustomRecomTempleteCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalCustomRecomContentCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.UnbundlingCondition;
import cn.com.hiveview.launcherapi.module.portal.service.CustomRecomTempleteListService;
import cn.com.hiveview.launcherapi.module.portal.service.PortalCustomRecomContentService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by user on 2017/8/9.
 */
@Controller
@RequestMapping("/customRecomContentList")
public class CustomRecomContentController {

    @Autowired
    private PortalCustomRecomContentService portalCustomRecomContentService;

    @Autowired
    private CustomRecomTempleteListService customRecomTempleteListService;

    @RequestMapping("/save")
    @ResponseBody
    public Object save(@RequestBody String indexStr ){
        try {
            if(StringUtils.isBlank(indexStr)){
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            CustomRecomTempleteCondition getCodition = JSON.parseObject(indexStr,CustomRecomTempleteCondition.class);
            if(getCodition == null){
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if(portalCustomRecomContentService.save(getCodition) > 0){
                return  JsonMessage.create(0, "", "success");
            }else {
                return JsonMessage.create(-1L, "数据库插入失败", "");
            }
        }catch (Exception e){
            e.printStackTrace();
            return JsonMessage.create(-1L, e.getMessage(), "");
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public Object update(@RequestBody String indexStr){
        try {
            if(StringUtils.isBlank(indexStr)){
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            CustomRecomTempleteCondition updateCodition = JSON.parseObject(indexStr,CustomRecomTempleteCondition.class);
            if(null == updateCodition){
                return  JsonMessage.create(-1l, "反序列化失败", "");
            }
            if(portalCustomRecomContentService.update(updateCodition) > 0){
                return  JsonMessage.create(0, "", "success");
            }else {
                return  JsonMessage.create(-1L, "数据库更新失败", "");
            }
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(@RequestBody String indexStr){
        try {
            if(StringUtils.isBlank(indexStr)){
                return  JsonMessage.create(-1l, "参数不能为空", "");
            }
            CustomRecomTempleteCondition deleteCondition = JSON.parseObject(indexStr,CustomRecomTempleteCondition.class);
            if(null == deleteCondition){
                return  JsonMessage.create(-1l, "反序列化失败", "");
            }
            if(portalCustomRecomContentService.getTabCount(deleteCondition)>0){
                return  JsonMessage.create(-1, "已关联Tab，无法删除！", "");
            }else{
                Integer bool = 0;
                Integer bool_son=0;
                bool = customRecomTempleteListService.delete(deleteCondition);
                PortalCustomRecomContentCondition condition = new PortalCustomRecomContentCondition();
                condition.setRecomTempletId(deleteCondition.getTempleteId());
                bool_son = portalCustomRecomContentService.delete(condition);
                if(bool>0){
                        return  JsonMessage.create(0, "", "success");
                }else {
                    return  JsonMessage.create(-1L, "数据库删除失败", "");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return JsonMessage.create(-1L, e.getMessage(), "");
        }
    }

    @RequestMapping("/getCount")
    @ResponseBody
    public Object getCount(@RequestBody String indexStr){
        try {
            if(StringUtils.isBlank(indexStr)){
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalCustomRecomContentCondition condition = JSON.parseObject(indexStr,PortalCustomRecomContentCondition.class);
            if(null == condition){
                return  JsonMessage.create(-1l, "反序列化失败", "");
            }
            return portalCustomRecomContentService.getCount(condition);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }

    @RequestMapping("/customContent")
    @ResponseBody
    public Object customContent(@RequestBody String indexStr){
        try {
            if(StringUtils.isBlank(indexStr)){
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            UnbundlingCondition condition = JSON.parseObject(indexStr,UnbundlingCondition.class);
            if(null == condition){
                return  JsonMessage.create(-1l, "反序列化失败", "");
            }
            List<List<PortalCustomRecomContentVo>> list = portalCustomRecomContentService.customContent(condition);

            return portalCustomRecomContentService.updateContentById(list);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }
    @RequestMapping("/saveCopyContent")
    @ResponseBody
    public Object saveCopyContent(@RequestBody String indexStr) {
        try {
            if(StringUtils.isBlank(indexStr)){
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            CustomRecomTempleteCondition getCodition = JSON.parseObject(indexStr,CustomRecomTempleteCondition.class);
            if(getCodition == null){
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if(portalCustomRecomContentService.saveCopyContent(getCodition) > 0){
                return  JsonMessage.create(0, "", "success");
            }else {
                return JsonMessage.create(-1L, "数据库插入失败", "");
            }
        }catch (Exception e){
            e.printStackTrace();
            return JsonMessage.create(-1L, e.getMessage(), "");
        }
    }

    @RequestMapping("/selectRowCol")
    @ResponseBody
    public Object selectRowCol(@RequestBody String indexStr){
        try {
            if(StringUtils.isBlank(indexStr)){
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            CustomRecomBackupsContentCondition condition = JSON.parseObject(indexStr,CustomRecomBackupsContentCondition.class);
            if(null == condition){
                return  JsonMessage.create(-1l, "反序列化失败", "");
            }
            return  portalCustomRecomContentService.selectRowCol(condition);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }
}
