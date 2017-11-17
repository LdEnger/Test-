package cn.com.hiveview.launcher.module.portal.service;

import cn.com.hiveview.launcher.utils.ConstantsUtil;
import cn.com.hiveview.launcher.utils.HttpsUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2017/8/1.
 */
@Service
public class TempletHotWordService {
    static Log logger = LogFactory.getLog(TempletHotWordService.class);

    public Object getList(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "templetHotWord/getList";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;

    }
}
