package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.core.util.Constants;
import cn.com.hiveview.core.util.HttpUtils;
import cn.com.hiveview.entity.module.portal.*;
import cn.com.hiveview.launcherapi.module.portal.condition.AreaGroupCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.EntranceAreaCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalLauncherTempletCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.AreaGroupDao;
import cn.com.hiveview.launcherapi.module.portal.dao.EntranceAreaDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalLauncherTempletDao;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by admin on 2017/8/2.
 */
@Service
public class EntranceAreaService {
    @Autowired
    private EntranceAreaDao entranceAreaDao;
    @Autowired
    private AreaGroupDao areaGroupDao;
    @Autowired
    PortalLauncherTempletDao portalLauncherTempletDao;
    public List<AreaCodeList> getAreaGroupList(){
        List<AreaCodeList> result = new ArrayList<AreaCodeList>();
        List<AreaGroupList> ags = this.areaGroupDao.getAreaGroupList(new AreaGroupCondition());
        for(AreaGroupList ag :ags){
            AreaCodeList va = new AreaCodeList();
            va.setCode(ag.getAreaCode());
            va.setName(ag.getAreaName());
            va.setType(1);
            result.add(va);
        }
        return result;
    }
    public List<AreaCodeList> getEquipments(){
        List<AreaCodeList> result = new ArrayList<AreaCodeList>();
        List<EquipmentInfoList> eList = equipments();
        for(EquipmentInfoList  eq : eList){
            AreaCodeList va = new AreaCodeList();
            va.setCode(eq.getEquipmentNo());
            va.setName(eq.getEquipmentNo());
            result.add(va);
        }
        //删除数据去重
        for  ( int  i  =   0 ; i  <  result.size()  -   1 ; i ++ )  {
            for  ( int  j  =  result.size()  -   1 ; j  >  i; j -- )  {
                if  (result.get(j).getCode().equals(result.get(i).getCode()))  {
                    result.remove(j);
                }
            }
        }
        return result;
    }
    public List<EquipmentInfoList> equipments() {
        List<EquipmentInfoList> list = null;
        try {
            String jsonRes = HttpUtils.doGet(Constants.GET_EQUMENTS_TYPE_URL);
            if (!StringUtils.isEmpty(jsonRes)) {
                JSONObject jsonString = JSONObject.parseObject(jsonRes);
                if (jsonString != null) {
                    String data = jsonString.getString("obj");
                    if(!StringUtils.isEmpty(data)){
                        list = JSONArray.parseArray(data, EquipmentInfoList.class);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<AreaCodeList> getArea(String type, String code) {
        /**
         * 这个接口给多个页面中的级联省市匡提供数据，原来type来区分省市，现在用了区分不同的模块
         */
        //判断type
        List<AreaCodeList> result = new ArrayList<AreaCodeList>();
        //虚拟个全国字段
        if (com.alibaba.druid.util.StringUtils.equals(type, "search")) {
            if (code.equals("00")) {
                result.add(new AreaCodeList("00000", "请选择", 0));
                result.add(new AreaCodeList("00", "全国", 0));
            } else {
                result.add(new AreaCodeList("00000", "请选择", 1));
                result.add(new AreaCodeList("00", "全部", 1));
            }
        } else if( com.alibaba.druid.util.StringUtils.equals(type,"tab") ) {
            if (code.equals("00")) {
                result.add(new AreaCodeList("00", "全国", 0));
            } else {
                result.add(new AreaCodeList("00", "全部", 1));
            }
        } else if(com.alibaba.druid.util.StringUtils.equals(type, "productPack")){
            result.add(new AreaCodeList("00000", "请选择", 1));
        }else if( com.alibaba.druid.util.StringUtils.equals(type,"batch") ) {
            if (code.equals("00")) {
            } else {
                result.add(new AreaCodeList("00", "全部", 1));
            }
        }
        List<AreaCodeList> tmp = null;

        String areaCodeUrl = MessageFormat.format(Constants.CAROUSEL_TV_AREA_CODE_URL, com.alibaba.druid.util.StringUtils.isEmpty(code) ? "" : code);
        String jsonRes = HttpUtils.doGet(areaCodeUrl);

        if (!com.alibaba.druid.util.StringUtils.isEmpty(jsonRes)) {
            JSONObject jsonObj = JSONObject.parseObject(jsonRes);
            String status = jsonObj.getString("code");
            if (status.equals("N000000")) {
                String areas = jsonObj.getString("result");
                tmp = JSONArray.parseArray(areas, AreaCodeList.class);
                result.addAll(tmp);
            }
        }
        return result;
    }

    public List<EntranceAreaList> getCityByLogoId(EntranceAreaCondition t)
    {
        List<EntranceAreaList> cityList =entranceAreaDao.getCityByAreaId(t);
        return cityList;
    }
    public List<AreaCodeList> getRomList(EntranceAreaCondition t){
        List<AreaCodeList> result = new ArrayList<AreaCodeList>();
        List<EntranceAreaList> romList = this.entranceAreaDao.getCityByAreaId(t);
        for(EntranceAreaList  eq : romList){
            AreaCodeList va = new AreaCodeList();
            va.setCode(eq.getCityId());
            va.setName(eq.getCityId());
            result.add(va);
        }
        return result;
    }

    public Integer add(EntranceAreaCondition condition) throws Exception {
        return this.entranceAreaDao.add(condition);
    }
    public Integer update(EntranceAreaCondition condition) throws Exception {
        String cityNames ="";
        Integer suc=0;
        //先删除
        entranceAreaDao.deleteCitys(condition);
        //批量新增
        String citys=condition.getCitys();
        String names=condition.getCityNames();
        if(!StringUtils.isEmpty(citys)){
            String [] citysArr=citys.split(",");
            String [] cityNamesArr=names.split(",");
            for(int i=0;i<citysArr.length;i++){
                EntranceAreaCondition logo=new EntranceAreaCondition();
                logo.setCityId(citysArr[i]);
                logo.setAreaId(condition.getAreaId());
                logo.setControllerType(condition.getControllerType());
                if(condition.getControllerType() == 5){
                    cityNames =cityNames +cityNamesArr[i]+",";
                    suc = entranceAreaDao.add(logo);
                }else{
                    //判重
                    if(this.entranceAreaDao.ifCount(logo) == 0){
                        cityNames =cityNames +cityNamesArr[i]+",";
                        suc = entranceAreaDao.add(logo);
                    }
                }
            }
            if(!StringUtils.isEmpty(cityNames)){
                cityNames = cityNames.substring(0,cityNames.length()-1);
                //如果是硬件型号+ip 则不可被同时关联到一个模板中
                PortalLauncherTempletCondition temp =new PortalLauncherTempletCondition();
                temp.setType(5);
                List<PortalLauncherTempletList> list = portalLauncherTempletDao.getComboboxList(temp);
                String [] arr1 = cityNames.split(",");
                List left =Arrays.asList(arr1);
                for(PortalLauncherTempletList t:list){
                       String  from=  t.getCityNames();
                       String []  arr2=from.split(",");
                       if(arr1.length == arr2.length){
                           List right =Arrays.asList(arr2);
                           boolean  flag = left.containsAll(right);
                           if(flag && t.getId()!=condition.getAreaId()){
                               return -2;
                           }
                       }
                }

            }else{
                return -2;
            }
        }
        //去操作模板表
        PortalLauncherTempletCondition  templetCondition =new PortalLauncherTempletCondition();
        templetCondition.setType(condition.getControllerType());
        templetCondition.setCityNames(cityNames);
        templetCondition.setId(condition.getAreaId());
        portalLauncherTempletDao.update(templetCondition);
        return suc;
    }
}
