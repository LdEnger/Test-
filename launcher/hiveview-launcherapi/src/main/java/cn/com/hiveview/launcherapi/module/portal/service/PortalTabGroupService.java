package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.core.util.Constants;
import cn.com.hiveview.core.util.JsonUtil;
import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.NavigationVo;
import cn.com.hiveview.entity.module.portal.PortalScreenRecommendListApiVo;
import cn.com.hiveview.entity.module.portal.PortalTabPageEntity;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalCustomRecomContentCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalTabGroupCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalCustomRecomContentDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalTabGroupDao;
import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by xiach on 2017/10/9.
 */
@Service
public class PortalTabGroupService {
    private static final Logger logger = LoggerFactory.getLogger(PortalTabGroupService.class.getName());
    @Autowired
    private PortalTabGroupDao portalTabGroupDao;
    @Autowired
    private PortalCustomRecomContentDao portalCustomRecomContentDao;
    @Autowired
    private NavigationService navigationService;
    @Autowired
    private RedisService redisService;
    private static Integer lockArray [] = {1,2,3,4,5,6,7,8,9,10};
    /**
     *
     * @param condition
     * @return  查询一页数据
     * @throws Exception
     */
    public ScriptPage<PortalTabGroupCondition> getPageList(PortalTabGroupCondition condition) throws Exception {
        ScriptPage<PortalTabGroupCondition> scriptPage = new ScriptPage<PortalTabGroupCondition>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<PortalTabGroupCondition> rows = this.portalTabGroupDao.getPageList(condition);
        int total = this.portalTabGroupDao.getCount(condition);
        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        return scriptPage;
    }
    public Integer add(List<PortalTabGroupCondition> conditions) throws Exception{
        Integer addCount=-1;
        Integer videoCount=0;
        Integer tabId=-1;
        Integer groupType=-1;
        for (PortalTabGroupCondition condition : conditions) {//判断选择的groups中有个视频的个数
            tabId=condition.getBelongTabId();
            groupType=condition.getGroupType();
            if(condition.getGroupType()==1){
                PortalCustomRecomContentCondition portalCustomRecomContentCondition = new PortalCustomRecomContentCondition();
                portalCustomRecomContentCondition.setRecomTempletId(condition.getBelongGroupId());
                Integer n = portalCustomRecomContentDao.getVideoCount(portalCustomRecomContentCondition);
                videoCount=videoCount+n;
            }else {
                break;
            }
        }
        if(videoCount>1){//不符合条件
            return -2;
        }
        if(videoCount==1){//符合条件，判断数据库中的是否已存在有带视频的Group
            PortalTabGroupCondition paramCondition = new PortalTabGroupCondition();
            paramCondition.setBelongTabId(tabId);
            paramCondition.setGroupType(groupType);
            List<PortalTabGroupCondition> dbTabGroups = portalTabGroupDao.getPageList(paramCondition);
            for (PortalTabGroupCondition dbTabGroup : dbTabGroups) {
                PortalCustomRecomContentCondition portalCustomRecomContentCondition = new PortalCustomRecomContentCondition();
                portalCustomRecomContentCondition.setRecomTempletId(dbTabGroup.getBelongGroupId());
                Integer n = portalCustomRecomContentDao.getVideoCount(portalCustomRecomContentCondition);
                if(n==1){
                    return -3;//数据库中已经有，不允许在添加
                }
            }
        }
        for (PortalTabGroupCondition condition : conditions) {
            Integer maxSeq = portalTabGroupDao.getMaxSeq(condition);
            if(maxSeq==null){//当表中没有数据从一开始排序
                condition.setSeq(1);
            }else {
                condition.setSeq(maxSeq+1);
            }
             addCount = portalTabGroupDao.add(condition);
        }
        return addCount;
    }

    public Integer delete(PortalTabGroupCondition condition){
        Integer delCount = portalTabGroupDao.delete(condition);
        return delCount;
    }

    public Integer update(PortalTabGroupCondition condition){
        Integer updateCount = portalTabGroupDao.update(condition);
        return updateCount;
    }

