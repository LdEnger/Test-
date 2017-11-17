package cn.com.hiveview.launcher.module.customRecomTemplete.service;

import cn.com.hiveview.launcher.utils.ConstantsUtil;
import cn.com.hiveview.launcher.utils.HttpsUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created by user on 2017/8/28.
 */
@Service
public class CustomRecomBackupsService {

    static Log logger = LogFactory.getLog(CustomRecomTempleteService.class);

    public Object getList(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "customRecomBackupsController/getPageList";
            rev =  HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }

    public Object get(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "customRecomBackupsController/get";
            rev =  HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }

    public Object getCount(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "customRecomBackupsController/getCount";
            rev =  HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }

    public  Object save(Object t){
        Object rev = null;
        try{
            String url = ConstantsUtil.domain + "customRecomBackupsController/save";
            rev = HttpsUtil.doPost(url ,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return rev;
    }

    public Object update(Object t){
        Object rev = null;
        try{
            String url = ConstantsUtil.domain + "customRecomBackupsController/update";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }

    public  Object delete(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "customRecomBackupsController/delete";
            rev  = HttpsUtil.doPost(url ,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }

    public  Object getMinMapping(Object t){
        Object rev = null;
        try{
            String url = ConstantsUtil.domain  + "customRecomBackupsController/getMinMapping";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }

    public  Object getMaxMapping(Object t){
        Object rev = null;
        try{
            String url = ConstantsUtil.domain  + "customRecomBackupsController/getMaxMapping";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }
    public  Object getMaxSeq(Object t){
        Object rev = null;
        try{
            String url = ConstantsUtil.domain  + "customRecomBackupsController/getMaxSeq";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }
    public Object selectRowCol(Object t){
        Object rev = null;
        try{
            String url = ConstantsUtil.domain + "customRecomContentList/selectRowCol";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }
}
