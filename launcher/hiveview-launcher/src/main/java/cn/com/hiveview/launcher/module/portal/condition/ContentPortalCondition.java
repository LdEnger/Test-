package cn.com.hiveview.launcher.module.portal.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by admin on 2017/7/10.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class ContentPortalCondition implements Serializable {
    private Integer id;
    private Integer belongRecomendId;
    private String title;
    private String secondTitle;
    private Integer isPlay;
    private Integer contentType;
    private Integer contentId;
    private Integer position;
    private Integer seq;
    private Integer isUseApp;
    private String contentFocus;
    private String recommendName;
    private String apkName;
    private String contentImg;
    private String contentUpdate;
    private Integer contentTotal;
    private Integer isTxtShow;
    private Integer isEffective;
    private String albumStream;
    private String videosetStream;
    private String cname;
    private Integer cid;
    private Integer cType;
    private Integer albumType;
    private String bigPic;
    private Integer templetId;
    private Integer hotWordId;
    private Integer isPay;
    private Integer qiyiVip;
    private Integer isVip;
    private Integer qiyiPay;
    private Integer qiyiEffective;
    private Integer jqEffective;
    private Integer imageType;
    private String contentName;
    public int page ;
    public int rows;
}
