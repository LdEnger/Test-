package cn.com.hiveview.launcher.module.acrossVertical.service;

import cn.com.hiveview.launcher.utils.ConstantsUtil;
import cn.com.hiveview.launcher.utils.HttpsUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created by chimeilong on 2017/7/4.
 */
@Service
public class AcrossVerticalService {
    static Log logger = LogFactory.getLog(AcrossVerticalService.class);

    public Object getScreenPortalList(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "screenRecommendList/getPageList";
            rev = HttpsUtil.doPost(url,t);
//            rev = HttpsUtil.doGet(url);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;

    }

    public Object insert(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "screenRecommendList/insert";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;

    }
    public Object update(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "screenRecommendList/update";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;

    }

    public Object del(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "screenRecommendList/del";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;

    }
}
