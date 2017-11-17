package cn.com.hiveview.launcher.module.Area.service;

import cn.com.hiveview.launcher.utils.ConstantsUtil;
import cn.com.hiveview.launcher.utils.HttpsUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created by user on 2017/10/6.
 */
@Service
public class PortalAreaAdminirationService {
    static Log logger = LogFactory.getLog(PortalAreaAdminirationService.class);


    public Object getPage(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "PortalAreaAdminirationController/getPage";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }

    public Object delete(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "PortalAreaAdminirationController/delete";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return rev;
    }
    public Object save(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "PortalAreaAdminirationController/save";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return rev;
    }
    public Object update(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "PortalAreaAdminirationController/update";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return rev;
    }
}
