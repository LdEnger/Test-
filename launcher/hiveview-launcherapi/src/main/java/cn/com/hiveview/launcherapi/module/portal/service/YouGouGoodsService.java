package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.core.util.HttpUtils4;
import cn.com.hiveview.core.util.TokenUtils;
import cn.com.hiveview.core.util.YouGouConstants;
import cn.com.hiveview.entity.module.common.JsonMessage;
import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.YouGouGoods;
import cn.com.hiveview.entity.module.portal.YouGouGoodsCategory;
import cn.com.hiveview.entity.module.portal.YouGouSpecials;
import cn.com.hiveview.launcherapi.module.portal.condition.YouGouGoodsCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.YouGouSpecialsCondition;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class YouGouGoodsService {


//    public static void main(String[] args) {
//        YouGouGoodsService s = new YouGouGoodsService();
//        YouGouGoodsCondition youGouGoodsCondition = new YouGouGoodsCondition();
//        youGouGoodsCondition.setCategorySn("399363559164743680");
//        System.out.println(s.getList(youGouGoodsCondition));
//    }

    /**
     * 获取一级品牌种类列表接口
     */
    @RequestMapping(value = {"/getTopCategoryList"})
    @ResponseBody
    public Object getTopCategoryList(YouGouGoodsCondition condition) {
        try {
            String url = YouGouConstants.GOODS_TOP_CATEGORY_LIST;
            Map<String, Object> map = new HashMap<>();
            map.put("pageIndex", condition.getPage());
            map.put("pageSize", condition.getRows());
            String params = JSON.toJSONString(map);
            String token = TokenUtils.youGouToken(map);
            params = URLEncoder.encode(params, "utf-8");
            String reqUrl = url + "?params=" + params + "&token=" + token;
            String resp = HttpUtils4.get(reqUrl);
            //解析返回请求
            if (resp == null) {
                return JsonMessage.create(0, "成功", null);
            }
            JSONObject ob = JSON.parseObject(resp);
            Integer returnValue = ob.getInteger("returnValue");
            if (returnValue == null || returnValue < 0) {
                return JsonMessage.create(-1, ob.toJSONString(), null);
            }
            JSONObject data = ob.getJSONObject("data");
            JSONArray records = data.getJSONArray("records");
            if (records == null) {
                return JsonMessage.create(0, "成功", null);
            }
            List<YouGouGoodsCategory> list = new ArrayList<>();
            for (Object record1 : records) {
                JSONObject record = (JSONObject) record1;
                String categorySn = record.getString("categorySn");
                String name = record.getString("name");
                YouGouGoodsCategory youGouGoodsCategory = new YouGouGoodsCategory();
                youGouGoodsCategory.setCategorySn(categorySn);
                youGouGoodsCategory.setName(name);
                list.add(youGouGoodsCategory);
            }
            ScriptPage<YouGouGoodsCategory> sp = new ScriptPage<>();
            sp.setRows(list);
            sp.setTotal(data.getInteger("totalCount"));
            return JsonMessage.create(0, "成功", sp);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonMessage.create(-1, "YouGouGoodsService.getTopCategoryList异常", null);
        }
    }

    /**
     * 获取下级商品分类接口 为二三级
     */
    @RequestMapping(value = {"/getSubCategoryList"})
    @ResponseBody
    public Object getSubCategoryList(YouGouGoodsCondition condition) {
        try {
            String url = YouGouConstants.GOODS_SUB_CATEGORY_LIST;
            Map<String, Object> map = new HashMap<>();
            map.put("pageIndex", condition.getPage());
            map.put("pageSize", condition.getRows());
            if (!StringUtils.isEmpty(condition.getCategorySn())) {
                map.put("parentSn", condition.getCategorySn());
            }
            String params = JSON.toJSONString(map);
            String token = TokenUtils.youGouToken(map);
            params = URLEncoder.encode(params, "utf-8");
            String reqUrl = url + "?params=" + params + "&token=" + token;
            String resp = HttpUtils4.get(reqUrl);
            //解析返回请求
            if (resp == null) {
                return JsonMessage.create(0, "成功", null);
            }
            JSONObject ob = JSON.parseObject(resp);
            Integer returnValue = ob.getInteger("returnValue");
            if (returnValue == null || returnValue < 0) {
                return JsonMessage.create(-1, ob.toJSONString(), null);
            }
            JSONObject data = ob.getJSONObject("data");
            JSONArray records = data.getJSONArray("records");
            if (records == null) {
                return JsonMessage.create(0, "成功", null);
            }
            List<YouGouGoodsCategory> list = new ArrayList<>();
            for (Object record1 : records) {
                JSONObject record = (JSONObject) record1;
                String categorySn = record.getString("categorySn");
                String name = record.getString("name");
                YouGouGoodsCategory youGouGoodsCategory = new YouGouGoodsCategory();
                youGouGoodsCategory.setCategorySn(categorySn);
                youGouGoodsCategory.setName(name);
                list.add(youGouGoodsCategory);
            }
            ScriptPage<YouGouGoodsCategory> sp = new ScriptPage<>();
            sp.setRows(list);
            sp.setTotal(data.getInteger("totalCount"));
            return JsonMessage.create(0, "成功", sp);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonMessage.create(-1, "YouGouGoodsService.getSubCategoryList异常", null);
        }
    }

    /**
     * 商品种类下的所有顶级商品列表
     */
    @RequestMapping(value = {"/getList"})
    @ResponseBody
    public Object getList(YouGouGoodsCondition condition) {
        try {
            String url = YouGouConstants.GOODS_LIST;
            Map<String, Object> map = new HashMap<>();
            map.put("pageIndex", condition.getPage());
            map.put("pageSize", condition.getRows());

            if (!StringUtils.isEmpty(condition.getCategorySn())) {
                map.put("categorySn", condition.getCategorySn());
            }
            if (!StringUtils.isEmpty(condition.getGoodsName())) {
                map.put("goodsName", condition.getGoodsName());
            }
            String params = JSON.toJSONString(map);
            String token = TokenUtils.youGouToken(map);
            params = URLEncoder.encode(params, "utf-8");
            String reqUrl = url + "?params=" + params + "&token=" + token;
            String resp = HttpUtils4.get(reqUrl);
            //解析返回请求
            if (resp == null) {
                return JsonMessage.create(0, "成功", null);
            }
            JSONObject ob = JSON.parseObject(resp);
            Integer returnValue = ob.getInteger("returnValue");
            if (returnValue == null || returnValue < 0) {
                return JsonMessage.create(-1, ob.toJSONString(), null);
            }
            JSONObject data = ob.getJSONObject("data");
            JSONArray records = data.getJSONArray("records");
            if (records == null) {
                return JsonMessage.create(0, "成功", null);
            }
            List<YouGouGoods> list = new ArrayList<>();
            for (Object record1 : records) {
                JSONObject record = (JSONObject) record1;
                String goodsSn = record.getString("goodsSn");
                String name = record.getString("name");
                String productImages = record.getString("productImages");
                YouGouGoods youGouGoods = new YouGouGoods();
                youGouGoods.setGoodsSn(goodsSn);
                youGouGoods.setName(name);
                youGouGoods.setProductImages(productImages);
                list.add(youGouGoods);
            }
            ScriptPage<YouGouGoods> sp = new ScriptPage<>();
            sp.setRows(list);
            sp.setTotal(data.getInteger("totalCount"));
            return JsonMessage.create(0, "成功", sp);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonMessage.create(-1, "YouGouGoodsService.getList异常", null);
        }
    }

    public ScriptPage<YouGouGoods> getPageList(YouGouGoodsCondition condition) throws Exception {
        ScriptPage<YouGouGoods> scriptPage = new ScriptPage<YouGouGoods>();
        JsonMessage getBean = JSON.parseObject(getList(condition).toString(), JsonMessage.class);
        scriptPage= JSON.parseObject(getBean.getData().toString(), ScriptPage.class);
        return scriptPage;
    }
}
