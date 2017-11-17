package cn.com.hiveview.launcher.module.macsn.controller;

import cn.com.hiveview.core.util.Constants;
import cn.com.hiveview.launcher.module.macsn.condition.MacAreaCondition;
import cn.com.hiveview.launcher.module.macsn.service.MacAreaService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2017/7/13.
 */
@Controller
@RequestMapping("/macsn")
public class MacAreaController {

    @Autowired
    private MacAreaService macAreaService;
    @RequestMapping(value = "list")
    public String getMacAreaList() {
        return "macsn/macAreaList";
    }

    @RequestMapping(value = "getPageList")
    @ResponseBody
    public Object getPageList(MacAreaCondition condition) {
        try {
/*            System.out.println(condition);*/
            return  macAreaService.getMacAreaList(condition);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public Object save(MacAreaCondition condition) {
        try {
            return macAreaService.insert(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(MacAreaCondition condition) {
        try {
            return  macAreaService.update(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/del")
    @ResponseBody
    public Object del(MacAreaCondition condition) {
        try {
            return  macAreaService.del(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/uploadExcel")
    @ResponseBody
    public String upLoadExcel(HttpServletResponse response, MacAreaCondition macArea, @RequestParam(value = "file", required = false) MultipartFile file) {
        String areaCode = macArea.getAreaCode();
        String areaName = macArea.getAreaName();
        Integer result = this.macAreaService.upExcelServer(file, Constants.UPLOAD_EXCEL_PATH,areaCode,areaName);
        //将实体对象转换为JSON Object转换
        JSONObject responseJSONObject = JSONObject.parseObject("{result:"+result+"}");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(responseJSONObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
        return String.valueOf(result);
    }
}
