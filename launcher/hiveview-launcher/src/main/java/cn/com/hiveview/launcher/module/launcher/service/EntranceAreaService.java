package cn.com.hiveview.launcher.module.launcher.service;

import cn.com.hiveview.launcher.utils.ConstantsUtil;
import cn.com.hiveview.launcher.utils.HttpsUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2017/8/2.
 */
@Service
public class EntranceAreaService {
    static Log logger = LogFactory.getLog(EntranceAreaService.class);

    public Object getDiffCityList(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "entranceArea/getDiffCityList";
            rev = HttpsUtil.doPost(url, t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;
    }
    public Object getCityByAreaId(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "entranceArea/getCityByAreaId";
            rev = HttpsUtil.doPost(url, t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;
    }
    public Object getRightCodeName(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "entranceArea/getRightCodeName";
            rev = HttpsUtil.doPost(url, t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;
    }
    public Object insert(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "entranceArea/insert";
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
            String url = ConstantsUtil.domain + "entranceArea/update";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;
    }
}
