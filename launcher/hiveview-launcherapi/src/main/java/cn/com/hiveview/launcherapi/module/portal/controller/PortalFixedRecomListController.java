package cn.com.hiveview.launcherapi.module.portal.controller;


import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalFiexdRecomListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalFixedRecomListCondition;
import cn.com.hiveview.launcherapi.module.portal.service.PortalFixedRecomListService;
import com.alibaba.fastjson.JSON;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by user on 2017/7/14.
 */
@Controller
@RequestMapping("/fiexdRecomList")
public class PortalFixedRecomListController {

    @Autowired
    private PortalFixedRecomListService portalFixedRecomListService;

    @RequestMapping(value = {"/getPageList"})
    @ResponseBody
    public ScriptPage<PortalFiexdRecomListVo> getPageList(@RequestBody String getStr){
        try{
            PortalFixedRecomListCondition getBean = JSON.parseObject(getStr,PortalFixedRecomListCondition.class);
            return  portalFixedRecomListService.getPageList(getBean);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }

    @RequestMapping(value = {"/getFixedRecomListOne"})
    @ResponseBody
    public PortalFiexdRecomListVo getFixedRecomListOne(@RequestBody String getStr){
        try{
            PortalFixedRecomListCondition getBean = JSON.parseObject(getStr,PortalFixedRecomListCondition.class);
            return  portalFixedRecomListService.getFixedRecomListOne(getBean);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }
    @RequestMapping(value = "/save")
    @ResponseBody
    public Object save(@RequestBody String indexStr){
        try {
            if (StringUtils.isBlank(indexStr)) {
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalFixedRecomListCondition insertBean = JSON.parseObject(indexStr, PortalFixedRecomListCondition.class);
            if (null == insertBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalFixedRecomListService.save(insertBean)>0) {
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
    public  Object delete(@RequestBody String indexStr){
        try{
            if(StringUtils.isBlank(indexStr)){
                return  JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalFixedRecomListCondition delBean  =  JSON.parseObject(indexStr,PortalFixedRecomListCondition.class);
            if(null == delBean){
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            Integer str = portalFixedRecomListService.delete(delBean);
            if(str == -1){
                return  JsonMessage.create(1, "", "");
            }else {
                if (str > 0) {
                    return JsonMessage.create(0, "", "");
                } else {
                    return JsonMessage.create(-1L, "数据库删除失败", "");
                }
            }}catch (Exception e){
            return JsonMessage.create(-1L, e.getMessage(), "");
        }

    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@RequestBody String indexStr) {
        try {
            if (StringUtils.isBlank(indexStr)) {
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalFixedRecomListCondition updateBean = JSON.parseObject(indexStr, PortalFixedRecomListCondition.class);
            if (null == updateBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalFixedRecomListService.update(updateBean)>0) {
                return JsonMessage.create(0, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库更新失败", "");
            }
        } catch (Exception ex) {
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }

    @RequestMapping(value = "getTypeList")
    @ResponseBody
    public List<PortalFiexdRecomListVo> getTypeList(@RequestBody String getStr){
        try {
            PortalFixedRecomListCondition getCondition = JSON.parseObject(getStr,PortalFixedRecomListCondition.class);
            return  portalFixedRecomListService.getTypeList(getCondition);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }

    @RequestMapping(value = "/updateEffective")
    @ResponseBody
    public Object updateEffective(@RequestBody String indexStr) {
        try {
            if (StringUtils.isBlank(indexStr)) {
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalFixedRecomListCondition updateBean = JSON.parseObject(indexStr, PortalFixedRecomListCondition.class);
            if (null == updateBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalFixedRecomListService.updateEffective(updateBean)>0) {
                return JsonMessage.create(0, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库更新失败", "");
            }
        } catch (Exception ex) {
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }

}
