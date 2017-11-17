package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.CustomRecomLayoutVo;
import cn.com.hiveview.entity.module.portal.CustomRecomTempleteListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.CustomRecomBackupsContentCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.CustomRecomLayoutCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.CustomRecomTempleteCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalTabGroupCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.CustomRecomBackupsContentDao;
import cn.com.hiveview.launcherapi.module.portal.dao.CustomRecomTempleteListDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalTabGroupDao;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2017/7/20.
 */
@Service
public class CustomRecomTempleteListService {

    @Autowired
    private  CustomRecomTempleteListDao customRecomTempleteListDao;
    @Autowired
    private CustomRecomBackupsContentDao customRecomBackupsContentDao;
    @Autowired
    private PortalTabGroupDao portalTabGroupDao;
    @Autowired
    private CustomRecomLayoutService customRecomLayoutServicec;

    @Autowired
    private CustomRecomGetLayoutService customRecomGetLayoutService;

    public ScriptPage<CustomRecomTempleteListVo> getList(CustomRecomTempleteCondition customRecomTempleteCondition){
        ScriptPage<CustomRecomTempleteListVo> scriptPage = new ScriptPage<>();
        customRecomTempleteCondition.setPageIndex(customRecomTempleteCondition.getPage());
        customRecomTempleteCondition.setPageSize(customRecomTempleteCondition.getRows());
        List<CustomRecomTempleteListVo> rows = this.customRecomTempleteListDao.getList(customRecomTempleteCondition);
        Integer total = this.customRecomTempleteListDao.getCount(customRecomTempleteCondition);
        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        return  scriptPage;
    }

    public CustomRecomTempleteListVo get(CustomRecomTempleteCondition condition) throws  Exception{
        System.out.println(condition);
        if(condition.getTempleteId() == null){
            return null;
        }
        return  this.customRecomTempleteListDao.get(condition);
    }
    public Integer save(CustomRecomTempleteCondition condition) throws  Exception{
        /*List<CustomRecomLayoutCondition> layouts = new ArrayList<CustomRecomLayoutCondition>();
        Integer result = 0;

        try{
            if(condition.getLayoutJson()!= null){
                layouts = customRecomGetLayoutService.getLayout(condition.getLayoutJson());
            }
            result = this.saveLayout(condition, layouts);

        }catch (Exception e){
            e.printStackTrace();
        }
        return  result;*/

        return this.customRecomTempleteListDao.save(condition); // 模板save
    }
    public Integer saveTemplete(CustomRecomTempleteCondition condition) throws  Exception{
        List<CustomRecomLayoutCondition> layouts = new ArrayList<CustomRecomLayoutCondition>();
        Integer result = 0;

        try{
            if(condition.getLayoutJson()!= null){
                layouts = customRecomGetLayoutService.getLayout(condition.getLayoutJson());
            }
            result = this.saveLayout(condition, layouts);

        }catch (Exception e){
            e.printStackTrace();
        }
        return  result;
    }
    public Integer saveLayout(CustomRecomTempleteCondition condition, List<CustomRecomLayoutCondition> layouts) throws Exception {
        Integer suc = 0;
        Integer layoutTempleteId = 0;
        if (!StringUtils.isEmpty(condition)) {
            condition.setTempleteLeve(1);
            condition.setIsEffective(0);
            Integer templeteId = customRecomTempleteListDao.save(condition); // 模板save
            suc = 0;
            if (templeteId != null) {
                for (CustomRecomLayoutCondition layoutPo : layouts) {
                    layoutPo.setTempleteId(condition.getTempleteId());
                    layoutPo.setRow(condition.getRow());
                    layoutPo.setCol(condition.getCol());
                    layoutTempleteId = condition.getTempleteId();
                    suc = customRecomLayoutServicec.save(layoutPo); // 布局方位save
                }
                if (layoutTempleteId != 0)
                customRecomGetLayoutService.orderLayout(condition.getTempleteId());
            }
        }

        return suc;
    }

