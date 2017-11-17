package cn.com.hiveview.launcher.module.portal.service;

import cn.com.hiveview.launcher.utils.ConstantsUtil;
import cn.com.hiveview.launcher.utils.HttpsUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2017/7/31.
 */
@Service
public class AppCategoryService {
    static Log logger = LogFactory.getLog(AppCategoryService.class);

    public Object getAppCategoryList(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "appCategory/getList";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;
    }
}
