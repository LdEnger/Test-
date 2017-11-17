package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.portal.CustomRecomLayoutVo;
import cn.com.hiveview.entity.module.portal.PortalCustomRecomContentVo;
import cn.com.hiveview.launcherapi.module.portal.condition.CustomRecomLayoutCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.CustomRecomTempleteCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalCustomRecomContentCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalLauncherTempletTabCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.CustomRecomTempleteListDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalCustomRecomContentDao;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by user on 2017/8/2.
 */
@Service
public class CustomRecomGetLayoutService {

    @Autowired
    CustomRecomLayoutService customRecomLayoutService;

    @Autowired
    CustomRecomTempleteListService customRecomTempleteListService;

    @Autowired
    PortalCustomRecomContentDao portalCustomRecomContentDao;

    @Autowired
    CustomRecomTempleteListDao customRecomTempleteListDao;


    public List<CustomRecomLayoutCondition> getLayout(String layoutJson) {
        List<CustomRecomLayoutCondition> layouts = new ArrayList<CustomRecomLayoutCondition>();
        JSONArray t = JSON.parseArray(layoutJson);
        Iterator<Object> iterator = t.iterator();
        while (iterator.hasNext()) {
            JSONObject record = (JSONObject) iterator.next();
            CustomRecomLayoutCondition layout = this.setLayoutData(record);
            layouts.add(layout);
        }
        return layouts;
    }

    public CustomRecomLayoutCondition setLayoutData(JSONObject record) {
        int layoutId = record.getInteger("id");
        int layoutX = record.getInteger("col");
        int layoutY = record.getInteger("row");
        int layoutW = record.getInteger("size_x");
        int layoutH = record.getInteger("size_y");
        int positionSeq = 0;
        if(record.getInteger("positionSeq")!=null){
            positionSeq = record.getInteger("positionSeq");
        }
        CustomRecomLayoutCondition layout = new CustomRecomLayoutCondition();
        layout.setLayoutH(layoutH);
        layout.setLayoutId(layoutId);
        layout.setLayoutW(layoutW);
        layout.setLayoutX(layoutX);
        layout.setLayoutY(layoutY);
        layout.setPositionSeq(positionSeq);
        return layout;
    }

    public String setTempleteJson(int templeteId) {
        String layoutJson = "";
        CustomRecomLayoutCondition t = new CustomRecomLayoutCondition();
        t.setTempleteId(templeteId);
        List<CustomRecomLayoutVo> layouts = customRecomLayoutService.getList(t);
        layoutJson = JSON.toJSONString(layouts);
        System.out.println("layoutJson:" + layoutJson);
        return layoutJson;
    }

    public void orderLayout(Integer templeteId)throws Exception{
        CustomRecomTempleteCondition condition = new CustomRecomTempleteCondition();
        CustomRecomLayoutCondition layoutCondition = new CustomRecomLayoutCondition();
        layoutCondition.setTempleteId(templeteId);
        Integer layoutCount = customRecomLayoutService.getCount(layoutCondition);
        condition.setTempleteId(templeteId);
        condition.setLayoutCount(layoutCount);
        customRecomTempleteListDao.update(condition);
        List<CustomRecomLayoutVo> vo = customRecomLayoutService.getListByPosition(layoutCondition);
        Integer i = 1;
        for(CustomRecomLayoutVo vo1 : vo){
            vo1.setPositionSeq(i);
            i=i+1;
            if(vo1.getLayoutX()%2==0){
                vo1.setLayoutTeam((vo1.getLayoutX()-4)/6+1);
            }else{
                vo1.setLayoutTeam((vo1.getLayoutX()-1)/6+1);
            }
            if(vo1.getLayoutW().equals(vo1.getLayoutH()) && vo1.getLayoutW().equals(Integer.valueOf(6))){
                vo1.setLayoutTeamType(1);
            }else if((vo1.getLayoutW().equals(vo1.getLayoutH()) && vo1.getLayoutW().equals(Integer.valueOf(3)) && vo1.getLayoutY().equals(Integer.valueOf(1)))
                    || (!vo1.getLayoutW().equals(vo1.getLayoutH()) && vo1.getLayoutY().equals(Integer.valueOf(4))) ){
                vo1.setLayoutTeamType(2);
            }else if((vo1.getLayoutW().equals(vo1.getLayoutH()) && vo1.getLayoutW().equals(Integer.valueOf(3)) && vo1.getLayoutY().equals(Integer.valueOf(4)))
                    || (!vo1.getLayoutW().equals(vo1.getLayoutH()) && vo1.getLayoutY().equals(Integer.valueOf(1)))){
                vo1.setLayoutTeamType(3);
            }
            CustomRecomLayoutCondition conditionPo = new CustomRecomLayoutCondition();
            conditionPo.setLayoutId(vo1.getLayoutId());
            conditionPo.setPositionSeq(vo1.getPositionSeq());
            conditionPo.setLayoutTeam(vo1.getLayoutTeam());
            conditionPo.setLayoutTeamType(vo1.getLayoutTeamType());
            customRecomLayoutService.updateLayoutTeam(conditionPo);
        }

    }

    public List<PortalCustomRecomContentCondition> getContents(String layoutJson) {
        List<PortalCustomRecomContentCondition> contents = new ArrayList<PortalCustomRecomContentCondition>();
        JSONArray t = JSON.parseArray(layoutJson);
        Iterator<Object> iterator = t.iterator();
        while (iterator.hasNext()) {
            JSONObject record = (JSONObject) iterator.next();
            PortalCustomRecomContentCondition content = this.setContentData(record);
            contents.add(content);

        }
        return contents;
    }

