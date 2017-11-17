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
public class CloudAppSubjectService {
    static Log logger = LogFactory.getLog(CloudAppSubjectService.class);

    public Object getCloudAppSubjectList(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "cloudAppSubject/getPageList";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;

    }
}
