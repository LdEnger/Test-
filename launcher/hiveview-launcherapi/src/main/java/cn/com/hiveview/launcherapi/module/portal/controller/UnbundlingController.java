package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.launcherapi.module.portal.condition.UnbundlingCondition;
import cn.com.hiveview.launcherapi.module.portal.service.UnbundlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 解绑下线数据类
 * Created by admin on 2017/8/17.
 */
@Controller
@RequestMapping("/unbundling")
public class UnbundlingController {
    @Autowired
    private UnbundlingService unbundlingService;

    /**
     * 解绑下线数据方法
     * @param contentType
     * @param apkBagName
     * @param contentId
     * @param isEffective
     * @return
     */
    @RequestMapping(value = {"/unbundlingList/{contentType}/{apkBagName}/{contentId}/{isEffective}"})
    @ResponseBody
    public Object getVipList(@PathVariable Integer contentType, @PathVariable String apkBagName, @PathVariable Integer contentId, @PathVariable Integer isEffective) {
        try {
            //根据不同类型解绑下线的数据模板
            //通知首屏大图横竖图
            UnbundlingCondition condition = new UnbundlingCondition();
            condition.setContentType(contentType);
            condition.setContentId(contentId);
            if(apkBagName.equals("null")){
                condition.setApkBagName(null);
            }else{
                condition.setApkBagName(apkBagName);
            }
            condition.setIsEffective(isEffective);
            if (unbundlingService.delete(condition)>0) {
                return JsonMessage.create(0, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库删除失败", "");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }
}
