package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.core.util.Constants;
import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.*;
import cn.com.hiveview.launcherapi.module.portal.condition.*;
import cn.com.hiveview.launcherapi.module.portal.dao.NewTempletApkChannelDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalDataGroupDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalFixedRecommendContentDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalTabGroupDao;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 2017/10/9.
 */
@Service
public class PortalDataGroupService {

    @Autowired
    private PortalDataGroupDao portalDataGroupDao;
    @Autowired
    private PortalFixedRecommendContentDao portalFixedRecommendContentDao;
    @Autowired
    private NewContentChanService newContentChanService;

    @Autowired
    private NewTempletApkChannelDao newTempletApkChannelDao;

    @Autowired
    private PortalTabGroupService portalTabGroupService;

    @Autowired
    private PortalTabGroupDao portalTabGroupDao;
    @Autowired
    private AppService appService;

    public ScriptPage<PortalDataGroupListVo> getPage(PortalDataGroupListCondition condition) throws Exception {
        ScriptPage<PortalDataGroupListVo> scriptPage = new ScriptPage<>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<PortalDataGroupListVo> rows = this.portalDataGroupDao.getPage(condition);
        Integer total = this.portalDataGroupDao.getCount(condition);
        scriptPage.setTotal(total);
        scriptPage.setRows(rows);
        return scriptPage;
    }

    public Integer delete(PortalDataGroupListCondition condition) throws Exception {
        PortalTabGroupCondition tabGroupCondition = new PortalTabGroupCondition();
        tabGroupCondition.setBelongGroupId(condition.getId());
        tabGroupCondition.setGroupType(3);
        Integer count = portalTabGroupDao.getCount(tabGroupCondition);
        if (count > 0) {
            return -1;
        }
        return this.portalDataGroupDao.delete(condition);
    }


    public Integer save(PortalDataGroupListCondition condition) throws Exception {

        if (null != condition.getContentType() && condition.getContentType() == 0) {
            if (condition.getPackageName() == 2) {
                condition.setChannel(null);
            }
        }
        if (null != condition.getContentType() && condition.getContentType() == 2) {
            NewTempletApkChannelCondition apkCondition = new NewTempletApkChannelCondition();
            apkCondition.setId(condition.getCategoryId());
            apkCondition.setApkBagName(condition.getApkPackageName());
            List<NewTempletApkChannelListVo> channelTyepe = newTempletApkChannelDao.getChannelPage(apkCondition);
            NewTempletApkChannelListVo n = channelTyepe.get(0);
            condition.setChannelType(n.getCType());
        }
        return this.portalDataGroupDao.save(condition);
    }

    public Integer updateEffective(PortalDataGroupListCondition condition) throws Exception {
        if (condition.getEffective() == 0) {
            PortalTabGroupCondition condition1 = new PortalTabGroupCondition();
            condition1.setGroupType(3);
            condition1.setBelongGroupId(condition.getId());
            this.portalTabGroupDao.delete(condition1);
        }
        return this.portalDataGroupDao.updateEffective(condition);
    }

    public Integer update(PortalDataGroupListCondition condition) throws Exception {
        if (null != condition.getHotId()) {
            PortalTabGroupCondition tabGroupCondition = new PortalTabGroupCondition();
            tabGroupCondition.setHotId(condition.getHotId());
            tabGroupCondition.setApkBagName(condition.getApkPackageName());
            tabGroupCondition.setChnId(condition.getChannel());
            tabGroupCondition.setBelongGroupId(condition.getId());
            portalTabGroupService.update(tabGroupCondition);
        }
        if (null != condition.getContentType() && condition.getContentType() == 0) {
            if (condition.getPackageName() == 2) {
                condition.setChannel(null);
/*                PortalTabGroupCondition tabGroupCondition = new PortalTabGroupCondition();
                tabGroupCondition*/
            }
        }
        if (null != condition.getContentType() && condition.getContentType() == 2) {
            NewTempletApkChannelCondition apkCodition = new NewTempletApkChannelCondition();
            apkCodition.setApkBagName(condition.getApkPackageName());
            apkCodition.setId(condition.getCategoryId());
            List<NewTempletApkChannelListVo> channelTyepe = newTempletApkChannelDao.getChannelPage(apkCodition);
            NewTempletApkChannelListVo n = channelTyepe.get(0);
            condition.setChannelType(n.getCType());

        } else {
            condition.setChannelType(null);
        }

        return this.portalDataGroupDao.update(condition);
    }

    public PortalDataGroupListVo getGroupVo(PortalDataGroupListCondition condition) throws Exception {
        return this.portalDataGroupDao.getGroupVo(condition);
    }

