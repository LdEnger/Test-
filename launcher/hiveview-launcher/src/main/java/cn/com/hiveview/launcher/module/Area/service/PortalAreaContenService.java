package cn.com.hiveview.launcher.module.Area.service;

import cn.com.hiveview.launcher.utils.ConstantsUtil;
import cn.com.hiveview.launcher.utils.HttpsUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created by user on 2017/10/8.
 */
@Service
public class PortalAreaContenService  {

    static Log logger = LogFactory.getLog(PortalAreaContenService.class);


    public Object getPage(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "PortalAreaContenController/getPage";
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
            String url = ConstantsUtil.domain + "PortalAreaContenController/delete";
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
            String url = ConstantsUtil.domain + "PortalAreaContenController/save";
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
            String url = ConstantsUtil.domain + "PortalAreaContenController/update";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return rev;
    }

    public Object getAreaMinSeq(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "PortalAreaContenController/getAreaMinSeq";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return rev;
    }


    public Object getAreaMaxSeq(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "PortalAreaContenController/getAreaMaxSeq";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return rev;
    }
}
