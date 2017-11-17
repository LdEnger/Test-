package cn.com.hiveview.launcher.module.fixedRecom.service;

import cn.com.hiveview.launcher.utils.ConstantsUtil;
import cn.com.hiveview.launcher.utils.HttpsUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2017/7/17.
 */
@Service
public class FixedRecomSevice {
    static Log logger = LogFactory.getLog(FixedRecomSevice.class);

    public  Object getPageList(Object t){
        Object rev = null;
        try{
             String url = ConstantsUtil.domain  + "fiexdRecomList/getPageList";
             rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }

    public  Object getFixedRecomListOne(Object t){
        Object rev = null;
        try{
            String url = ConstantsUtil.domain  + "fiexdRecomList/getFixedRecomListOne";
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
            String url = ConstantsUtil.domain + "fiexdRecomList/save";

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
            String url = ConstantsUtil.domain + "fiexdRecomList/delete";
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
            String url = ConstantsUtil.domain + "fiexdRecomList/update";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;

    }
    public Object getTypeList(Object t){
        Object rev = null;
        try{
         String url = ConstantsUtil.domain + "fiexdRecomList/getTypeList";
         rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return rev;
    }
    public  Object updateEffective(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "fiexdRecomList/updateEffective";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;

    }
}
