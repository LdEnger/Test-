package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by admin on 2017/7/11.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class PortalScreenRecommendListApiVo implements Serializable {
    private Integer templetId;
    private Integer recommendId;
    private String timeSpan;
    private String title;
    private String secondTitle;
    private Integer isPlay;
    private String bigPic;
    //private Integer albumType;
    private String apkName;
    private Integer cid;
    private Integer contentId;
    //private String recommendName;
    private Integer recommendType;
    private Integer contentType;
    private Integer seq;
    //private Integer isUseApp;
    private String contentImg;
    //private String contentName;
    //private Integer isTxtShow;
    //private Integer isEffective;
    private Integer hotWordId;
    //private Integer qiyiVip;
}
