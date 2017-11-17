package cn.com.hiveview.launcherapi.module.portal.controller;

import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalDataGroupListVo;
import cn.com.hiveview.entity.module.portal.PortalFixedRecommendContentVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalDataGroupListCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalFixedRecomContentCondition;
import cn.com.hiveview.launcherapi.module.portal.service.PortalDataGroupService;
import cn.com.hiveview.launcherapi.module.portal.service.RedisService;
import cn.com.hiveview.launcherapi.module.portal.util.DBHelper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Random;

/**
 * Created by user on 2017/10/9.
 */
@Controller
@RequestMapping("/PortalDataGroupController")
public class PortalDataGroupController {
    private static Integer fixedArray[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    @Autowired
    private RedisService redisService;

    @Autowired
    private PortalDataGroupService portalAreaContentService;
    @Autowired
    private DBHelper holder;
    @RequestMapping("/getPage")
    @ResponseBody
    public ScriptPage<PortalDataGroupListVo> getPage(@RequestBody String getStr) {
        try {
            PortalDataGroupListCondition getCondition = JSON.parseObject(getStr, PortalDataGroupListCondition.class);
            return portalAreaContentService.getPage(getCondition);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @ResponseBody
    @RequestMapping("/getGroupVo")
    public PortalDataGroupListVo getGroupVo(@RequestBody String getStr) {
        try {
            PortalDataGroupListCondition condition = JSON.parseObject(getStr, PortalDataGroupListCondition.class);
            return portalAreaContentService.getGroupVo(condition);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(@RequestBody String delStr) {
        try {
            if (StringUtils.isBlank(delStr)) {
                return JsonMessage.create(-1L, "参数不能为空", "");
            }
            PortalDataGroupListCondition delCodition = JSON.parseObject(delStr, PortalDataGroupListCondition.class);
            if (null == delCodition) {
                return JsonMessage.create(-1L, "反序列化失败", "");
            }
            Integer delete = portalAreaContentService.delete(delCodition);

            if (delete > 0) {
                return JsonMessage.create(0, "", "");
            } else if (delete < 0) {
                return JsonMessage.create(-1, "数据已经与Tab管理管理,无法删除", "");
            } else {
                return JsonMessage.create(-1L, "数据库删除失败", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonMessage.create(-1L, e.getMessage(), "");
        }
    }


    @ResponseBody
    @RequestMapping("/save")
    public Object save(@RequestBody String saStr) {
        try {
            if (StringUtils.isBlank(saStr)) {
                return JsonMessage.create(-1L, "参数不能为空", "");
            }
            PortalDataGroupListCondition saCondition = JSON.parseObject(saStr, PortalDataGroupListCondition.class);
            if (null == saCondition) {
                return JsonMessage.create(-1L, "反序列化失败", "");
            }
            if (portalAreaContentService.save(saCondition) > 0) {
                return JsonMessage.create(0, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库插入失败", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonMessage.create(-1L, e.getMessage(), "");
        }
    }


    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody String upStr) {
        try {
            if (StringUtils.isBlank(upStr)) {
                return JsonMessage.create(-1L, "参数不能为空", "");
            }
            PortalDataGroupListCondition upCondition = JSON.parseObject(upStr, PortalDataGroupListCondition.class);
            if (null == upCondition) {
                return JsonMessage.create(-1L, "反序列化失败", "");
            }
            if (portalAreaContentService.update(upCondition) > 0) {
                return JsonMessage.create(0, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库更新失败", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonMessage.create(-1L, e.getMessage(), "");
        }

    }

    @ResponseBody
    @RequestMapping("/updateEffective")
    public Object updateEffective(@RequestBody String upStr) {
        try {
            if (StringUtils.isBlank(upStr)) {
                return JsonMessage.create(-1L, "参数不能为空", "");
            }
            PortalDataGroupListCondition upCondition = JSON.parseObject(upStr, PortalDataGroupListCondition.class);
            if (null == upCondition) {
                return JsonMessage.create(-1L, "反序列化失败", "");
            }
            if (portalAreaContentService.updateEffective(upCondition) > 0) {
                return JsonMessage.create(0, "", "");
            } else {
                return JsonMessage.create(-1L, "数据库更新失败", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonMessage.create(-1L, e.getMessage(), "");
        }

    }

    @RequestMapping(value = {"/getDataGroupList/{id}/{userId}/{mac}/{sn}/{type}/{page}/{size}/{version}"}, method = RequestMethod.GET)
    @ResponseBody
    public Object getDataGroupList(@PathVariable Integer id, @PathVariable Integer userId,
                                   @PathVariable String mac, @PathVariable String sn, @PathVariable Integer type, @PathVariable Integer page,
                                   @PathVariable Integer size, @PathVariable String version) {
        try {
            if (StringUtils.isBlank(id + "")) {
                return JsonMessage.create(-1L, "id不能为空", "");
            }

            if (StringUtils.isBlank(version + "")) {
                return JsonMessage.create(-1L, "version不能为空", "");
            }
            if (StringUtils.isBlank(userId + "")) {
                return JsonMessage.create(-1L, "userId不能为空", "");
            }
            List<PortalFixedRecommendContentVo> list;
            //赋参数
            PortalFixedRecomContentCondition condition = new PortalFixedRecomContentCondition();
            condition.setFixedRecomId(id);
            condition.setPageIndex(page);
            condition.setPageSize(size);
            holder.setDBType(DBHelper.DB_TYPE_R);
            //添加缓存
            String redisKey = "R_K_DATA_GROUP_LIST" + "_" + id + "_" + mac + "_" + sn + "_" + userId + "_" + type + "_" + page + "_" + size + "_" + version;
            if (redisService.exists(redisKey)) {
                String val = redisService.get(redisKey);
                list = JSON.parseObject(val, new TypeReference<List<PortalFixedRecommendContentVo>>() {
                });
            } else {
                Integer index = new Random().nextInt(fixedArray.length);
                synchronized (fixedArray[index]) {
                    if (redisService.exists(redisKey)) {
                        String val = redisService.get(redisKey);
                        list = JSON.parseObject(val, new TypeReference<List<PortalFixedRecommendContentVo>>() {
                        });
                    } else {
                        list = portalAreaContentService.getDataGroupList(condition, userId, mac, sn, type);
                        if (list != null && list.size() > 0) {
                            redisService.setEx(redisKey, 21600, JSON.toJSONString(list));
                        }
                    }
                }

            }
            holder.clearDBType();
            return JsonMessage.create(0, "", list);
        } catch (Exception ex) {
            return JsonMessage.create(-1L, ex.getMessage(), "");
        }
    }

}
