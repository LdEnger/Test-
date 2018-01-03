package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalAreaAdministrationListEntity;
import cn.com.hiveview.entity.module.portal.PortalAreaAdministrationListVo;
import cn.com.hiveview.entity.module.portal.PortalAreaContentListVo;
import cn.com.hiveview.entity.module.portal.PortalTabPageEntity;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalAreaAdminirationListCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalAreaContentListCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalLauncherTempletTabCondition;
import cn.com.hiveview.launcherapi.module.portal.service.PortalAreaContentService;
import cn.com.hiveview.launcherapi.module.portal.util.DBHelper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by user on 2017/10/8.
 */
@Controller
@RequestMapping("/PortalAreaContenController")
public class PortalAreaContenController {

    @Autowired
    private PortalAreaContentService portalAreaContentService;

    @RequestMapping(value = { "/getPage" })
    @ResponseBody
    public Object getPage(@RequestBody String getStr) {
        try {
            PortalAreaContentListCondition getBean = JSON.parseObject(getStr, PortalAreaContentListCondition.class);
            return JSON.toJSONString(portalAreaContentService.getPage(getBean), SerializerFeature.WriteMapNullValue);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = { "/getAreaContentList/{areaId}/{page}/{size}/{version}" })
    @ResponseBody
    public Object getAreaContentList(@PathVariable Integer areaId, @PathVariable Integer page, @PathVariable Integer size, @PathVariable String version){
        try {

            PortalAreaContentListCondition condition = new PortalAreaContentListCondition();
            condition.setAreaId(areaId);
            condition.setPage(page);
            condition.setRows(size);
            condition.setVersion(version);
            condition.setPageIndex(condition.getPage());
            condition.setPageSize(condition.getRows());

            List<PortalAreaAdministrationListEntity> result = portalAreaContentService.getAreaContentList(condition);
            return JsonMessage.create(0, "",result);
        } catch (Exception ex) {
            ex.printStackTrace();
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }

    @RequestMapping("/delete")
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
    @RequestMapping(value = "/moveTop")
    @ResponseBody
    public Object moveTop(@RequestBody String indexStr) {
        try {
            if (StringUtils.isBlank(indexStr)) {
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalAreaContentListCondition topBean = JSON.parseObject(indexStr, PortalAreaContentListCondition.class);
            if (null == topBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalAreaContentService.updateMoveTop(topBean)>0) {
                return JsonMessage.create(0, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库更新失败", "");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }
    @RequestMapping(value = "/moveUp")
    @ResponseBody
    public Object moveUp(@RequestBody String indexStr) {
        try {
            if (StringUtils.isBlank(indexStr)) {
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalAreaContentListCondition upBean = JSON.parseObject(indexStr, PortalAreaContentListCondition.class);
            if (null == upBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalAreaContentService.updateMoveUp(upBean)>0) {
                return JsonMessage.create(0, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库更新失败", "");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }
    @RequestMapping(value = "/moveDown")
    @ResponseBody
    public Object moveDown(@RequestBody String indexStr) {
        try {
            if (StringUtils.isBlank(indexStr)) {
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalAreaContentListCondition downBean = JSON.parseObject(indexStr, PortalAreaContentListCondition.class);
            if (null == downBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalAreaContentService.updateMoveDown(downBean)>0) {
                return JsonMessage.create(0, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库更新失败", "");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }

}
