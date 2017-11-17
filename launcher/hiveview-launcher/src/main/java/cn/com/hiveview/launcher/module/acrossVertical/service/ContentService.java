package cn.com.hiveview.launcher.module.acrossVertical.service;

import cn.com.hiveview.launcher.utils.ConstantsUtil;
import cn.com.hiveview.launcher.utils.HttpsUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/7/25.
 */
@Service
public class ContentService {
    static Log logger = LogFactory.getLog(ContentService.class);

    public Object getContentPortalList(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "screentRecommendContent/getPageList";
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
            String url = ConstantsUtil.domain + "screentRecommendContent/insert";
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
            String url = ConstantsUtil.domain + "screentRecommendContent/update";
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
            String url = ConstantsUtil.domain + "screentRecommendContent/del";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;
    }

    public Object getMaxSeq(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "screentRecommendContent/getMaxSeq";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;
    }

    public Object getMinSeq(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "screentRecommendContent/getMinSeq";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;
    }

    public Object getMaxContent(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "screentRecommendContent/getMaxContent";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;
    }

    public Object getMinContent(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "screentRecommendContent/getMinContent";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;
    }

    public Object getNewContentChanPageList(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "newContentChan/getPageList";
            rev = HttpsUtil.doPost(url,t);
//            rev = HttpsUtil.doGet(url);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;

    }
}
