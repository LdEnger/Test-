package cn.com.hiveview.launcher.module.beanCurd.service;

import cn.com.hiveview.launcher.utils.ConstantsUtil;
import cn.com.hiveview.launcher.utils.HttpsUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.stereotype.Service;

/**
 * Created by user on 2017/7/25.
 */
@Service
public class PortalBeanCurdEditListService {
    static Log logger = LogFactory.getLog(PortalBeanCurdEditListService.class);

    public  Object getPageList(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "portalBeanCurdEditListController/getPageList";
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
            String url = ConstantsUtil.domain + "portalBeanCurdEditListController/delete";
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
            String url = ConstantsUtil.domain + "portalBeanCurdEditListController/save";
            rev =HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }

    public Object update(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "portalBeanCurdEditListController/update";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }
    public  Object getList(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "portalBeanCurdEditListController/getList";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }
    public Object getEditList(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "portalBeanCurdEditListController/getEditList";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }
    public  Object getEdit(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "portalBeanCurdEditListController/getEdit";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }
    public Object getEditCount(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "portalBeanCurdEditListController/getEditCount";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }
}