    private PortalCustomRecomContentCondition setContentData(JSONObject record) {
        int layoutId = record.getInteger("id");
        String contentImg = record.getString("contentImg");
        String contentName = record.getString("contentName");
        Integer contentId=record.getInteger("contentId");
        Integer contentType=record.getInteger("contentType");
        Integer customContentImgSel=record.getInteger("customContentImgSel");
        String contentSubtitle=record.getString("contentSubtitle");
        String contentOutcropImg=record.getString("contentOutcropImg");
        String contentBigPic=record.getString("contentBigPic");
        Integer categoryId=record.getInteger("categoryId");
        Integer chnId=record.getInteger("chnId");
        Integer chnType=record.getInteger("chnType");
        Integer hotId=record.getInteger("hotId");
        Integer hotType=record.getInteger("hotType");
        Integer aqyIsVip=record.getInteger("aqyIsVip");
        String apkBagName=record.getString("apkBagName");
        String specSn=record.getString("specSn");
        String apkVersionCode=record.getString("apkVersionCode");
        String apkDownUrl=record.getString("apkDownUrl");
        String videoUrl=record.getString("videoUrl");
        Integer videoId=record.getInteger("videoId");
        String action=record.getString("action");
        String apk=record.getString("apk");
        Integer isPlay=record.getInteger("isPlay");
        Integer installStyle=record.getInteger("installStyle");
        Integer appType=record.getInteger("appType");

        PortalCustomRecomContentCondition content = new PortalCustomRecomContentCondition();
        content.setContentImg(contentImg);
        content.setContentName(contentName);
        content.setLayoutId(layoutId);
        content.setContentId(contentId);
        content.setCustomContentImgSel(customContentImgSel);
        content.setContentType(contentType);
        content.setContentSubtitle(contentSubtitle.equals("") ? null:contentSubtitle);
        content.setContentOutcropImg(contentOutcropImg.equals("") ? null:contentOutcropImg);
        content.setContentBigPic(contentBigPic.equals("") ? null:contentBigPic);
        content.setCategoryId(categoryId);
        content.setChnId(chnId);
        content.setChnType(chnType);
        content.setHotId(hotId);
        content.setHotType(hotType);
        content.setAqyIsVip(aqyIsVip);
        content.setApkBagName(apkBagName.equals("") ? null:apkBagName);
        content.setSpecSn(specSn);
        content.setApkVersionCode(apkVersionCode);
        content.setApkDownUrl(apkDownUrl);
        content.setVideoId(videoId);
        content.setVideoUrl(videoUrl);
        content.setAction(action);
        content.setApk(apk);
        content.setIsPlay(isPlay);
        content.setInstallStyle(installStyle);
        content.setAppType(appType);
        return content;
    }



    public JSONArray setContentJson(int templeteId,int fatherId) {
        JSONArray json = new JSONArray();
        CustomRecomLayoutCondition layout = new CustomRecomLayoutCondition();
        layout.setTempleteId(fatherId);
        List<CustomRecomLayoutVo> layouts = customRecomLayoutService.getList(layout);

        PortalCustomRecomContentCondition cont = new PortalCustomRecomContentCondition();
        cont.setRecomTempletId(templeteId);
        List<PortalCustomRecomContentVo> contents = portalCustomRecomContentDao.getList(cont);
        for (CustomRecomLayoutVo layoutVo : layouts) {
            for (PortalCustomRecomContentVo contentVo : contents) {
                if (contentVo.getLayoutId().equals(layoutVo.getLayoutId())) {
                    layoutVo.setTempleteId(contentVo.getTempleteId());
                    layoutVo.setContentId(contentVo.getContentId());
                    layoutVo.setContentName(contentVo.getContentName());
                    layoutVo.setContentSubtitle(contentVo.getContentSubtitle());
                    layoutVo.setContentType(contentVo.getContentType());
                    layoutVo.setContentImg(contentVo.getContentImg());
                    layoutVo.setContentOutcropImg(contentVo.getContentOutcropImg());
                    layoutVo.setContentBigPic(contentVo.getContentBigPic());
                    layoutVo.setCustomContentImgSel(contentVo.getCustomContentImgSel());
                    layoutVo.setCategoryId(contentVo.getCategoryId());
                    layoutVo.setChnId(contentVo.getChnId());
                    layoutVo.setChnType(contentVo.getChnType());
                    layoutVo.setHotId(contentVo.getHotId());
                    layoutVo.setHotType(contentVo.getHotType());
                    layoutVo.setApkBagName(contentVo.getApkBagName());
                    layoutVo.setAqyIsVip(contentVo.getAqyIsVip());
                    layoutVo.setTempleteId(1);
                    layoutVo.setSpecSn(contentVo.getSpecSn());
                    layoutVo.setApkVersionCode(contentVo.getApkVersionCode());
                    layoutVo.setApkDownUrl(contentVo.getApkDownUrl());
                    layoutVo.setVideoUrl(contentVo.getVideoUrl());
                    layoutVo.setVideoId(contentVo.getVideoId());
                    layoutVo.setAction(contentVo.getAction());
                    layoutVo.setApk(contentVo.getApk());
                    layoutVo.setIsPlay(contentVo.getIsPlay());
                    layoutVo.setInstallStyle(contentVo.getInstallStyle());
                    layoutVo.setAppType(contentVo.getAppType());
                }
            }
        }
        for (CustomRecomLayoutVo layoutVo : layouts) {
            json.add(layoutVo);
        }
        return json;
    }

}
