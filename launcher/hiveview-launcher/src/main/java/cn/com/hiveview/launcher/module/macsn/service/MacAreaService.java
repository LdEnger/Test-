package cn.com.hiveview.launcher.module.macsn.service;

import cn.com.hiveview.entity.module.portal.PortalMacAreaList;
import cn.com.hiveview.launcher.module.macsn.condition.MacAreaCondition;
import cn.com.hiveview.launcher.utils.ConstantsUtil;
import cn.com.hiveview.launcher.utils.HttpsUtil;
import com.alibaba.druid.util.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2017/7/13.
 */
@Service
public class MacAreaService {
    static Log logger = LogFactory.getLog(MacAreaService.class);

    public Object getMacAreaList(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "MacAreaList/getPageList";
            rev = HttpsUtil.doPost(url, t);
//          rev = HttpsUtil.doGet(url);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;

    }

    public Object insert(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "MacAreaList/insert";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
/*        System.out.println(rev);*/
        return rev;

    }
    public Object update(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "MacAreaList/update";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;

    }

    public Object del(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "MacAreaList/del";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;

    }
    public Integer upExcelServer(MultipartFile file, String upload_path, String areaCode, String areaName) {
        String fileName = file.getOriginalFilename();
        String type = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        String name = fileName.substring(0, fileName.indexOf("."));
        File uploadFile = new File(upload_path);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
        Date currentDate = new Date();
        String date = sdf.format(currentDate);
        // 如果文件夹不存在则创建
        if (!uploadFile.exists() && !uploadFile.isDirectory()) {
            uploadFile.mkdir();
        }
        String excelPath = upload_path + name + "_" + date+"_"+type;
        FileOutputStream out = null;
        InputStream in = null;
        byte[] buffer = new byte[2048];
        try {
            out = new FileOutputStream(excelPath);
            in = file.getInputStream();
            int len = buffer.length;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return 0;
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        }
        Integer result = this.excelHandleWorker(excelPath, areaCode, areaName);
        return result;
    }

    public Integer excelHandleWorker(String excelPath, String areaCode, String areaName) {
        Integer result = 0;
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(excelPath);
            Workbook wb = WorkbookFactory.create(inputStream);
            Sheet readsheet = wb.getSheetAt(0);
            int rowLen = readsheet.getLastRowNum();
            for (int i = 0; i <= rowLen+1; i++) {
                Row row = readsheet.getRow(i);
                // 当前行为空
                if (row == null) {
                    continue;
                }
                try {
                    MacAreaCondition ma = new MacAreaCondition();
                    String mac = row.getCell(0).toString();
                    String sn = row.getCell(1).toString();
                    if(StringUtils.isEmpty(mac)|| StringUtils.isEmpty(sn)){
                        continue;
                    }else{
                        ma.setMac(mac.replaceAll(":", "").replaceAll("-", "").toUpperCase());
                        ma.setSn(sn.toUpperCase());
                        //如果mac sn 如果存在则记录下来，不做导入
                        String url = ConstantsUtil.domain + "MacAreaList/getList";
                        Object obj = HttpsUtil.doPost(url,ma);
                        if(!(obj.toString().isEmpty())){
                            result++;
                            continue;
                        }
                        ma.setAreaCode(areaCode);
                        ma.setAreaName(areaName);
                        String insert_url = ConstantsUtil.domain + "MacAreaList/insert";
                        HttpsUtil.doPost(insert_url,ma);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                    return -1;
                }
            }
        }
        return result;
    }
}
