package cn.com.hiveview.launcher.module.start.controller;

import cn.com.hiveview.launcher.module.start.condition.StartInstructionCondition;
import cn.com.hiveview.launcher.module.start.service.StartInstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/7/12.
 */
@Controller
@RequestMapping("/start")
public class StartInstructionController {

    @Autowired
    private StartInstructionService startInstructionService;

    @RequestMapping(value = "list")
    public String getstartInstructionList() {
        return "start/startInstructionList";
    }

    @RequestMapping(value = "getPageList")
    @ResponseBody
    public Object getPageList(StartInstructionCondition condition) {
        try {
            return  startInstructionService.getStartInstructionList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/save")
    @ResponseBody
    public Object save(StartInstructionCondition condition) {
        try {
            return  startInstructionService.insert(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(StartInstructionCondition condition) {
        try {
            return  startInstructionService.update(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/del")
    @ResponseBody
    public Object del(StartInstructionCondition condition) {
        try {
            return  startInstructionService.del(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/getComboboxList")
    @ResponseBody
    public Object getComboboxList(StartInstructionCondition condition){
        try {
            return startInstructionService.getComboboxList(condition);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
}
