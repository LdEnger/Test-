package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalNotStartInstructionListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalNotStartInstructionListConditon;
import cn.com.hiveview.launcherapi.module.portal.condition.UnbundlingCondition;
import cn.com.hiveview.launcherapi.module.portal.service.PortalNotStartInstructionService;
import cn.com.hiveview.launcherapi.module.portal.service.UnbundlingService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2017/10/13.
 */
@Controller
@RequestMapping("/PortalNotStartInstruction")
public class PortalNotStartInstructionConrtoller {



    @Autowired
    private PortalNotStartInstructionService portalNotStartInstructionService;

    @RequestMapping("/getPage")
    @ResponseBody
    public ScriptPage<PortalNotStartInstructionListVo> getPage(@RequestBody String getStr) {
        try {
            PortalNotStartInstructionListConditon getBean = JSON.parseObject(getStr, PortalNotStartInstructionListConditon.class);
            return portalNotStartInstructionService.getPage(getBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/save")
    @ResponseBody
    public Object save(@RequestBody String indexStr) {
        try {
            if (StringUtils.isBlank(indexStr)) {
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalNotStartInstructionListConditon insertBean = JSON.parseObject(indexStr, PortalNotStartInstructionListConditon.class);
            if (null == insertBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalNotStartInstructionService.save(insertBean) >0) {
                return JsonMessage.create(0, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库插入失败", "");
            }
        } catch (Exception ex) {
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object del(@RequestBody String indexStr) {
        try {
            if (StringUtils.isBlank(indexStr)) {
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalNotStartInstructionListConditon delBean = JSON.parseObject(indexStr, PortalNotStartInstructionListConditon.class);
            if (null == delBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            Integer result = portalNotStartInstructionService.delete(delBean);
            if(result == -1){
                return JsonMessage.create(1, "", "");
            }else if (result > 0) {
                return JsonMessage.create(0, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库删除失败", "");
            }
        } catch (Exception ex) {
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }
    @RequestMapping("/update")
    @ResponseBody
    public Object update(@RequestBody String indexStr) {
        try {
            if (StringUtils.isBlank(indexStr)) {
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalNotStartInstructionListConditon updateBean = JSON.parseObject(indexStr, PortalNotStartInstructionListConditon.class);
            if (null == updateBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalNotStartInstructionService.update(updateBean)>0) {
                return JsonMessage.create(0, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库更新失败", "");
            }
        } catch (Exception ex) {
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }

    @RequestMapping("/updateEffice")
    @ResponseBody
    public Object updateEffice(@RequestBody String indexStr) {
        try {
            if (StringUtils.isBlank(indexStr)) {
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalNotStartInstructionListConditon updateBean = JSON.parseObject(indexStr, PortalNotStartInstructionListConditon.class);
            if (null == updateBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalNotStartInstructionService.updateEffice(updateBean)>0) {
                return JsonMessage.create(0, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库更新失败", "");
            }
        } catch (Exception ex) {
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }



    @RequestMapping("/getPageList")
    @ResponseBody
    public ScriptPage<PortalNotStartInstructionListVo> getPageList(@RequestBody String getStr) {
        try {
            PortalNotStartInstructionListConditon getBean = JSON.parseObject(getStr, PortalNotStartInstructionListConditon.class);
            return portalNotStartInstructionService.getPageList(getBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @RequestMapping(value = {"/updateVersion/{startApk}"})
    @ResponseBody
    public Object updateVersion(@PathVariable Integer startApk) {
        try {
            PortalNotStartInstructionListConditon updateBean =new PortalNotStartInstructionListConditon();
            updateBean.setStartApk(startApk);
            portalNotStartInstructionService.updateNotice(updateBean);
            return JsonMessage.create(0, "", "");
        } catch (Exception ex) {
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }
}
