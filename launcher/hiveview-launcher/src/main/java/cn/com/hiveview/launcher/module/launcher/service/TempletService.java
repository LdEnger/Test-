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
public class TempletService {
    static Log logger = LogFactory.getLog(MacAreaService.class);

    public Object getPageList(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "templetList/getPageList";
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
            String url = ConstantsUtil.domain + "templetList/insert";
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
            String url = ConstantsUtil.domain + "templetList/update";
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
            String url = ConstantsUtil.domain + "templetList/del";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;

    }

    public Object getStartList(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "startInstructionList/getComboboxList";
            rev = HttpsUtil.doPost(url, t);
//          rev = HttpsUtil.doGet(url);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;

    }
    public Object getLogoList(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "logoManageList/getLogoList";
            rev = HttpsUtil.doPost(url, t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;

    }

    public Object getImageList(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "screenRecommendList/getComboboxList";
            rev = HttpsUtil.doPost(url, t);
//          rev = HttpsUtil.doGet(url);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;

    }
    public Object getBlockList(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "portalBeanCurdList/getCurdList";
            rev = HttpsUtil.doPost(url, t);
//          rev = HttpsUtil.doGet(url);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;

    }

    public Object addCopy(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "templetList/addCopy";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;

    }
}
