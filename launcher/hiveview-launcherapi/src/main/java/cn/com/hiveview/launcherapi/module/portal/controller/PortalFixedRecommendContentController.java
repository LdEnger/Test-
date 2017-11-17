package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalFixedRecommendContentVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalFixedRecomContentCondition;
import cn.com.hiveview.launcherapi.module.portal.service.PortalFixedRecommendContentService;
import cn.com.hiveview.launcherapi.module.portal.service.RedisService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by user on 2017/7/14.
 */
@Controller
@RequestMapping("/fixedRecommendContent")
public class PortalFixedRecommendContentController {

    @Autowired
    private PortalFixedRecommendContentService portalFixedRecommendContentService;
    @Autowired
    private RedisService redisService;
    private static Integer fixedArray [] = {1,2,3,4,5,6,7,8,9,10};

    @RequestMapping(value = { "/getPageList" })
    @ResponseBody
    public ScriptPage<PortalFixedRecommendContentVo> getPageList(@RequestBody String getStr) {
        try {
            PortalFixedRecomContentCondition getBean = JSON.parseObject(getStr, PortalFixedRecomContentCondition.class);
            return portalFixedRecommendContentService.getList(getBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public Object save(@RequestBody String indexStr) {
        try {
            if (StringUtils.isBlank(indexStr)) {
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalFixedRecomContentCondition insertBean = JSON.parseObject(indexStr, PortalFixedRecomContentCondition.class);
            if (null == insertBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalFixedRecommendContentService.save(insertBean)>0) {
                return JsonMessage.create(0, "", "");
            } else {
                if(portalFixedRecommendContentService.save(insertBean) == -2){
                    return JsonMessage.create(-2, "", "");
                }else{
                return JsonMessage.create(-1L, "数据库插入失败", "");}
            }
        } catch (Exception ex) {
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object del(@RequestBody String indexStr) {
        try {
            if (StringUtils.isBlank(indexStr)) {
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalFixedRecomContentCondition delBean = JSON.parseObject(indexStr, PortalFixedRecomContentCondition.class);
            if (null == delBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalFixedRecommendContentService.delete(delBean)>0) {
                return JsonMessage.create(0, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库删除失败", "");
            }
        } catch (Exception ex) {
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@RequestBody String indexStr) {
        try {
            if (StringUtils.isBlank(indexStr)) {
                return JsonMessage.create(-1l, "参数不能为空", "");
            }
            PortalFixedRecomContentCondition updateBean = JSON.parseObject(indexStr, PortalFixedRecomContentCondition.class);
            if (null == updateBean) {
                return new JsonMessage().create(-1l, "反序列化失败", "");
            }
            if (portalFixedRecommendContentService.update(updateBean)>0) {
                return JsonMessage.create(0, "", "");
            } else {
                if(portalFixedRecommendContentService.update(updateBean)== -2){
                    return JsonMessage.create(-2, "", "");
                }else{
                return JsonMessage.create(-1L, "数据库更新失败", "");
                }
            }
        } catch (Exception ex) {
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }

    @RequestMapping(value = "/getFixedRecommendContent")
    @ResponseBody
    public PortalFixedRecommendContentVo getLogo(@RequestBody String indexStr) {
        try {
            PortalFixedRecomContentCondition getBean = JSON.parseObject(indexStr, PortalFixedRecomContentCondition.class);
            return portalFixedRecommendContentService.getFixedRecommendContent(getBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/getFixedRecommendContentList")
    @ResponseBody
    public ScriptPage<PortalFixedRecommendContentVo> getFixedRecommendContentList(@RequestBody String getStr) {
        try {
            PortalFixedRecomContentCondition getBean = JSON.parseObject(getStr, PortalFixedRecomContentCondition.class);
            return portalFixedRecommendContentService.getFixedRecommendContentList(getBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/getFixedRecommendContentOne")
    @ResponseBody
    public PortalFixedRecommendContentVo getFixedRecommendContentOne(@RequestBody String getStr) {
        try {
            PortalFixedRecomContentCondition getBean = JSON.parseObject(getStr, PortalFixedRecomContentCondition.class);
            return portalFixedRecommendContentService.getFixedRecommendContentOne(getBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/getMinMapping")
    @ResponseBody
    public Object getMinMapping(@RequestBody String getStr){
        try {
            PortalFixedRecomContentCondition condition = JSON.parseObject(getStr,PortalFixedRecomContentCondition.class);
            return this.portalFixedRecommendContentService.getMinMapping(condition);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/getMaxMapping")
    @ResponseBody
    public Object getMaxMapping(@RequestBody String getStr){
        try {
            PortalFixedRecomContentCondition condition = JSON.parseObject(getStr,PortalFixedRecomContentCondition.class);
            return this.portalFixedRecommendContentService.getMaxMapping(condition);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/getMinSeq")
    @ResponseBody
    public Object getMinSeq(@RequestBody String getStr){
        try {
            PortalFixedRecomContentCondition condition = JSON.parseObject(getStr,PortalFixedRecomContentCondition.class);
          System.out.println("222:"+this.portalFixedRecommendContentService.getMinSeq(condition));
            return this.portalFixedRecommendContentService.getMinSeq(condition);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping(value = { "/getPortalFixedRecomContentList/{id}/{userId}/{mac}/{sn}/{page}/{size}/{version}" }, method = RequestMethod.GET)
    @ResponseBody
    public Object getPortalFixedRecomContentList(HttpServletRequest request, @PathVariable Integer id,@PathVariable Integer userId,
                                                 @PathVariable String  mac,@PathVariable String sn,@PathVariable Integer page,
                                                 @PathVariable Integer size,@PathVariable String version) {
        try {
            if (StringUtils.isBlank(id+"")) {
                return JsonMessage.create(-1l, "id不能为空", "");
            }

            if (StringUtils.isBlank(version+"")) {
                return JsonMessage.create(-1l, "version不能为空", "");
            }

           /* mac = mac.replaceAll(":", "").replaceAll("-", "").toUpperCase();
            sn = sn.toUpperCase();*/

            List<PortalFixedRecommendContentVo> list = new ArrayList<PortalFixedRecommendContentVo>();
            String keyList = "R_K_LAUNCHER_CATCH_FIXED_RECOM"+"_"+id+"_"+userId+"_"+mac+"_"+sn+"_"+page+"_"+size+"_"+version;
            if(redisService.exists(keyList)){
                String val = redisService.get(keyList);
                list = JSONObject.parseObject(val, List.class);
            }else{
                Integer index = new Random().nextInt(fixedArray.length);
                synchronized (fixedArray[index]) {
                    if (redisService.exists(keyList)) {
                        String val = redisService.get(keyList);
                        list = JSONObject.parseObject(val, List.class);
                    } else {
                        //赋参数
                        PortalFixedRecomContentCondition condition = new PortalFixedRecomContentCondition();
                        condition.setFixedRecomId(id);
                        condition.setPageIndex(page);
                        condition.setPageSize(size);
                        list = portalFixedRecommendContentService.getPortalFixedRecomContentList(condition,userId,mac,sn);
                        if (list != null && list.size() > 0) {
                            redisService.setEx(keyList, getRandomTime(), JSON.toJSONString(list));
                        }
                    }
                }
            }

            return JsonMessage.create(0, "",list);
        } catch (Exception ex) {
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }

    public int getRandomTime(){
        return 5400 + new Random().nextInt(1800);
    }
}
