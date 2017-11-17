package cn.com.hiveview.launcher.module.tab.service;

import cn.com.hiveview.launcher.utils.ConstantsUtil;
import cn.com.hiveview.launcher.utils.HttpsUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created by xiach on 2017/10/10.
 */
@Service
public class TabGroupService {
    static Log logger = LogFactory.getLog(TabGroupService.class);

    public Object getPageList(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "portalTabGroup/getPageList";
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
            String url = ConstantsUtil.domain + "portalTabGroup/add";
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
            String url = ConstantsUtil.domain + "portalTabGroup/update";
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
            String url = ConstantsUtil.domain + "portalTabGroup/delete";
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
            String url = ConstantsUtil.domain + "portalTabGroup/getMaxContent";
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
            String url = ConstantsUtil.domain + "portalTabGroup/getMinContent";
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
            String url = ConstantsUtil.domain + "portalTabGroup/getMaxSeq";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;
    }
    public Object getCount(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "portalTabGroup/getCount";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;
    }

    public Object addOne(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "portalTabGroup/addOne";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;

    }
    public Object checkVideo(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "portalTabGroup/checkVideo";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;
    }
}
