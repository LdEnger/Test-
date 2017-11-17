package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.*;
import cn.com.hiveview.launcherapi.module.portal.condition.AppCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.NewContentChanCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalFixedRecomContentCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalFixedRecomListCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalFixedRecommendContentDao;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by user on 2017/7/20.
 */
@Service
public class PortalFixedRecommendContentService {

    @Autowired
    private ShardedJedisPool shardedJedisPool;
    private static String fixKey = "userid_:";
    @Autowired
    private PortalFixedRecommendContentDao portalFixedRecommendContentDao;
    @Autowired
    private NewContentChanService newContentChanService;
    @Autowired
    private AppService appService;
    @Autowired
    private PortalFixedRecomListService portalFixedRecomListService;
    @Autowired
    private RedisService redisService;

    public ScriptPage<PortalFixedRecommendContentVo> getList(PortalFixedRecomContentCondition condition) throws Exception {
        ScriptPage<PortalFixedRecommendContentVo> scriptPage = new ScriptPage<PortalFixedRecommendContentVo>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<PortalFixedRecommendContentVo> rows = this.portalFixedRecommendContentDao.getList(condition);
        int total = this.portalFixedRecommendContentDao.getCount(condition);
        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        return scriptPage;
    }
    public ScriptPage<PortalFixedRecommendContentVo> getFixedRecommendContentList(PortalFixedRecomContentCondition condition) throws Exception {
        ScriptPage<PortalFixedRecommendContentVo> scriptPage = new ScriptPage<PortalFixedRecommendContentVo>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());

        List<PortalFixedRecommendContentVo> rows = this.portalFixedRecommendContentDao.getFixedRecommendContentList(condition);

