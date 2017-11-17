package cn.com.hiveview.launcher.module.customRecomTemplete.service;

import cn.com.hiveview.launcher.utils.ConstantsUtil;
import cn.com.hiveview.launcher.utils.HttpsUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created by user on 2017/8/9.
 */
@Service
public class CustomRecomContentService {

    static Log logger = LogFactory.getLog(CustomRecomTempleteService.class);

    public  Object save(Object t){
        Object rev = null;
        try{
            String url = ConstantsUtil.domain + "customRecomContentList/save";
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
            String url = ConstantsUtil.domain + "customRecomContentList/update";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }

    public Object delete(Object t){
        Object rev = null;
        try{
            String url = ConstantsUtil.domain + "customRecomContentList/delete";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }

    public Object getCount(Object t){
        Object rev = null;
        try{
            String url = ConstantsUtil.domain + "customRecomContentList/getCount";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }
    public Object saveCopyContent(Object t){
        Object rev = null;
        try{
            String url = ConstantsUtil.domain + "customRecomContentList/saveCopyContent";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }

}