    public Integer update(CustomRecomTempleteCondition condition) throws Exception{
        /*List<CustomRecomLayoutCondition> layouts = new ArrayList<CustomRecomLayoutCondition>();
        if (!StringUtils.isEmpty(condition)) {
            layouts = customRecomGetLayoutService.getLayout(condition.getLayoutJson());
        }
        Boolean bool = this.updateTemplete(condition);
        Integer templeteId = condition.getTempleteId();
        Integer flag = 0;
        if (bool) {
            flag = customRecomLayoutServicec.update(layouts, templeteId);
            customRecomGetLayoutService.orderLayout(condition.getTempleteId());
        }
        return  flag;*/
        if(condition.getFatherId()!= null){
        PortalTabGroupCondition portalTabGroupCondition = new PortalTabGroupCondition();
        CustomRecomBackupsContentCondition customRecomBackupsContentCondition = new CustomRecomBackupsContentCondition();
        portalTabGroupCondition.setBelongGroupId(condition.getTempleteId());
        portalTabGroupCondition.setGroupName(condition.getContentName());
        portalTabGroupCondition.setGroupTitle( condition.getTempleteTitle());
        portalTabGroupCondition.setGroupBackground(condition.getBackPic());
        customRecomBackupsContentCondition.setTempleteId(condition.getTempleteId());
        customRecomBackupsContentCondition.setTempleteName(condition.getContentName());
        customRecomBackupsContentDao.update(customRecomBackupsContentCondition);
        portalTabGroupDao.update(portalTabGroupCondition);
        }
        return this.customRecomTempleteListDao.update(condition);

    }
    public Integer updateTempleteLayout(CustomRecomTempleteCondition condition)throws Exception{
        List<CustomRecomLayoutCondition> layouts = new ArrayList<CustomRecomLayoutCondition>();
        if (!StringUtils.isEmpty(condition)) {
            layouts = customRecomGetLayoutService.getLayout(condition.getLayoutJson());
        }
        Boolean bool = this.updateTemplete(condition);
        Integer templeteId = condition.getTempleteId();
        Integer flag = 0;
        if (bool) {
            for (CustomRecomLayoutCondition layoutPo : layouts) {
                layoutPo.setRow(condition.getCol());
                layoutPo.setCol( condition.getRow());
            }
            flag = customRecomLayoutServicec.update(layouts, templeteId);
            customRecomGetLayoutService.orderLayout(condition.getTempleteId());
        }
        return  flag;
    }
    public Integer delete(CustomRecomTempleteCondition condition)throws  Exception{
        try {
            Integer res = null;
            Integer resu = null;
            Integer bool=customRecomTempleteListDao.selectFatherId(condition);
            if (!StringUtils.isEmpty(condition.getTempleteId()) && bool==0) {
                CustomRecomLayoutCondition layoutPo = new CustomRecomLayoutCondition();
                res = customRecomTempleteListDao.delete(condition);
                layoutPo.setTempleteId(condition.getTempleteId());
                resu = customRecomLayoutServicec.delete(layoutPo);
            }
            if (res != null && resu != null) {
                return 1;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    public CustomRecomTempleteListVo getTempleteById(int templeteId) {
        CustomRecomTempleteCondition templetePo = new CustomRecomTempleteCondition();
        CustomRecomTempleteListVo templeteVo = new CustomRecomTempleteListVo();
        if(templeteId != 0){
            templetePo.setTempleteId(templeteId);
            templeteVo = customRecomTempleteListDao.get(templetePo);
            String json = customRecomGetLayoutService.setTempleteJson(templeteId);
            templeteVo.setLayoutJson(json);
        }
        return templeteVo;
    }

    public JSONArray getTempleteJSONById(int templeteId) {
        List<CustomRecomLayoutVo> list = new ArrayList<CustomRecomLayoutVo>();
        JSONArray ja = new JSONArray();
        CustomRecomLayoutCondition t = new CustomRecomLayoutCondition();
        t.setTempleteId(templeteId);
        list = customRecomLayoutServicec.getList(t);
        for (CustomRecomLayoutVo layout : list) {
            ja.add(layout);
        }
        return ja;
    }

    public List<HashMap<String,String>> setTemplete(Integer templeteLeve){
        CustomRecomTempleteCondition t = new CustomRecomTempleteCondition();
        t.setTempleteLeve(templeteLeve);
        List<CustomRecomTempleteListVo> TempleteNameList = customRecomTempleteListDao.getFirstList(t);
        List<HashMap<String,String>> TempleteName = new ArrayList<HashMap<String,String>>();
        if(TempleteNameList!=null&&!TempleteNameList.isEmpty()){
            for (CustomRecomTempleteListVo templete : TempleteNameList) {
                HashMap<String,String> map = new HashMap<String,String>();
                map.put("templeteId", templete.getTempleteId()+"");
                if(templeteLeve==1){
                    map.put("templeteName", templete.getTempleteName());
                }else{
                    map.put("templeteName", templete.getContentName()+"");
                }
                TempleteName.add(map);
            }
        }else{
            HashMap<String,String> jb = new HashMap<String,String>();
            jb.put("templeteId","-1");
            jb.put("templeteName", "数据为空");
            TempleteName.add(jb);
        }
        return TempleteName;
    }

    public JSONArray getJsonById(int templeteId,int fatherId) {
        JSONArray templeteJson =  new JSONArray();
        if(templeteId != 0){
            templeteJson = customRecomGetLayoutService.setContentJson(templeteId,fatherId);
        }
        return templeteJson;
    }

    public JSONArray getContent(CustomRecomTempleteCondition condition){

        Integer templeteId = condition.getTempleteId();
        Integer fatherId = condition.getFatherId();
        JSONArray layoutJson = new JSONArray();
        if (templeteId == 0) {
            layoutJson = this.getTempleteJSONById(fatherId);
        } else {
            layoutJson = this.getJsonById(templeteId, fatherId);
        }
        return layoutJson;

    }

    public boolean updateTemplete(CustomRecomTempleteCondition condition) {
        boolean flag = false;
        Integer bool = customRecomTempleteListDao.selectFatherId(condition);
        if (bool == 0) {
            int count = customRecomTempleteListDao.update(condition);

            if (count > 0) {
                flag = true;
            }
        }
        return flag;
    }

    public Integer selectFatherId(CustomRecomTempleteCondition condition){
        return customRecomTempleteListDao.selectFatherId(condition);
    }

    public Integer updateIsEffective(CustomRecomTempleteCondition condition){
        return customRecomTempleteListDao.updateIsEffective(condition);
    }
    public CustomRecomTempleteListVo getByTempleteId(CustomRecomTempleteCondition condition){
        return customRecomTempleteListDao.get(condition);
    }
    public List<CustomRecomTempleteListVo> getFirstList(CustomRecomTempleteCondition customRecomTempleteCondition){
       return  customRecomTempleteListDao.getFirstList(customRecomTempleteCondition);
    }
}
