package cn.com.hiveview.launcher.module.logoLicenseManage.service;

import cn.com.hiveview.launcher.utils.ConstantsUtil;
import cn.com.hiveview.launcher.utils.HttpsUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created by user on 2017/7/10.
 */
@Service
public class LogoLicenseManageService {

    static Log logger = LogFactory.getLog(LogoLicenseManageService.class);

    public Object getLogoManageList(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "logoManageList/getPageList";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;

    }
    public Object insert(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "logoManageList/save";
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
            String url = ConstantsUtil.domain + "logoManageList/update";
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
            String url = ConstantsUtil.domain + "logoManageList/delete";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;

    }

    public Object getLogo(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "logoManageList/getLogo";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;

    }
}