    public List<PortalFixedRecommendContentVo> getDataGroupList(PortalFixedRecomContentCondition condition, Integer userId, String mac, String sn, int type) throws Exception {
        List<PortalFixedRecommendContentVo> resultList = new ArrayList<PortalFixedRecommendContentVo>();
        List<PortalFixedRecommendContentVo> list = new ArrayList<PortalFixedRecommendContentVo>();
        PortalDataGroupListCondition content = new PortalDataGroupListCondition();
        String url = "";
        //1:通过固定推荐位id查到基本信息
        //2 当为 1频道推荐类型时  可能是大麦影视的 也有可能时应用游戏  类型不同请求的猜你喜欢接口拼接参数不同
        // 当为 2频道内容类型时  通过包名和频道以及频道类型 直接返回客户端
        // 当为 3热词类型时  通过包名和频道和热词id 直接返回客户端
        //频道内容类型和热词类型 合并到grouplist接口中
        try {
            content.setId(condition.getFixedRecomId());
            PortalDataGroupListVo groupInfo = portalDataGroupDao.getGroupInfo(content);
            if (groupInfo != null && groupInfo.getPackageName() != null) {
                Integer operateContent = groupInfo.getPackageName();
                Integer categoryId = groupInfo.getCategoryId();
                Integer chnId = groupInfo.getChannel();
                list = this.portalFixedRecommendContentDao.getFixedRecommendContentList(condition);
                //包名(0应用游戏,1大麦影视)
                String typeRecom;
                // 2应用游戏,1大麦影视
                if (operateContent == 1) {
                    typeRecom = "video";
                    if (chnId == null) {
                        return resultList;
                    }
                } else {
                    //应用的接口
                    typeRecom = "app";
                    if (categoryId == null) {
                        return resultList;
                    }
                    chnId = categoryId;
                }
//                url = "http://api.recom.pthv.gitv.tv/distr_recom/data/getRecomVideosByUser.json?mac="+mac+"&sn="+sn+"&userId="+userId+"&pageSize=50&pageNum=1&chnnel="+chnId;
                url = Constants.RECOM_USER_URL + "?type=" + typeRecom + "&mac=" + mac + "&sn=" + sn + "&userId=" + userId + "&chnnel=" + chnId;

                List<String> chanlist = null;
                String jsonRes = Http.get(url, "UTF-8");
                if (!StringUtils.isEmpty(jsonRes)) {
                    JSONObject jsonString = JSONObject.parseObject(jsonRes);
                    if (jsonString != null) {
                        String data = jsonString.getString("desc");
                        if (!StringUtils.isEmpty(data)) {
                            chanlist = Arrays.asList(data.replaceAll(" ", "").split(","));
                        }
                    }
                }
                 /* 猜你喜欢数据去重*/
                List<Integer> programsetIdList = new ArrayList<>();
                if (!StringUtils.isEmpty(chanlist)) {
                    for (String contentId : chanlist) {
                        boolean flag = true;
                        Integer programsetId = Integer.valueOf(contentId);
                        for (PortalFixedRecommendContentVo fixRecomList : list) {
                            if (fixRecomList.getContentId().equals(programsetId)) {
                                flag = false;
                            }
                        }
                        if (flag) {
                            programsetIdList.add(programsetId);
                        }
                    }
                }
                if (programsetIdList.size() > 0) {
                    if (operateContent == 1) {
                        NewContentChanCondition chanCondition = new NewContentChanCondition();
                        chanCondition.setProgramsetIdList(programsetIdList);
                        chanCondition.setApkBagName("com.hiveview.cloudscreen.vipvideo");
                        chanCondition.setStatus(1);
                        List<NewContentChanList> chanVolist = newContentChanService.getDataGroupList(chanCondition);
                        if (chanVolist != null && chanVolist.size() > 0) {
                            for (NewContentChanList chanVo : chanVolist) {
                                PortalFixedRecommendContentVo portalFixedRecommendContentVo = new PortalFixedRecommendContentVo();
                                portalFixedRecommendContentVo.setContentId(chanVo.getProgramsetId());
                                portalFixedRecommendContentVo.setContentType(1);
                                portalFixedRecommendContentVo.setContentName(chanVo.getAlbumName());
                                portalFixedRecommendContentVo.setApkBagName("com.hiveview.cloudscreen.vipvideo");
                                portalFixedRecommendContentVo.setChnId(chanVo.getChnId());
                                portalFixedRecommendContentVo.setChnType(chanVo.getChnType());
                                portalFixedRecommendContentVo.setContentImg(chanVo.getAlbumXqyPic());
                                portalFixedRecommendContentVo.setFixedRecomId(condition.getFixedRecomId());
                                portalFixedRecommendContentVo.setTempletId(chanVo.getTempletId());
                                portalFixedRecommendContentVo.setQiyiIsVip(chanVo.getAqyIsVip());
                                list.add(portalFixedRecommendContentVo);
                            }
                        }
                    } else {
                        //应用
                        AppCondition appCondition = new AppCondition();
                        appCondition.setAppIds(programsetIdList);
                        appCondition.setState(1);
                        appCondition.setCategoryId(chnId);
                        List<AppVo> appVoList = appService.getDataGroupList(appCondition);
                        if (appVoList != null && appVoList.size() > 0) {
                            for (AppVo appVo : appVoList) {
                                PortalFixedRecommendContentVo portalFixedRecommendContentVo = new PortalFixedRecommendContentVo();
                                portalFixedRecommendContentVo.setContentId(appVo.getAppId());
                                portalFixedRecommendContentVo.setContentType(2);
                                portalFixedRecommendContentVo.setContentName(appVo.getAppName());
                                portalFixedRecommendContentVo.setContentImg(appVo.getAppLongIcon());
                                portalFixedRecommendContentVo.setFixedRecomId(condition.getFixedRecomId());
                                portalFixedRecommendContentVo.setTempletId(1);
                                portalFixedRecommendContentVo.setAppCategory(appVo.getCategoryId());
                                list.add(portalFixedRecommendContentVo);
                            }
                        }
                    }
                    System.gc();
                }

            /*分页*/
                for (int i = 0; i < condition.getPageSize() && i < list.size() - condition.getPageIndex(); i++) {
                    PortalFixedRecommendContentVo result = list.get(condition.getPageIndex() + i);
                    resultList.add(result);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }
}

