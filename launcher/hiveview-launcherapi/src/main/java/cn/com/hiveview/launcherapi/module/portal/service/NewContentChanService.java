package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.core.util.Constants;
import cn.com.hiveview.core.util.HttpUtils;
import cn.com.hiveview.core.util.JsonUtil;
import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.NewContentChanList;
import cn.com.hiveview.entity.module.portal.PortalScreenRecommendListApiVo;
import cn.com.hiveview.entity.module.portal.PortalSysAppIconsList;
import cn.com.hiveview.launcherapi.module.portal.condition.NewContentChanCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalSysAppIconsListCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.NewContentChanDao;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by admin on 2017/7/18.
 */
@Service
public class NewContentChanService {
    @Autowired
   public NewContentChanDao newContentChanDao;
    @Autowired
    private RedisService redisService;
    private static Integer lockArray [] = {1,2,3,4,5,6,7,8,9,10};
    public ScriptPage<NewContentChanList> getPageAllList(NewContentChanCondition condition) throws Exception {
        if(condition.apkBagName!=null && condition.apkBagName.equals("com.hiveview.premiere")){
            return this.getPageListByPremiere(condition);
        }else {
            return this.getPageList(condition);
        }
    }
    public ScriptPage<NewContentChanList> getPageList(NewContentChanCondition condition) throws Exception {
        ScriptPage<NewContentChanList> scriptPage = new ScriptPage<NewContentChanList>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<NewContentChanList> rows = this.newContentChanDao.getPageList(condition);
        int total = this.newContentChanDao.getPageCount(condition);
        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        return scriptPage;
    }

    public ScriptPage<NewContentChanList> getPageListByPremiere(NewContentChanCondition condition) throws Exception {
        ScriptPage<NewContentChanList> scriptPage = new ScriptPage<NewContentChanList>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<NewContentChanList> rows = this.newContentChanDao.getPageListByPremiere(condition);
        int total = this.newContentChanDao.getCountByPremiere(condition);
        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        return scriptPage;
    }
    public NewContentChanList get(NewContentChanCondition condition) throws Exception{
        return this.newContentChanDao.get(condition);
    }

    public List<NewContentChanList> getList(NewContentChanCondition condition) throws Exception{
        List<NewContentChanList> result = new ArrayList<NewContentChanList>();
        String key = "newContentChan_getList";
        String val = redisService.get(key);
        if(val != null){
            result  = JSON.parseObject(val, List.class);
        }else{
            Integer index = new Random().nextInt(lockArray.length);
            synchronized (lockArray[index]) {
                if (redisService.exists(key)){
                    val = redisService.get(key);
                    result  = JSON.parseObject(val, List.class);
                }else{
                    String  recomInfo =  HttpUtils.doGet(Constants.RECOM_URL);
                    Map<String, Object> map  = JsonUtil.parseMap(recomInfo);
                    String desc = (String) map.get("desc");
                    //condition.setAlbumName(desc);
                    condition.setAlbumName("107846,107847,107848,189687");
                    result = this.newContentChanDao.getList(condition);
                }
                    int randNum = new Random().nextInt(1800)+5400;
                    redisService.setEx(key,randNum, JsonUtil.toJSONString(result));
                }
            }
        return result;
    }

    /**
     * 用于智能推荐接口,按programsetId列表取数据
     */
    public List<NewContentChanList> getDataGroupList(NewContentChanCondition condition){
        return this.newContentChanDao.getDataGroupList(condition);
    }
}
