package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.entity.module.portal.PortalCustomRecomContentApiVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalCustomRecomContentCondition;
import cn.com.hiveview.launcherapi.module.portal.service.PortalCustomRecomContentService;
import cn.com.hiveview.launcherapi.module.portal.service.RedisService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by user on 2017/7/21.
 */
@Controller
@RequestMapping("/portalCustomRecomContent")
public class PortalCustomRecomContentController {

    @Autowired
    private PortalCustomRecomContentService portalCustomRecomContentService;
    @Autowired
    private RedisService redisService;
    private static Integer customArray [] = {1,2,3,4,5,6,7,8,9,10};

    @RequestMapping(value = { "/getPortalCustomRecomContentList/{id}/{page}/{size}/{version}" }, method = RequestMethod.GET)
    @ResponseBody
    public Object getPortalCustomRecomContentList(HttpServletRequest request, @PathVariable Integer id,  @PathVariable Integer page, @PathVariable Integer size,
                                @PathVariable String version) {
        try {
            if (StringUtils.isBlank(id+"")) {
                return JsonMessage.create(-1l, "id不能为空", "");
            }

            if (StringUtils.isBlank(version+"")) {
                return JsonMessage.create(-1l, "version不能为空", "");
            }


            List<PortalCustomRecomContentApiVo> list = new ArrayList<PortalCustomRecomContentApiVo>();
            String keyList = "R_K_LAUNCHER_CATCH_CUSTOM_RECOM"+"_"+id+"_"+page+"_"+size+"_"+version;
            if(redisService.exists(keyList)){
                String val = redisService.get(keyList);
                list = JSONObject.parseObject(val, List.class);
            }else{
                Integer index = new Random().nextInt(customArray.length);
                synchronized (customArray[index]) {
                    if (redisService.exists(keyList)) {
                        String val = redisService.get(keyList);
                        list = JSONObject.parseObject(val, List.class);
                    } else {
                        //赋参数
                        PortalCustomRecomContentCondition condition = new PortalCustomRecomContentCondition();
                        condition.setRecomTempletId(id);
                        condition.setPageIndex(page);
                        condition.setPageSize(size);
                        list = portalCustomRecomContentService.getPortalCustomRecomContentList(condition);
                        if (list != null && list.size() > 0) {
                            redisService.setEx(keyList, getRandomTime(), JSON.toJSONString(list));
                        }
                    }
                }
            }

            return JsonMessage.create(0, "",list);
        } catch (Exception ex) {
            ex.printStackTrace();
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }
    public int getRandomTime(){
        return 5400 + new Random().nextInt(1800);
    }
}
