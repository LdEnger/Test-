package cn.com.hiveview.launcher.module.jumpInstruction.controller;

import cn.com.hiveview.launcher.module.jumpInstruction.condition.JumpInstructionCondition;
import cn.com.hiveview.launcher.module.jumpInstruction.service.JumpInstructionService;
import cn.com.hiveview.launcher.module.vipLogo.condition.VipLogoCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xiach on 2017/10/8.
 */
@Controller
@RequestMapping("/jumpInstruction")
public class JumpInstructionController {

    @Autowired
    private JumpInstructionService jumpInstructionService;

    @RequestMapping(value = "/list")
    public String getJumpInstructionList() {
        return "/jumpInstruction/jumpInstructionList";
    }

    @RequestMapping(value = "/getPageList")
    @ResponseBody
    public Object getPageList(JumpInstructionCondition condition) {
        try {

            return  jumpInstructionService.getPageList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object save(JumpInstructionCondition condition) {
        try {
            return  jumpInstructionService.add(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(JumpInstructionCondition condition) {
        try {
            return  jumpInstructionService.update(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object del(JumpInstructionCondition condition) {
        try {
            return  jumpInstructionService.delete(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/getActionById")
    @ResponseBody
    public Object getActionById(JumpInstructionCondition condition) {
        try {

            return  jumpInstructionService.getActionById(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
