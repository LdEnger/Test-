package cn.com.hiveview.launcher.module.bigpics.service;

import cn.com.hiveview.launcher.module.logoLicenseManage.service.LogoLicenseManageService;
import cn.com.hiveview.launcher.utils.ConstantsUtil;
import cn.com.hiveview.launcher.utils.HttpsUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.protocol.HTTP;
import org.springframework.stereotype.Service;

/**
 * Created by user on 2017/7/11.
 */
@Service
public class BigPicsService {
    static Log logger = LogFactory.getLog(BigPicsService.class);

    public Object getPageList(Object t){
        Object rev = null;
        try{
            String url = ConstantsUtil.domain + "screenBigPicsList/getList";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }

    public Object save(Object t){
        Object rev = null;
        try{
            String url = ConstantsUtil.domain + "screenBigPicsList/save";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return rev;
    }

    public Object del(Object t){
        Object rev = null;
        try{
            String url = ConstantsUtil.domain + "screenBigPicsList/delete";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }
    public Object update(Object t){
        Object rev = null;
        try{
            String url = ConstantsUtil.domain + "screenBigPicsList/update";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }

    public Object updateBig(Object t){
        Object rev = null;
        try{
            String url = ConstantsUtil.domain + "screenBigPicsList/updateBig";
            rev = HttpsUtil.doPost(url,t);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return  rev;
    }

}
