package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.core.util.ActivityConstant;
import cn.com.hiveview.core.util.HttpUtils;
import cn.com.hiveview.core.util.QueryString;
import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.ActivityFreeVipList;
import cn.com.hiveview.entity.module.portal.CommonActivityList;
import cn.com.hiveview.launcherapi.module.portal.condition.ActivityFreeVipCondition;
import org.springframework.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.List;

/**
 * Created by admin on 2017/7/31.
 */
@Service
public class ActivityFreeVipService {
    public ScriptPage<ActivityFreeVipList> getPageList(ActivityFreeVipCondition condition) throws Exception {
        String jsonRes = "";
        try {
            String type = condition.getType() == null ?"":String.valueOf(condition.getType());
            String url = MessageFormat.format(ActivityConstant.activityList,
                    ActivityConstant.activityServer);
            QueryString qs = new QueryString("type", type);
            qs.add("title",condition.getTitle());
            qs.add("pageNo",condition.page+"");
            qs.add("pageSize",condition.rows+"");
            String reqUrl = url+ qs;
            jsonRes = HttpUtils.doGet(reqUrl);
            if (!StringUtils.isEmpty(jsonRes)) {
                JSONObject jsonString = JSONObject.parseObject(jsonRes);
                if (!StringUtils.isEmpty(jsonString)) {
                    String result = jsonString.getString("result");
                    if (!StringUtils.isEmpty(result)) {
                        JSONObject jsonResult = JSONObject.parseObject(result);
                        String rows = jsonResult.getString("rows");
                        Integer total = jsonResult.getInteger("total");
                        ScriptPage<ActivityFreeVipList> scriptPage = new ScriptPage<ActivityFreeVipList>();
                        if(!StringUtils.isEmpty(rows)){
                            List<ActivityFreeVipList> operVos = JSONArray.parseArray(rows, ActivityFreeVipList.class);
                            scriptPage.setRows(operVos);
                        }
                        scriptPage.setTotal(total);
                        return scriptPage;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ScriptPage<ActivityFreeVipList>();
    }


    public List<CommonActivityList> getList() throws Exception {
        String jsonRes = "";
        try {
            String url = MessageFormat.format(ActivityConstant.activityTypeList, ActivityConstant.activityServer);
            jsonRes = HttpUtils.doGet(url);
            if (!StringUtils.isEmpty(jsonRes)) {
                JSONObject jsonString = JSONObject.parseObject(jsonRes);
                if (!StringUtils.isEmpty(jsonString)) {
                    String result = jsonString.getString("result");
                    if (!StringUtils.isEmpty(result)) {
                        List<CommonActivityList> operVos = JSONArray.parseArray(result, CommonActivityList.class);
                        return operVos;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
