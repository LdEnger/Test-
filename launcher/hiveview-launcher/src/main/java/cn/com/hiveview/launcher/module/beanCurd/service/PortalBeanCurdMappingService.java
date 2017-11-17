package cn.com.hiveview.launcher.module.beanCurd.service;

import cn.com.hiveview.launcher.utils.ConstantsUtil;
import cn.com.hiveview.launcher.utils.HttpsUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created by user on 2017/8/2.
 */
@Service
public class PortalBeanCurdMappingService {
    static Log logger = LogFactory.getLog(PortalBeanCurdMappingService.class);

    public  Object getPageList(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "portalBeanCurdMappingController/getPageList";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }

    public Object save(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "portalBeanCurdMappingController/save";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return rev;
    }
    public Object delete(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "portalBeanCurdMappingController/delete";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return rev;
    }
    public  Object update(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "portalBeanCurdMappingController/update";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return rev;
    }
    public Object getMinMapping(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "portalBeanCurdMappingController/getMinMapping";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return rev;
    }
    public Object getMaxMapping(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "portalBeanCurdMappingController/getMaxMapping";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return rev;
    }
    public Object getMappingCount(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "portalBeanCurdMappingController/getMappingCount";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return rev;
    }

    public Object getMinSeq(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "portalBeanCurdMappingController/getMinSeq";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return rev;
    }
}
