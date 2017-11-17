package cn.com.hiveview.launcher.module.vipLogo.service;

import cn.com.hiveview.launcher.module.appIcon.service.SysAppIconService;
import cn.com.hiveview.launcher.utils.ConstantsUtil;
import cn.com.hiveview.launcher.utils.HttpsUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created by xiach on 2017/10/6.
 */
@Service
public class VipLogoService {

    static Log logger = LogFactory.getLog(VipLogoService.class);

    public Object getPageList(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "portalVipLogo/getPageList";
            rev = HttpsUtil.doPost(url, t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;
    }

    public Object add(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "portalVipLogo/add";
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
            String url = ConstantsUtil.domain + "portalVipLogo/update";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;

    }

    public Object delete(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "portalVipLogo/delete";
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
            String url = ConstantsUtil.domain + "portalVipLogo/getMaxSeq";
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
            String url = ConstantsUtil.domain + "portalVipLogo/getMinSeq";
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
            String url = ConstantsUtil.domain + "portalVipLogo/getMaxContent";
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
            String url = ConstantsUtil.domain + "portalVipLogo/getMinContent";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;
    }




}

