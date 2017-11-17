package cn.com.hiveview.launcher.module.dataGroup.service;

import cn.com.hiveview.launcher.utils.ConstantsUtil;
import cn.com.hiveview.launcher.utils.HttpsUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created by user on 2017/10/12.
 */
@Service
public class NewTempletApkChannelService {

    static Log logger = LogFactory.getLog(NewTempletApkChannelService.class);

    public  Object getChannelPage(Object t){
        Object rev = null;
        try{
            String url = ConstantsUtil.domain  + "NewTempletApkChannelController/getChannelPage";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }


    public Object getApp(Object t){
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "appCategory/getList";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return rev;
    }
}
