package cn.com.hiveview.launcher.module.jumpInstruction.service;

import cn.com.hiveview.launcher.module.vipLogo.service.VipLogoService;
import cn.com.hiveview.launcher.utils.ConstantsUtil;
import cn.com.hiveview.launcher.utils.HttpsUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created by xiach on 2017/10/8.
 */
@Service
public class JumpInstructionService {
    static Log logger = LogFactory.getLog(JumpInstructionService.class);

    public Object getPageList(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "portalJumpInstruction/getPageList";
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
            String url = ConstantsUtil.domain + "portalJumpInstruction/add";
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
            String url = ConstantsUtil.domain + "portalJumpInstruction/update";
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
            String url = ConstantsUtil.domain + "portalJumpInstruction/delete";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;

    }
    public Object getActionById(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "portalJumpInstruction/getActionById";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;

    }
}
