package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.AreaCodeList;
import cn.com.hiveview.entity.module.portal.EntranceAreaList;
import cn.com.hiveview.entity.module.portal.NewContentChanList;
import cn.com.hiveview.launcherapi.module.portal.condition.AppCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.EntranceAreaCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalSysAppIconsListCondition;
import cn.com.hiveview.launcherapi.module.portal.service.EntranceAreaService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/8/2.
 */
@Controller
@RequestMapping("/entranceArea")
public class EntranceAreaController {
    @Autowired
    private EntranceAreaService entranceAreaService;
    @RequestMapping(value = "/getDiffCityList")
    @ResponseBody
    public List<AreaCodeList> getDiffCityList(@RequestBody String getStr) {
        EntranceAreaCondition entranceArea= JSON.parseObject(getStr, EntranceAreaCondition.class);
        Integer controllerType = entranceArea.getControllerType();
        Integer diffCityType = entranceArea.getDiffCityType();
        List<AreaCodeList> allList = null;
        if(controllerType != null && controllerType == 2){
            allList = entranceAreaService.getAreaGroupList();
        }else if(controllerType != null && controllerType == 3){
            allList = entranceAreaService.getEquipments();
        }else{
            allList = entranceAreaService.getArea("search", "-1");
        }
        List<AreaCodeList> delList = new ArrayList<AreaCodeList>();
        try {
            if(diffCityType == null || diffCityType != 5){
                entranceArea.setAreaId(null);
            }else{
                entranceArea.setAreaId(-1);
            }
            List<EntranceAreaList> result = entranceAreaService.getCityByLogoId(entranceArea);
            for (AreaCodeList vc : allList) {
                String code = vc.getCode();
                if (code.equals("00000")) {
                    delList.add(vc);
                }
//				if (entranceArea.getAreaId() != null) {
                for (EntranceAreaList lmv : result) {
                    String cityId = lmv.getCityId();
                    if (code.equals(cityId)) {
                        delList.add(vc);
                    }
                }
                //}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        allList.removeAll(delList);
        return allList;
    }

    @RequestMapping(value = "/getCityByAreaId")
    @ResponseBody
    public List<EntranceAreaList> getCityByLogoId(@RequestBody String getStr) {
        EntranceAreaCondition entranceArea= JSON.parseObject(getStr, EntranceAreaCondition.class);
        List<EntranceAreaList> result = new ArrayList<EntranceAreaList>();
        try {
            result = entranceAreaService.getCityByLogoId(entranceArea);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    @RequestMapping(value = "/getRightCodeName")
    @ResponseBody
    public Object getRightCodeName(@RequestBody String getStr) {
        Object rev = null;
        EntranceAreaCondition entranceArea= JSON.parseObject(getStr, EntranceAreaCondition.class);
        Integer controllerType = entranceArea.getControllerType();
        List<AreaCodeList> allList = null;
        List<AreaCodeList> allList2 = null;
        if(controllerType != null && controllerType == 2){
            allList = entranceAreaService.getAreaGroupList();
        }else if(controllerType != null && controllerType == 3){
            allList = entranceAreaService.getEquipments();
        }else if(controllerType != null && controllerType == 4){
            allList = entranceAreaService.getRomList(entranceArea);
        }else if(controllerType != null && controllerType == 5){
            entranceArea.setDiffCityType(5);
            allList = entranceAreaService.getArea("search", "-1");
            allList2 = entranceAreaService.getEquipments();
            allList.addAll(allList2);
        }else{
            allList = entranceAreaService.getArea("search", "-1");
        }
        List<AreaCodeList> delList = new ArrayList<AreaCodeList>();
        try {
            Integer id = entranceArea.getAreaId();
            if(id!=null){
                List<EntranceAreaList> result = entranceAreaService.getCityByLogoId(entranceArea);
                if (result != null && result.size() > 0 && allList!=null && allList.size() > 0) {
                    for (AreaCodeList vc : allList) {
                        String code = vc.getCode();
                        for (EntranceAreaList lmv : result) {
                            String cityId = lmv.getCityId();
                            if (code.equals(cityId)) {
                                delList.add(vc);
                            }
                        }
                    }
                }}
        } catch (Exception e) {
            e.printStackTrace();
        }
        rev = delList;
        if(controllerType == 4 && delList !=null){
            ScriptPage<EntranceAreaList> scriptPage = new ScriptPage<EntranceAreaList>();
            scriptPage.setRows(delList);
            scriptPage.setTotal(delList.size());
            rev = scriptPage;
        }
        return rev;
    }

    @RequestMapping(value = "/insert")
    @ResponseBody
    public Object insert(@RequestBody String indexStr) {
        try {
            if (StringUtils.isBlank(indexStr)) {
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            EntranceAreaCondition insertBean = JSON.parseObject(indexStr, EntranceAreaCondition.class);
            if (null == insertBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (entranceAreaService.add(insertBean)>0) {
                return JsonMessage.create(0, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库插入失败", "");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
            EntranceAreaCondition updateBean = JSON.parseObject(indexStr, EntranceAreaCondition.class);
            if (null == updateBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            Integer open = entranceAreaService.update(updateBean);
            if (open>0) {
                return JsonMessage.create(0, "", "");
            }else if(open == -2){
                    return JsonMessage.create(-2, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库更新失败", "");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }
}
