package cn.com.hiveview.launcher.module.portal.service;

import cn.com.hiveview.launcher.utils.ConstantsUtil;
import cn.com.hiveview.launcher.utils.HttpsUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2017/10/10.
 */
@Service
public class NewVipActivityService {
    static Log logger = LogFactory.getLog(NewVipActivityService.class);
    public Object getActivityList(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "newVipActivity/getPageList";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;
    }
}
