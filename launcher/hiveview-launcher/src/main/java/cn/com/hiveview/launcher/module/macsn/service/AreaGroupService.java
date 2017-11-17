package cn.com.hiveview.launcher.module.macsn.service;

import cn.com.hiveview.launcher.utils.ConstantsUtil;
import cn.com.hiveview.launcher.utils.HttpsUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/7/14.
 */
@Service
public class AreaGroupService {
    static Log logger = LogFactory.getLog(AreaGroupService.class);

    public Object getAreaGroupList(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "AreaGroupList/getPageList";
            rev = HttpsUtil.doPost(url, t);
//          rev = HttpsUtil.doGet(url);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;
    }

    public Object getAreaList(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "AreaGroupList/getList";
            rev = HttpsUtil.doPost(url, t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;
    }

    public Object getAreaGroupByCode(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "AreaGroupList/getAreaGroupByCode";
            rev = HttpsUtil.doPost(url, t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;
    }

    public Object insert(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "AreaGroupList/insert";
            rev = HttpsUtil.doPost(url,t);
            System.out.println(rev);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;

    }
    public Object update(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "AreaGroupList/update";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;

    }

    public Object del(Object t) {
        Object rev = null;
        try {
            String url = ConstantsUtil.domain + "AreaGroupList/del";
            rev = HttpsUtil.doPost(url,t);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        return rev;

    }
}
