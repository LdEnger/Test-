package cn.com.hiveview.launcher.module.portal.service;
import cn.com.hiveview.launcher.utils.ConstantsUtil;
import cn.com.hiveview.launcher.utils.HttpsUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2017/7/10.
 */
@Service
public class ContentPortalService {
    static Log logger = LogFactory.getLog(ContentPortalService.class);

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

}
