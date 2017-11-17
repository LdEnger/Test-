package cn.com.hiveview.launcher.module.portal.service;

import cn.com.hiveview.launcher.utils.ConstantsUtil;
import cn.com.hiveview.launcher.utils.HttpsUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2017/10/14.
 */
@Service
public class YouGouSpecialsService {
    static Log logger = LogFactory.getLog(YouGouSpecialsService.class);
    public Object getYouGouSpecialsList(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "yougou/specials/getPageList";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;
    }
}