        int total = this.portalFixedRecommendContentDao.getFixedRecommendContentCount(condition);

        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        return scriptPage;
    }

    public Integer save(PortalFixedRecomContentCondition condition) throws Exception {
        if(portalFixedRecommendContentDao.getFixedRecommendContentCountByContentId(condition)>0){
            return -2;
        }else {
            try {
                if (condition.getOperateContent() != null && condition.getOperateContent().equals(Integer.valueOf(1))) {
                    NewContentChanCondition newContentChanCondition = new NewContentChanCondition();
                    newContentChanCondition.setProgramsetId(condition.getContentId());
                    newContentChanCondition.setApkBagName("com.hiveview.cloudscreen.vipvideo");
                    newContentChanCondition.setStatus(1);
                    if (condition.getContentId() != null) {
                        NewContentChanList newContentChanList = newContentChanService.get(newContentChanCondition);
                        condition.setContentType(1);
                        if (newContentChanList != null) {
                            condition.setChnId(newContentChanList.getChnId());
                            condition.setChnType(newContentChanList.getChnType());
                            condition.setApkBagName(newContentChanList.getApkBagName());
                            condition.setTempletId(newContentChanList.getTempletId());
                            condition.setQiyiIsVip(newContentChanList.getAqyIsVip());
                        }
                    }
                } else if (condition.getOperateContent() != null && condition.getOperateContent().equals(Integer.valueOf(2))) {
                    AppCondition appCondition = new AppCondition();
                    appCondition.setAppId(condition.getContentId());
                    AppVo appVo = appService.get(appCondition);
                    condition.setContentType(11);
                    if (appVo != null) {
                        condition.setAppCategory(appVo.getCategoryId());
                    }
                }
                Integer seq = portalFixedRecommendContentDao.getMaxSeq(condition);
                seq = seq == null ? 1 : seq + 1;
                condition.setSeq(seq);
                return this.portalFixedRecommendContentDao.save(condition);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
    public Integer delete(PortalFixedRecomContentCondition condition) throws Exception {
        return this.portalFixedRecommendContentDao.delete(condition);
    }
    public Integer update(PortalFixedRecomContentCondition condition) throws Exception {
        System.out.println(condition);
       if( condition.getContentId() == null){
           return this.portalFixedRecommendContentDao.update(condition);
       }else{
        if(portalFixedRecommendContentDao.getFixedRecommendContentCountByContentId(condition)>0){
            return -2;
        }else{
        return this.portalFixedRecommendContentDao.update(condition);}
       }

    }

    public PortalFixedRecommendContentVo getFixedRecommendContent(PortalFixedRecomContentCondition condition) throws Exception{
        return this.portalFixedRecommendContentDao.getFixedRecommendContent(condition);
    }

    public List<PortalFixedRecommendContentVo> getPortalFixedRecomContentList(PortalFixedRecomContentCondition condition,Integer userId,String mac,String sn)throws Exception{
        List<PortalFixedRecommendContentVo> list = new ArrayList<PortalFixedRecommendContentVo>();
        List<PortalFixedRecommendContentVo> resultList = new ArrayList<PortalFixedRecommendContentVo>();
        PortalFixedRecomListCondition listCondition = new PortalFixedRecomListCondition();
        String url = "http://api.recom.pthv.gitv.tv/distr_recom/data/getRecomVideosByUser.json?mac="+mac+"&sn="+sn+"&userId="+userId+"&pageSize=100&pageNum=1";
        Integer chnId = null;
        try{
            listCondition.setRecomId(condition.getFixedRecomId());
            PortalFiexdRecomListVo listVo= portalFixedRecomListService.getFixedRecomListOne(listCondition);
            if(listVo!=null){
                Integer operateContent = listVo.getOperateContent();
                chnId = listVo.getChnId();
                if(operateContent == 2){
                    list = this.portalFixedRecommendContentDao.getFixedRecommendContentList(condition);
                    return list;
                }else if(operateContent == 1){
                    if(listVo.getOperateType() == 0){
                        list = this.portalFixedRecommendContentDao.getFixedRecommendContentList(condition);
                    }
                }
                if(chnId!=null){
                    url = "http://api.recom.pthv.gitv.tv/distr_recom/data/getRecomVideosByUser.json?mac="+mac+"&sn="+sn+"&userId="+userId+"&pageSize=50&pageNum=1&chnnel="+chnId;
                }
            }
            System.out.println(url);

            List<String> chanlist = null;
            String keyList = "R_K_LAUNCHER_CATCH_RECOM_VIDEO_USER"+ "_"+ mac + "_"+ sn + "_"+ userId + "_"+ chnId;
            if(redisService.exists(keyList)){
                String val = redisService.get(keyList);
                chanlist = JSONObject.parseObject(val, List.class);
            }else{
                String jsonRes = Http.get(url, "UTF-8");
                if (!StringUtils.isEmpty(jsonRes)) {
                    JSONObject jsonString = JSONObject.parseObject(jsonRes);
                    if (jsonString != null) {
                        String data = jsonString.getString("desc");
                        if(!StringUtils.isEmpty(data)){
                            chanlist = Arrays.asList(data.replaceAll(" ", "").split(","));
                        }
                    }
                }
                if(chanlist!=null && chanlist.size() > 0){
                    redisService.setEx(keyList,getRandomTime(), JSON.toJSONString(chanlist));
                }
            }



           /* 猜你喜欢数据去重*/
            List<Integer> programsetIdList = new ArrayList<Integer>();
            if(!StringUtils.isEmpty(chanlist)){
                for (String contentId : chanlist){
                    boolean flag = true;
                    Integer programsetId = Integer.valueOf(contentId);
                    for (PortalFixedRecommendContentVo fixRecomList : list){
                        if (fixRecomList.getContentId().equals(programsetId)){
                            flag = false;
                        }
                    }
                    if (flag){
                        programsetIdList.add(programsetId);
                    }
                }
            }

            if(!StringUtils.isEmpty(programsetIdList)){
                for (Integer contentId : programsetIdList){
                    NewContentChanCondition chanCondition = new NewContentChanCondition();
                    chanCondition.setProgramsetId(contentId);
                    chanCondition.setApkBagName("com.hiveview.cloudscreen.vipvideo");
                    chanCondition.setStatus(1);
                    NewContentChanList chanVo = newContentChanService.get(chanCondition);
                    if(chanVo!=null){
                        PortalFixedRecommendContentVo portalFixedRecommendContentVo = new PortalFixedRecommendContentVo();
                        portalFixedRecommendContentVo.setContentId(chanVo.getProgramsetId());
                        portalFixedRecommendContentVo.setContentType(1);
                        portalFixedRecommendContentVo.setContentName(chanVo.getAlbumName());
                        portalFixedRecommendContentVo.setApkBagName("com.hiveview.cloudscreen.vipvideo");
                        portalFixedRecommendContentVo.setChnId(chanVo.getChnId());
                        portalFixedRecommendContentVo.setChnType(chanVo.getChnType());
                        portalFixedRecommendContentVo.setContentImg(chanVo.getAlbumHbPic());
                        portalFixedRecommendContentVo.setFixedRecomId(condition.getFixedRecomId());
                        portalFixedRecommendContentVo.setTempletId(chanVo.getTempletId());
                        portalFixedRecommendContentVo.setQiyiIsVip(chanVo.getAqyIsVip());
                        list.add(portalFixedRecommendContentVo);
                    }
                }
                System.gc();
            }

            /*分页*/
            for (int i=0;i < condition.getPageSize() && i < list.size() - condition.getPageIndex();i++){
                PortalFixedRecommendContentVo result = list.get(condition.getPageIndex() + i);
                resultList.add(result);
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return resultList;

    }
    public PortalFixedRecommendContentVo getFixedRecommendContentOne(PortalFixedRecomContentCondition condition)throws Exception{
        PortalFixedRecommendContentVo one = this.portalFixedRecommendContentDao.getFixedRecommendContentOne(condition);
        return one;
    }
    public PortalFixedRecommendContentVo getMinMapping(PortalFixedRecomContentCondition condition)throws Exception{
        return this.portalFixedRecommendContentDao.getMinMapping(condition);
    }
    public PortalFixedRecommendContentVo getMaxMapping(PortalFixedRecomContentCondition condition)throws Exception{
        return this.portalFixedRecommendContentDao.getMaxMapping(condition);
    }
    public Integer getMinSeq(PortalFixedRecomContentCondition condition)throws Exception{
       return this.portalFixedRecommendContentDao.getMinSeq(condition);
    }
    public int getRandomTime(){
        return 21600 + new Random().nextInt(3600);
    }

}
