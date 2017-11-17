package cn.com.hiveview.launcher.module.dataGroup.service;

import cn.com.hiveview.launcher.utils.ConstantsUtil;
import cn.com.hiveview.launcher.utils.HttpsUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created by user on 2017/10/10.
 */
@Service
public class NewTempletChannelWordsService {
    static Log logger = LogFactory.getLog(NewTempletChannelWordsService.class);

    public  Object getPage(Object t){
        Object rev = null;
        try{
            String url = ConstantsUtil.domain  + "NewTempletChannel/getPage";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }

    public  Object getPageList(Object t){
        Object rev = null;
        try{
            String url = ConstantsUtil.domain  + "NewTempletChannel/getPageList";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }

}
