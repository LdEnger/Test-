package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.core.util.HttpUtils4;
import cn.com.hiveview.core.util.JsonUtil;
import cn.com.hiveview.core.util.TokenUtils;
import cn.com.hiveview.core.util.YouGouConstants;
import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.YouGouSpecials;
import cn.com.hiveview.launcherapi.module.portal.condition.ActivityFreeVipCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.YouGouSpecialsCondition;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class YouGouSpecialsService {

    public Object getList(YouGouSpecialsCondition condition) {
        try {
            String url = YouGouConstants.SPECIALS_LIST;
            Map<String, Object> map = new HashMap<>();
            map.put("pageIndex", condition.getPage());
            map.put("pageSize", condition.getRows());
            if (!StringUtils.isEmpty(condition.getName())) {
                map.put("name", condition.getName());
            }
            String params = JSON.toJSONString(map);
            String token = TokenUtils.youGouToken(map);
            params = URLEncoder.encode(params, "utf-8");
            String reqUrl = url + "?params=" + params + "&token=" + token;
            String resp = HttpUtils4.get(reqUrl);

            //解析返回请求
            if (resp == null) {
                return JsonMessage.create(0, "成功", null);
            }
            JSONObject ob = JSON.parseObject(resp);
            Integer returnValue = ob.getInteger("returnValue");
            if (returnValue == null || returnValue < 0) {
                return JsonMessage.create(-1, ob.toJSONString(), null);
            }
            JSONObject data = ob.getJSONObject("data");
            JSONArray records = data.getJSONArray("records");
            if (records == null) {
                return JsonMessage.create(0, "成功", null);
            }
            List<YouGouSpecials> list = new ArrayList<>();
            for (Object record1 : records) {
                JSONObject record = (JSONObject) record1;
                String name = record.getString("name");
                String imgUrl = record.getString("imgUrl");
                String specSn = record.getString("specSn");
                YouGouSpecials youGouSpecials = new YouGouSpecials();
                youGouSpecials.setImgUrl(imgUrl);
                youGouSpecials.setName(name);
                youGouSpecials.setSpecSn(specSn);
                list.add(youGouSpecials);
            }
            ScriptPage<YouGouSpecials> sp = new ScriptPage<>();
            sp.setRows(list);
            sp.setTotal(data.getInteger("totalCount"));
            return JsonMessage.create(0, "成功", sp);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonMessage.create(-1, "异常", null);
        }
    }

    public ScriptPage<YouGouSpecials> getPageList(YouGouSpecialsCondition condition) throws Exception {
        ScriptPage<YouGouSpecials> scriptPage = new ScriptPage<YouGouSpecials>();
        JsonMessage getBean = JSON.parseObject(getList(condition).toString(), JsonMessage.class);
        scriptPage= JSON.parseObject(getBean.getData().toString(), ScriptPage.class);
        return scriptPage;
    }
}
