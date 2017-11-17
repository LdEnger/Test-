package cn.com.hiveview.launcher.module.portal.controller;

import cn.com.hiveview.launcher.module.portal.condition.MergeVideoData;
import cn.com.hiveview.launcher.module.portal.condition.NewVipActivityCondition;
import cn.com.hiveview.launcher.module.portal.service.NewVipActivityService;
import cn.com.hiveview.launcher.module.portal.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2017/10/13.
 */
@Controller
@RequestMapping("/program")
public class ProgramController {
    @Autowired
    private ProgramService programService;
    @RequestMapping(value = "getPageList")
    @ResponseBody
    public Object getPageList(MergeVideoData condition) {
        try {
            return  programService.getPageList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
