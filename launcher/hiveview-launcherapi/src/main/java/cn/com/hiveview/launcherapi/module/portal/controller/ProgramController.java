package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.launcherapi.module.portal.condition.MergeVideoData;
import cn.com.hiveview.launcherapi.module.portal.service.ProgramService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2017/10/13.
 */
@Controller
@RequestMapping("program")
public class ProgramController {
    @Autowired
    private ProgramService programService;
    @RequestMapping(value = { "/getPageList" })
    @ResponseBody
    public Object getPageList(@RequestBody String getStr) {
        try {
            MergeVideoData getBean = JSON.parseObject(getStr, MergeVideoData.class);
            return JSON.toJSONString(programService.getPageList(getBean), SerializerFeature.WriteMapNullValue);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
