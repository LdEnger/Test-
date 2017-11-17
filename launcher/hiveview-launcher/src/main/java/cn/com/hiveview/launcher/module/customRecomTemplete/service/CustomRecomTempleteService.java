package cn.com.hiveview.launcher.module.customRecomTemplete.service;

import cn.com.hiveview.launcher.utils.ConstantsUtil;
import cn.com.hiveview.launcher.utils.HttpsUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created by user on 2017/7/20.
 */
@Service
public class CustomRecomTempleteService {
    static Log logger = LogFactory.getLog(CustomRecomTempleteService.class);

    public Object getList(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "customRecomTempleteList/getPageList";
            rev =  HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }

    public Object getCustomRecomTemplete(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "customRecomTempleteList/getCustomRecomTemplete";
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
            String url = ConstantsUtil.domain + "customRecomTempleteList/save";
            rev = HttpsUtil.doPost(url ,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return rev;
    }
    public Object saveTemplete(Object t){
        Object rev = null;
        try{
            String url = ConstantsUtil.domain + "customRecomTempleteList/saveTemplete";
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
            String url = ConstantsUtil.domain + "customRecomTempleteList/update";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }
    public Object getRolCow(Object t){
        Object rev = null;
        try{
            String url = ConstantsUtil.domain + "customRecomTempleteList/getRolCow";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }
    public Object updateTempleteLayout(Object t){
        Object rev = null;
        try{
            String url = ConstantsUtil.domain + "customRecomTempleteList/updateTempleteLayout";
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
            String url = ConstantsUtil.domain + "customRecomTempleteList/delete";
            rev  = HttpsUtil.doPost(url ,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }

    public  Object getLayout(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "customRecomTempleteList/getLayout";
            rev  = HttpsUtil.doPost(url ,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }

    public  Object setFatherSelect(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "customRecomTempleteList/setFatherSelect";
            rev  = HttpsUtil.doPost(url ,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }

    public  Object getContent(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "customRecomTempleteList/getContent";
            rev  = HttpsUtil.doPost(url ,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }

    public Object updateIsEffective(Object t){
        Object rev = null;
        try{
            String url = ConstantsUtil.domain + "customRecomTempleteList/updateIsEffective";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }

    public Object selectFatherId(Object t){
        Object rev = null;
        try{
            String url = ConstantsUtil.domain + "customRecomTempleteList/selectFatherId";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }
    public Object selectByTempleteId(Object t){
        Object rev = null;
        try{
            String url = ConstantsUtil.domain + "customRecomTempleteList/selectByTempleteId";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }
    public Object getFirstList(Object t){
        Object rev = null;
        try{
            String url = ConstantsUtil.domain + "customRecomTempleteList/getFirstList";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }
}
