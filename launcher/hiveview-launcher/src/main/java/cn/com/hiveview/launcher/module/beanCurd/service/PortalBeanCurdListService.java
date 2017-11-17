package cn.com.hiveview.launcher.module.beanCurd.service;


import cn.com.hiveview.launcher.utils.ConstantsUtil;
import cn.com.hiveview.launcher.utils.HttpsUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created by user on 2017/7/24.
 */
@Service
public class PortalBeanCurdListService {
    static Log logger = LogFactory.getLog(PortalBeanCurdListService.class);

    public Object getPageList(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "portalBeanCurdList/getPageList";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }

    public  Object updateIs(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "portalBeanCurdList/updateIs";
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
            String url = ConstantsUtil.domain + "portalBeanCurdList/delete";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }

    public Object getCurdList(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "portalBeanCurdList/getCurdList";
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
            String url = ConstantsUtil.domain + "portalBeanCurdList/save";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }
    public Object update(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "portalBeanCurdList/update";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return rev;
    }
}