    public PortalTabGroupCondition getMinContent(PortalTabGroupCondition condition){
        return portalTabGroupDao.getMinContent(condition);
    }
    public PortalTabGroupCondition getMaxContent(PortalTabGroupCondition condition){
        return portalTabGroupDao.getMaxContent(condition);
    }
    public List<PortalTabPageEntity> getList(PortalTabGroupCondition condition) throws Exception {
        List<PortalTabPageEntity> result = new ArrayList<PortalTabPageEntity>();
        try {
            String groupRedisValue = Constants.GROUPLIST_BYTABID_REDIS_KEY+"_"+condition.getBelongTabId()+"_"+condition.getPage()+"_"+condition.getRows();
            if("4.0".equals(condition.getVersion())){
                groupRedisValue+="_4.0";
            }
            String val = redisService.get(groupRedisValue);
            if(!StringUtils.isEmpty(val)){
                result  = JSON.parseObject(val, List.class);
            }else{
                Integer index = new Random().nextInt(lockArray.length);
                synchronized (lockArray[index]) {
                    if (redisService.exists(groupRedisValue)){
                        val = redisService.get(groupRedisValue);
                        result  = JSON.parseObject(val, List.class);
                    }else{
                        result = portalTabGroupDao.getList(condition);
                        int randNum = new Random().nextInt(Constants.X) + Constants.Y;
                        redisService.setEx(groupRedisValue,randNum, JsonUtil.toJSONString(result));
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Object obj =new Object[]{condition.getBelongTabId(),condition.getPage(),condition.getRows()};
            logger.info("通过tabId获取group数据接口出错>>>>>"+obj);
            return result;
        }
      return result;
    }
    public Integer getMaxSeq(PortalTabGroupCondition condition){
        return portalTabGroupDao.getMaxSeq(condition);
    }

    public Integer getCount(PortalTabGroupCondition condition){return portalTabGroupDao.getCount(condition);}

    public Integer addOne(PortalTabGroupCondition condition){
        Integer videoCount=-1;
        Integer dbCount = portalTabGroupDao.getCount(condition);
        if(dbCount!=0){//该Group已经关联
            return -2;
        }
        if(condition.getGroupType()==1){//判断是不是Group，并判断是否含有视频
            PortalCustomRecomContentCondition portalCustomRecomContentCondition = new PortalCustomRecomContentCondition();
            portalCustomRecomContentCondition.setRecomTempletId(condition.getBelongGroupId());
            videoCount = portalCustomRecomContentDao.getVideoCount(portalCustomRecomContentCondition);
            if(videoCount>0){//符合条件，判断数据库中的是否已存在有带视频的Group
                PortalTabGroupCondition selParam = new PortalTabGroupCondition();
                selParam.setBelongTabId(condition.getBelongTabId());
                selParam.setGroupType(1);
                List<PortalTabGroupCondition> dbTabGroups = portalTabGroupDao.getPageList(selParam);
                for (PortalTabGroupCondition dbTabGroup : dbTabGroups) {//判断Group中是否有视频
                    portalCustomRecomContentCondition.setRecomTempletId(dbTabGroup.getBelongGroupId());
                    Integer n = portalCustomRecomContentDao.getVideoCount(portalCustomRecomContentCondition);
                    if(n>0){
                        return -3;//数据库中已经有带视频的group，不允许在添加
                    }
                }
                //判断是否有首映播放器
                PortalTabGroupCondition firstVideoPlay = new PortalTabGroupCondition();
                firstVideoPlay.setBelongTabId(condition.getBelongTabId());
                firstVideoPlay.setBelongGroupId(4);
                Integer m = portalTabGroupDao.getCount(firstVideoPlay);
                if(m>0){
                    return -3;//数据库中已经有首映播放器不允许在添加
                }
            }
        }
        if(condition.getGroupType()==2 && condition.getBelongGroupId()==4){//判断是否为speGroup,是否为首映播放器
            PortalCustomRecomContentCondition portalCustomRecomContentCondition = new PortalCustomRecomContentCondition();
            portalCustomRecomContentCondition.setRecomTempletId(condition.getBelongGroupId());//查询视频参数
            PortalTabGroupCondition selParam = new PortalTabGroupCondition();//查询数据库的Group参数
            selParam.setBelongTabId(condition.getBelongTabId());
            selParam.setGroupType(1);
            List<PortalTabGroupCondition> dbTabGroups = portalTabGroupDao.getPageList(selParam);
            for (PortalTabGroupCondition dbTabGroup : dbTabGroups) {//判断Group中是否有视频
                //PortalCustomRecomContentCondition pcrCondition = new PortalCustomRecomContentCondition();
                portalCustomRecomContentCondition.setRecomTempletId(dbTabGroup.getBelongGroupId());
                Integer n = portalCustomRecomContentDao.getVideoCount(portalCustomRecomContentCondition);
                if(n>0){
                    return -3;//数据库中已经有带视频的group，不允许在添加带视频的
                }
            }
        }

        PortalTabGroupCondition exitParam=new PortalTabGroupCondition();
        exitParam.setBelongTabId(condition.getBelongTabId());
        exitParam.setUpAndDown(2);
        if(portalTabGroupDao.getCount(exitParam)==1){//如果已存在纵向的Group，不许再添加Group
            return -5;//纵向的Group只能独占一个tab
        }


        if(condition.getGroupType()==3){//判断是否为dataGroup,并且没有行数
            PortalTabGroupCondition selParam=new PortalTabGroupCondition();
            selParam.setBelongTabId(condition.getBelongTabId());
            if(null!=condition.getUpAndDown()){
                if(condition.getUpAndDown()==2 && portalTabGroupDao.getCount(selParam)!=0){//添加纵向Group只能是空的tab
                    return -5;//纵向的Group只能独占一个tab
                }
            }

            if(condition.getRow()==null){//判断是否有行数
                condition.setSeq(-1); //设置该Group排序为负
                Integer minSeq = portalTabGroupDao.getMinSeq(condition);
                if(null!=minSeq && minSeq==-1){
                    return -4;//数据库中已存在含有未知行的dataGroup
                }
            }else {
                Integer maxSeq = portalTabGroupDao.getMaxSeq(condition);
                if(maxSeq==null){//当表中没有数据从一开始排序
                    condition.setSeq(1);
                }else {
                    condition.setSeq(maxSeq+1);
                }
            }
        }else {
            Integer maxSeq = portalTabGroupDao.getMaxSeq(condition);
            if(maxSeq==null){//当表中没有数据从一开始排序
                condition.setSeq(1);
            }else {
                condition.setSeq(maxSeq+1);
            }
        }
        Integer addCount = portalTabGroupDao.add(condition);
        return addCount;
    }

    public boolean checkVideo(PortalTabGroupCondition condition){
        //1:查出所属tab
        //2 循环判断统一tab下,查出所有groupId
        //3 不能同时有多余1个的视频group存在
        boolean flag = true;
        PortalTabGroupCondition firstStep = new PortalTabGroupCondition();
        firstStep.setBelongGroupId(condition.getBelongGroupId());
        firstStep.setGroupType(1);
        List<PortalTabGroupCondition> tabs = portalTabGroupDao.getTabListByGroupId(firstStep);
        for(PortalTabGroupCondition tab :tabs){
            Integer allCount= 0;
            PortalTabGroupCondition fourStep = new PortalTabGroupCondition();
            fourStep.setBelongTabId(tab.getBelongTabId());
            fourStep.setGroupType(2);
            List<PortalTabGroupCondition> fourGroups = portalTabGroupDao.getTabListByGroupId(fourStep);
            for(PortalTabGroupCondition four:fourGroups){
                if(four.getBelongGroupId() == 4){
                    allCount = 1;
                }
            }
            PortalTabGroupCondition secondStep = new PortalTabGroupCondition();
            secondStep.setBelongTabId(tab.getBelongTabId());
            secondStep.setGroupType(1);
            List<PortalTabGroupCondition> groups = portalTabGroupDao.getTabListByGroupId(secondStep);
            for(PortalTabGroupCondition group :groups){
                PortalCustomRecomContentCondition thirdStep = new PortalCustomRecomContentCondition();
                thirdStep.setRecomTempletId(group.getBelongGroupId());
                Integer count = portalCustomRecomContentDao.getVideoCount(thirdStep);
                count = count + allCount;
                if(count >=1){
                    flag = false;
                }
            }
        }
        return flag;
    }
    public boolean backUpVideo(PortalTabGroupCondition condition){
        //1:查出所属tab
        //2 循环判断统一tab下,查出所有groupId
        //3 不能同时有多余1个的视频group存在
        boolean flag = true;
        PortalTabGroupCondition firstStep = new PortalTabGroupCondition();
        firstStep.setBelongGroupId(condition.getBelongGroupId());
        firstStep.setGroupType(1);
        List<PortalTabGroupCondition> tabs = portalTabGroupDao.getTabListByGroupId(firstStep);
        for(PortalTabGroupCondition tab :tabs){
            Integer allCount= 0;
            PortalTabGroupCondition fourStep = new PortalTabGroupCondition();
            fourStep.setBelongTabId(tab.getBelongTabId());
            fourStep.setGroupType(2);
            List<PortalTabGroupCondition> fourGroups = portalTabGroupDao.getTabListByGroupId(fourStep);
            for(PortalTabGroupCondition four:fourGroups){
                if(four.getBelongGroupId() == 4){
                    allCount = 1;
                }
            }
            PortalTabGroupCondition secondStep = new PortalTabGroupCondition();
            secondStep.setBelongTabId(tab.getBelongTabId());
            secondStep.setGroupType(1);
            List<PortalTabGroupCondition> groups = portalTabGroupDao.getTabListByGroupId(secondStep);
            for(PortalTabGroupCondition group :groups){
                PortalCustomRecomContentCondition thirdStep = new PortalCustomRecomContentCondition();
                thirdStep.setRecomTempletId(group.getBelongGroupId());
                Integer count = portalCustomRecomContentDao.getVideoCount(thirdStep);
                allCount = count + allCount;
                if(allCount >=1){
                    flag = false;
                }
            }
        }
        if(tabs ==null ||tabs.size() ==0){
            PortalCustomRecomContentCondition thirdStep = new PortalCustomRecomContentCondition();
            thirdStep.setRecomTempletId(condition.getBelongGroupId());
            Integer count = portalCustomRecomContentDao.getVideoCount(thirdStep);
            if(count >=1){
                flag = false;
            }
        }
        return flag;
    }
}
