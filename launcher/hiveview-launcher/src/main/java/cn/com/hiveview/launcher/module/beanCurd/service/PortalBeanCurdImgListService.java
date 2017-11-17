package cn.com.hiveview.launcher.module.beanCurd.service;

import cn.com.hiveview.launcher.utils.ConstantsUtil;
import cn.com.hiveview.launcher.utils.HttpsUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created by user on 2017/7/26.
 */
@Service
public class PortalBeanCurdImgListService {

    static Log logger = LogFactory.getLog(PortalBeanCurdImgListService.class);

    public  Object getPageList(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "portalBeanCurdImgListController/getPageList";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }

    public  Object update(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "portalBeanCurdImgListController/update";
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
            String url = ConstantsUtil.domain + "portalBeanCurdImgListController/delete";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }

    public Object getImgList(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "portalBeanCurdImgListController/getImgList";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }

    public  Object save(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "portalBeanCurdImgListController/save";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return rev;
    }
}
