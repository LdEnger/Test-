package cn.com.hiveview.launcher.module.portal.service;

import cn.com.hiveview.launcher.utils.ConstantsUtil;
import cn.com.hiveview.launcher.utils.HttpsUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2017/10/15.
 */
@Service
public class YouGouGoodsService {
    static Log logger = LogFactory.getLog(YouGouGoodsService.class);
    public Object getYouGouGoodsList(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "yougou/goods/getPageList";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;
    }
    public Object getTopCategoryList(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "yougou/goods/getComboboxList";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;
    }

    public Object getSubCategoryList(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "yougou/goods/getSubComboboxList";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;
    }
}
