package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.*;
import cn.com.hiveview.launcherapi.module.portal.condition.*;
import cn.com.hiveview.launcherapi.module.portal.dao.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by user on 2017/7/21.
 */
@Service
public class PortalCustomRecomContentService {

    @Autowired
    private PortalCustomRecomContentDao portalCustomRecomContentDao;

    @Autowired
    private CustomRecomTempleteListDao customRecomTempleteListDao;

    @Autowired
    private CustomRecomGetLayoutService customRecomGetLayoutService;

    @Autowired
    private CustomRecomTempleteListService customRecomTempleteListService;

    @Autowired
    private PortalLauncherTempletContentService portalLauncherTempletCondition;

    @Autowired
    private CustomRecomBackupsContentService customRecomBackupsContentService;
    @Autowired
    private PortalTabGroupDao portalTabGroupDao;
    @Autowired
    private CustomRecomBackupsContentDao customRecomBackupsContentDao;
    @Autowired
    private UnbundlingDao unbundlingDao;
    @Autowired
    CustomRecomLayoutDao customRecomLayoutDao;
    @Autowired
    PortalTabGroupService portalTabGroupService;
    public List<PortalCustomRecomContentApiVo> getPortalCustomRecomContentList(PortalCustomRecomContentCondition condition){
        List<PortalCustomRecomContentApiVo> list = new ArrayList<PortalCustomRecomContentApiVo>();
        try{

            Integer pageIndex = condition.getPageIndex();
            Integer pageSize = condition.getPageSize();
            condition.setLayoutTeamIndex(pageIndex+1);
            condition.setLayoutTeamSize(pageSize+pageIndex);
            list = portalCustomRecomContentDao.getPortalCustomRecomContentAPIList(condition);

        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public Integer save (CustomRecomTempleteCondition customRecomTempleteCondition){
        Integer bool = null;
        try {
            List<PortalCustomRecomContentCondition> contents = new ArrayList<PortalCustomRecomContentCondition>();
            if (!StringUtils.isEmpty(customRecomTempleteCondition)) {
                CustomRecomTempleteCondition templeteCondition = new CustomRecomTempleteCondition();
                templeteCondition.setTempleteId(customRecomTempleteCondition.getFatherId());
                CustomRecomTempleteListVo templeteListVo = customRecomTempleteListDao.get(templeteCondition);
                customRecomTempleteCondition.setLayoutCount(templeteListVo.getLayoutCount());
                customRecomTempleteCondition.setTempleteLeve(2);
                customRecomTempleteCondition.setIsEffective(0);
                contents = customRecomGetLayoutService.getContents(customRecomTempleteCondition.getLayoutJson());
            }

            bool = this.addTempleteAndContent(
                    customRecomTempleteCondition, contents);
            if (bool>0) {
                int templeteId = customRecomTempleteCondition.getFatherId();
                CustomRecomTempleteCondition t = new CustomRecomTempleteCondition();
                t.setTempleteId(templeteId);
                t.setIsEffective(1);// 状态置为1，则矩阵模板不可再次修改
                customRecomTempleteListService.updateTemplete(t);//
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bool;
    }

    public Integer addTempleteAndContent(CustomRecomTempleteCondition customRecomTempleteCondition,
                                         List<PortalCustomRecomContentCondition> contents) {
        Integer suc = 0;
        if (!StringUtils.isEmpty(customRecomTempleteCondition)) {
            Integer templeteId = customRecomTempleteListDao.save(customRecomTempleteCondition); // 模板save
            if (templeteId != null) {
                for (PortalCustomRecomContentCondition content : contents) {
                    content.setRecomTempletId(customRecomTempleteCondition.getTempleteId());
                    suc = portalCustomRecomContentDao.save(content); // 布局方位save
                }
            }
        }
        return suc;
    }

    public Integer update(CustomRecomTempleteCondition customRecomTempleteCondition){
        List<PortalCustomRecomContentCondition> contents = new ArrayList<PortalCustomRecomContentCondition>();
        if (!StringUtils.isEmpty(customRecomTempleteCondition)) {
            CustomRecomTempleteCondition templeteCondition = new CustomRecomTempleteCondition();
            templeteCondition.setTempleteId(customRecomTempleteCondition.getFatherId());
            CustomRecomTempleteListVo templeteListVo = customRecomTempleteListDao.get(templeteCondition);
            customRecomTempleteCondition.setLayoutCount(templeteListVo.getLayoutCount());
            customRecomTempleteCondition.setTempleteLeve(2);
            contents = customRecomGetLayoutService.getContents(customRecomTempleteCondition.getLayoutJson());
        }

        return this.updateTempleteAndContent(customRecomTempleteCondition, contents);
    }

    public Integer updateTempleteAndContent(CustomRecomTempleteCondition customRecomTempleteCondition,
                                            List<PortalCustomRecomContentCondition> contents) {
        Integer suc = 0;
        if (!StringUtils.isEmpty(customRecomTempleteCondition)) {
            Integer count = customRecomTempleteListDao.update(customRecomTempleteCondition); // 模板save

            PortalCustomRecomContentCondition contentPo = new PortalCustomRecomContentCondition();
            Integer delCount = 0;
            Integer templeteId = 0;
            if (count > 0) {
                templeteId = customRecomTempleteCondition.getTempleteId();
                contentPo.setRecomTempletId(templeteId);
                delCount = portalCustomRecomContentDao.delete(contentPo);
            }
            List<CustomRecomTempleteListVo> list = customRecomTempleteListDao.getTempleteByFatherId(templeteId);
            for (PortalCustomRecomContentCondition content : contents) {
                content.setRecomTempletId(templeteId);
                suc = portalCustomRecomContentDao.save(content); // 布局方位save
            }
        }

        return suc;
    }

    public Integer delete(PortalCustomRecomContentCondition condition){

        return portalCustomRecomContentDao.delete(condition);
    }

    public Integer getTabCount(CustomRecomTempleteCondition condition){
        PortalTabGroupCondition portalTabGroupCondition = new PortalTabGroupCondition();
        Integer result = 0;
        System.out.println(condition.getTempleteId());
        if(condition.getTempleteId()!=null){
            portalTabGroupCondition.setBelongGroupId(condition.getTempleteId());
            portalTabGroupCondition.setGroupType(1);
            result = portalTabGroupDao.getCount(portalTabGroupCondition);
        }
        System.out.println(result);
        return result;
    }

    public Integer getCount(PortalCustomRecomContentCondition condition){
        return portalCustomRecomContentDao.getCount(condition);
    }

    public List<List<PortalCustomRecomContentVo>> customContent(UnbundlingCondition condition){
        List<List<PortalCustomRecomContentVo>> result = new ArrayList<List<PortalCustomRecomContentVo>>();
        PortalCustomRecomContentCondition portalCustomRecomContentCondition = new PortalCustomRecomContentCondition();
        portalCustomRecomContentCondition.setContentId(condition.getContentId());
        portalCustomRecomContentCondition.setContentType(condition.getContentType());
        List<PortalCustomRecomContentVo> portalCustomRecomContentVo = portalCustomRecomContentDao.getContentLayoutListByContentId(portalCustomRecomContentCondition);

        for (PortalCustomRecomContentVo recomContentVo : portalCustomRecomContentVo){
            PortalCustomRecomContentCondition recomContentCondition = new PortalCustomRecomContentCondition();
            recomContentCondition.setRecomTempletId(recomContentVo.getRecomTempletId());
            recomContentCondition.setLayoutW(recomContentVo.getLayoutW());
            recomContentCondition.setLayoutH(recomContentVo.getLayoutH());
            recomContentCondition.setPositionSeq(recomContentVo.getPositionSeq());
            List<PortalCustomRecomContentVo> contentVoList = portalCustomRecomContentDao.getContentListByTempletId(recomContentCondition);

            result.add(contentVoList);



        }
        return result;
    }

    public Integer updateContentById(List<List<PortalCustomRecomContentVo>> contentVoList ) throws Exception{
        PortalCustomRecomContentCondition customRecomContentCondition = new PortalCustomRecomContentCondition();
        for(List<PortalCustomRecomContentVo> listList : contentVoList){
            int jj = 0;
            for(int i = 0; i<listList.size();i++){
                Integer downContentId = listList.get(0).getContentId();
                PortalCustomRecomContentCondition recomContentCondition = new PortalCustomRecomContentCondition();

                if(jj<listList.size() && listList.size() != 1){
                    for(int j=jj;j<listList.size();j++){
                        if(listList.get(j).getContentId().equals(downContentId)){
                            recomContentCondition.setId(listList.get(j).getId());
                            Integer deleteResult = portalCustomRecomContentDao.delete(recomContentCondition);
                        }else{
                            recomContentCondition.setId(listList.get(j).getId());
                            recomContentCondition.setLayoutId(listList.get(i).getLayoutId());
                            portalCustomRecomContentDao.updateContentById(recomContentCondition);
                            jj = j+1;
                            break;
                        }

                    }
                }else{
                    this.saveSupplementalBackupData(listList.get(i));
                }
            }
        }
        return null;
    }

    public Integer saveSupplementalBackupData(PortalCustomRecomContentVo portalCustomRecomContentVo)throws Exception{

        Integer result = null;
        Integer layoutId =portalCustomRecomContentVo.getLayoutId();
        Integer recomTempletId = portalCustomRecomContentVo.getRecomTempletId();
        Integer layoutW = portalCustomRecomContentVo.getLayoutW();
        Integer layoutH = portalCustomRecomContentVo.getLayoutH();
        Integer layoutType = null;
        if (layoutW.equals(6) && layoutH.equals(6)){
            layoutType = 1;
        }else if (layoutW.equals(3) && layoutH.equals(3)){
            layoutType = 2;
        }else if(layoutW.equals(6) && layoutH.equals(3)){
            layoutType = 3;
        }
        CustomRecomBackupsContentCondition customRecomBackupsContentCondition = new CustomRecomBackupsContentCondition();
        customRecomBackupsContentCondition.setLayoutType(layoutType);
        /*customRecomBackupsContentCondition.setContentType(portalCustomRecomContentVo.getContentType());*/
        customRecomBackupsContentCondition.setIsEffective(1);
        CustomRecomBackupsContentVo customRecomBackupsContentVo = customRecomBackupsContentService.getBySeq(customRecomBackupsContentCondition);
        PortalCustomRecomContentCondition portalCustomRecomContentCondition = new PortalCustomRecomContentCondition();
        portalCustomRecomContentCondition.setLayoutId(layoutId);
        portalCustomRecomContentCondition.setRecomTempletId(recomTempletId);
        portalCustomRecomContentCondition.setContentId(customRecomBackupsContentVo.getContentId());
        portalCustomRecomContentCondition.setContentType(customRecomBackupsContentVo.getContentType());
        portalCustomRecomContentCondition.setContentName(customRecomBackupsContentVo.getContentName());
        portalCustomRecomContentCondition.setContentImg(customRecomBackupsContentVo.getContentImg());
        portalCustomRecomContentCondition.setContentOutcropImg(customRecomBackupsContentVo.getContentOutcropImg());
        portalCustomRecomContentCondition.setContentBigPic(customRecomBackupsContentVo.getContentBigPic());
        portalCustomRecomContentCondition.setCustomContentImgSel(customRecomBackupsContentVo.getCustomContentImgSel());
        portalCustomRecomContentCondition.setCategoryId(customRecomBackupsContentVo.getCategoryId());
        portalCustomRecomContentCondition.setChnId(customRecomBackupsContentVo.getChnId());
        portalCustomRecomContentCondition.setChnType(customRecomBackupsContentVo.getChnType());
        portalCustomRecomContentCondition.setHotId(customRecomBackupsContentVo.getHotId());
        portalCustomRecomContentCondition.setHotType(customRecomBackupsContentVo.getHotType());
        portalCustomRecomContentCondition.setApkBagName(customRecomBackupsContentVo.getApkBagName());
        portalCustomRecomContentCondition.setTempleteId(customRecomBackupsContentVo.getTempleteId());
        portalCustomRecomContentCondition.setAqyIsVip(customRecomBackupsContentVo.getAqyIsVip());
        result = portalCustomRecomContentDao.save(portalCustomRecomContentCondition);
        if(result > 0){
            CustomRecomBackupsContentCondition backupsContentCondition = new CustomRecomBackupsContentCondition();
            backupsContentCondition.setId(customRecomBackupsContentVo.getId());
            backupsContentCondition.setLayoutType(layoutType);
           /* backupsContentCondition.setIsEffective(0);*/
            Integer seq = 1;
            Integer minSeq = customRecomBackupsContentService.getMinSeq(backupsContentCondition);
            if(minSeq != null){
                seq = minSeq - 1;
            }
            backupsContentCondition.setSeq(seq);
            customRecomBackupsContentService.update(backupsContentCondition);
        }
        return result;

    }
    public Integer saveCopyContent(CustomRecomTempleteCondition customRecomTempleteCondition){
        //System.out.println(customRecomTempleteCondition);
        PortalCustomRecomContentCondition customRecomContentCondition = new PortalCustomRecomContentCondition();
        //System.out.println(customRecomTempleteCondition.getTempleteId());
        customRecomContentCondition.setRecomTempletId(customRecomTempleteCondition.getTempleteId());
        Integer result = null ;
        Integer templeteId = customRecomTempleteListDao.save(customRecomTempleteCondition);
        System.out.println(templeteId);
        /*if(templeteId>0){
                return templeteId;
        }*/

        //System.out.println(customRecomContentCondition);
        PortalCustomRecomContentCondition condition = new PortalCustomRecomContentCondition();
        List<PortalCustomRecomContentVo>  getByTempleteId = portalCustomRecomContentDao.getList(customRecomContentCondition);
        System.out.println(getByTempleteId.size());
        if(getByTempleteId.size() == 0){
            return 1;
        }
        for(int i=0;i<getByTempleteId.size();i++){
            System.out.println(getByTempleteId.get(i));
            condition.setSeq(getByTempleteId.get(i).getSeq());
            condition.setContentSubtitle(getByTempleteId.get(i).getContentSubtitle());
            condition.setVideoTempletId(getByTempleteId.get(i).getVideoTempletId());
            condition.setVideoId(getByTempleteId.get(i).getVideoId());
            condition.setCreateTime(getByTempleteId.get(i).getCreateTime());
            condition.setUpdateTime(getByTempleteId.get(i).getUpdateTime());
            condition.setLayoutId(getByTempleteId.get(i).getLayoutId());
            condition.setIsEffective(getByTempleteId.get(i).getIsEffective());
            condition.setContentId(getByTempleteId.get(i).getContentId());
            condition.setContentType(getByTempleteId.get(i).getContentType());
            condition.setContentName(getByTempleteId.get(i).getContentName());
            condition.setContentImg(getByTempleteId.get(i).getContentImg());
            condition.setContentOutcropImg(getByTempleteId.get(i).getContentOutcropImg());
            condition.setContentBigPic(getByTempleteId.get(i).getContentBigPic());
            condition.setCustomContentImgSel(getByTempleteId.get(i).getCustomContentImgSel());
            condition.setCategoryId(getByTempleteId.get(i).getCategoryId());
            condition.setChnId(getByTempleteId.get(i).getChnId());
            condition.setChnType(getByTempleteId.get(i).getChnType());
            condition.setHotId(getByTempleteId.get(i).getHotId());
            condition.setHotType(getByTempleteId.get(i).getHotType());
            condition.setApkBagName(getByTempleteId.get(i).getApkBagName());
            condition.setTempleteId(getByTempleteId.get(i).getTempleteId());
            condition.setAqyIsVip(getByTempleteId.get(i).getAqyIsVip());
            condition.setRecomTempletId(customRecomTempleteCondition.getTempleteId());
            condition.setIsPlay(getByTempleteId.get(i).getIsPlay());
            condition.setAction(getByTempleteId.get(i).getAction());
            condition.setApk(getByTempleteId.get(i).getApk());
            condition.setApkVersionCode(getByTempleteId.get(i).getApkVersionCode());
            condition.setApkDownUrl(getByTempleteId.get(i).getApkDownUrl());
            condition.setSpecSn(getByTempleteId.get(i).getSpecSn());
            condition.setVideoUrl(getByTempleteId.get(i).getVideoUrl());
            result = portalCustomRecomContentDao.saveCopyContent(condition);
        if(result == 0){
            return result;
        }
        }
        return result;
    }

    public Integer delNotice(UnbundlingCondition condition){
        List<List<PortalCustomRecomContentVo>> result = new ArrayList<List<PortalCustomRecomContentVo>>();
        PortalCustomRecomContentCondition portalCustomRecomContentCondition = new PortalCustomRecomContentCondition();
        portalCustomRecomContentCondition.setContentId(condition.getContentId());
        portalCustomRecomContentCondition.setContentType(condition.getContentType());
        List<PortalCustomRecomContentVo> portalCustomRecomContentVo = portalCustomRecomContentDao.getContentLayoutListByContentId(portalCustomRecomContentCondition);

        for (PortalCustomRecomContentVo recomContentVo : portalCustomRecomContentVo){
            CustomRecomBackupsContentCondition backUpCondition = new CustomRecomBackupsContentCondition();
            Integer id = recomContentVo.getId();
            Integer templetId = recomContentVo.getRecomTempletId();
            Integer layoutW = recomContentVo.getLayoutW();
            Integer layoutH = recomContentVo.getLayoutH();
            Integer layoutId= recomContentVo.getLayoutId();
            backUpCondition.setTempleteId(templetId);
            backUpCondition.setLayoutW(layoutW);
            backUpCondition.setLayoutH(layoutH);
            backUpCondition.setLayoutId(layoutId);
            //备份过来的如果是视频，需要判断是不是已经超过2个视频位置
            CustomRecomBackupsContentVo v =customRecomBackupsContentDao.getDataIfPlay(backUpCondition);
            if( v !=null && v.getIsPlay() !=null && v.getIsPlay() == 1){
                PortalTabGroupCondition chVideo = new PortalTabGroupCondition();
                chVideo.setBelongGroupId(templetId);
                if(recomContentVo.getIsPlay() !=1){
                    boolean flag = portalTabGroupService.backUpVideo(chVideo);
                    if(!flag){
                        continue;
                    }else{
                        PortalCustomRecomContentCondition thirdStep = new PortalCustomRecomContentCondition();
                        thirdStep.setRecomTempletId(templetId);
                        Integer count = portalCustomRecomContentDao.getVideoCount(thirdStep);
                        if(count >=1){
                            continue;
                        }
                    }
                }
            }
            //select 备份  into  内容表
            Integer  flag = customRecomBackupsContentDao.insertBackUpList(backUpCondition);
            //再把备份库里的表数据次数+1
            customRecomBackupsContentDao.updateIsUseredCount(backUpCondition);
            //删除下线的折腾数据通过id
            if(flag > 0){
                condition.setGroupId(templetId);
                condition.setId(id);
                this.unbundlingDao.deleteCustomRecomContent(condition);
            }
        }
        return 1;
    }
    public PortalCustomRecomContentCondition selectRowCol(CustomRecomBackupsContentCondition customRecomBackupsContentCondition){
        CustomRecomLayoutCondition customRecomLayoutCondition = new CustomRecomLayoutCondition();
        customRecomLayoutCondition.setLayoutId(customRecomBackupsContentCondition.getLayoutId());
        CustomRecomLayoutVo customRecomLayoutVo =  customRecomLayoutDao.get(customRecomLayoutCondition);
        System.out.println("111"+customRecomLayoutVo);
        PortalCustomRecomContentCondition portalCustomRecomContentCondition1 = new PortalCustomRecomContentCondition();
        if(customRecomLayoutVo == null){
            portalCustomRecomContentCondition1.setCol(0);
            portalCustomRecomContentCondition1.setRow(0);
            portalCustomRecomContentCondition1.setLayoutW(0);
            portalCustomRecomContentCondition1.setLayoutH(0);
        }else{
            portalCustomRecomContentCondition1.setCol(customRecomLayoutVo.getCol());
            portalCustomRecomContentCondition1.setRow(customRecomLayoutVo.getRow());
            portalCustomRecomContentCondition1.setId(customRecomBackupsContentCondition.getId());
            portalCustomRecomContentCondition1.setLayoutW(customRecomLayoutVo.getLayoutW());
            portalCustomRecomContentCondition1.setLayoutH(customRecomLayoutVo.getLayoutH());
        }
        return portalCustomRecomContentCondition1;
    }

    public ScriptPage<PortalCustomRecomContentCondition> getPageList(PortalCustomRecomContentCondition condition){
        ScriptPage<PortalCustomRecomContentCondition> scriptPage = new ScriptPage<PortalCustomRecomContentCondition>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<PortalCustomRecomContentCondition> rows = this.portalCustomRecomContentDao.getPageList(condition);
        int total = this.portalCustomRecomContentDao.getCount(condition);
        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        return scriptPage;
    }
}
