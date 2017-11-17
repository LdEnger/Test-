package cn.com.hiveview.launcher.module.dataGroup.service;

import cn.com.hiveview.core.captcha.CaptchaUtil;
import cn.com.hiveview.launcher.utils.ConstantsUtil;
import cn.com.hiveview.launcher.utils.HttpsUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created by user on 2017/10/9.
 */
@Service
public class PortalDataGroupService {

    static Log logger = LogFactory.getLog(PortalDataGroupService.class);

    public  Object getPage(Object t){
        Object rev = null;
        try{
            String url = ConstantsUtil.domain  + "PortalDataGroupController/getPage";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }

    public  Object save(Object t){
        Object rev = null;
        try{
            String url = ConstantsUtil.domain + "PortalDataGroupController/save";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception  e ){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }
    public  Object delete(Object t){
        Object rev = null;
        try{
            String url = ConstantsUtil.domain + "PortalDataGroupController/delete";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e ){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }

    public  Object update(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "PortalDataGroupController/update";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }
    public  Object updateEffective(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "PortalDataGroupController/updateEffective";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }

    public Object getAppPage(Object t){
        Object rev= null;
        try {
            String url = ConstantsUtil.domain + "AppTag/getPage";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return rev;
    }
    public Object getGroupVo(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "PortalDataGroupController/getGroupVo";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }
}
