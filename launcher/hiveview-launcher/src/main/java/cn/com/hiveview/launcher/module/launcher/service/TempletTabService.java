package cn.com.hiveview.launcher.module.launcher.service;

import cn.com.hiveview.launcher.module.macsn.service.MacAreaService;
import cn.com.hiveview.launcher.utils.ConstantsUtil;
import cn.com.hiveview.launcher.utils.HttpsUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/7/27.
 */
@Service
public class TempletTabService {
    static Log logger = LogFactory.getLog(MacAreaService.class);

    public Object getPageList(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "templetTabList/getPageList";
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
            String url = ConstantsUtil.domain + "templetTabList/insert";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;

    }
    public Object moveTop(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "templetTabList/moveTop";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;

    }
    public Object moveUp(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "templetTabList/moveUp";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;

    }
    public Object moveDown(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "templetTabList/moveDown";
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
            String url = ConstantsUtil.domain + "templetTabList/update";
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
            String url = ConstantsUtil.domain + "templetTabList/del";
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
            String url = ConstantsUtil.domain + "templetTabList/getMaxSeq";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;
    }

    public Object getMinTab(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "templetTabList/getMinTab";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;
    }

    public Object getMaxTab(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "templetTabList/getMaxTab";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;
    }

    public Object getTopSeq(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "templetTabList/getTopSeq";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;
    }
    public Object preview(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "templetTabList/preview";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;
    }
}
