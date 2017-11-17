package cn.com.hiveview.launcher.module.portal.controller;

import cn.com.hiveview.launcher.module.portal.condition.NewContentChanCondition;
import cn.com.hiveview.launcher.module.portal.service.NewContentChanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2017/8/2.
 */
@Controller
@RequestMapping("/portal/newContentChan")
public class NewContentChanController {
    @Autowired
    NewContentChanService newContentChanService;

    @RequestMapping(value = "/getNewContentChanPageList")
    @ResponseBody
    public Object getNewContentChanPageList(NewContentChanCondition condition) {
        try {
            return  newContentChanService.getNewContentChanPageList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
