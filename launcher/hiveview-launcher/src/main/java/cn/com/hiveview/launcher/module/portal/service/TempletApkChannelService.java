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
public class TempletApkChannelService {

    static Log logger = LogFactory.getLog(TempletApkChannelService.class);

    public Object getChannelTypeList(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "channel/getChannelTypeList";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;

    }
    public Object getChannelList(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "channel/getChannelList";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;
    }
}
