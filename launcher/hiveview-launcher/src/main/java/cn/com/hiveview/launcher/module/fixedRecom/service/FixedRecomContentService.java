package cn.com.hiveview.launcher.module.fixedRecom.service;

import cn.com.hiveview.launcher.utils.ConstantsUtil;
import cn.com.hiveview.launcher.utils.HttpsUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created by user on 2017/7/25.
 */
@Service
public class FixedRecomContentService {
    static Log logger = LogFactory.getLog(FixedRecomContentService.class);

    public  Object getPageList(Object t){
        Object rev = null;
        try{
            String url = ConstantsUtil.domain  + "fixedRecommendContent/getPageList";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }
    public  Object getFixedRecommendContentList(Object t){
        Object rev = null;
        try{
            String url = ConstantsUtil.domain  + "fixedRecommendContent/getFixedRecommendContentList";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }
    public  Object getFixedRecommendContentOne(Object t){
        Object rev = null;
        try{
            String url = ConstantsUtil.domain  + "fixedRecommendContent/getFixedRecommendContentOne";
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
            String url = ConstantsUtil.domain + "fixedRecommendContent/save";
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
            String url = ConstantsUtil.domain + "fixedRecommendContent/delete";
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
            String url = ConstantsUtil.domain + "fixedRecommendContent/update";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;

    }
    public  Object getAppPageList(Object t){
        Object rev = null;
        try{
            String url = ConstantsUtil.domain  + "app/getPageList";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }

        return  rev;
    }
    public  Object getNewContentChanPageList(Object t){
        Object rev = null;
        try{
            String url = ConstantsUtil.domain  + "newContentChan/getPageList";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }

    public  Object getMinMapping(Object t){
        Object rev = null;
        try{
            String url = ConstantsUtil.domain  + "fixedRecommendContent/getMinMapping";
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
            String url = ConstantsUtil.domain  + "fixedRecommendContent/getMaxMapping";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }

    public  Object getMinSeq(Object t){
        Object rev = null;
        try{
            String url = ConstantsUtil.domain  + "fixedRecommendContent/getMinSeq";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }
}
