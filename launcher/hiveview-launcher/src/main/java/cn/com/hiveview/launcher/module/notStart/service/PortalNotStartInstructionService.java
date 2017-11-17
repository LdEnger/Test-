package cn.com.hiveview.launcher.module.notStart.service;

import cn.com.hiveview.launcher.utils.ConstantsUtil;
import cn.com.hiveview.launcher.utils.HttpsUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created by user on 2017/10/13.
 */

@Service
public class PortalNotStartInstructionService {
    static Log logger = LogFactory.getLog(PortalNotStartInstructionService.class);

    public Object getPage(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "PortalNotStartInstruction/getPage";
            rev = HttpsUtil.doPost(url, t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;

    }

    public Object save(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "PortalNotStartInstruction/save";
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
            String url = ConstantsUtil.domain + "PortalNotStartInstruction/update";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;

    }
    public Object updateEffice(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "PortalNotStartInstruction/updateEffice";
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
            String url = ConstantsUtil.domain + "PortalNotStartInstruction/delete";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;

    }

    public Object getPageList(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "PortalNotStartInstruction/getPageList";
            rev = HttpsUtil.doPost(url, t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;
    }

}



