package cn.com.hiveview.entity.module.portal;

import cn.com.hiveview.entity.module.common.ScriptPage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by admin on 2017/6/30.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class PortalScreenRecommendList implements Serializable {
    private Integer templetId;
    private Integer type;
    private Integer recommendId;
    private String timeSpan;
    private String title;
    private String secondTitle;
    private Integer isPlay;
    private String bigPic;
    private Integer albumType;
    private String apkName;
    private Integer cid;
    private String contentFocus;
    private Integer contentId;
    private String recommendName;
    private Integer recommendType;
    private Integer count;
    private Integer imageType;//图片类型  0 大图  1横图 2竖图
    private Integer contentType;
    private Integer position;
    private Integer seq;
    private Integer isUseApp;
    private String contentImg;
    private String contentName;
    private String contentUpdate;
    private Integer contentTotal;
    private Integer isTxtShow;
    private Integer isEffective;
    private String albumStream;
    private String videosetStream;
    private String cname;
    private Integer hotWordId;
    private Integer isPay;
    private Integer qiyiVip;
    private Integer isVip;
    private Integer qiyiPay;
    private Integer qiyiEffective;
    private Integer jqEffective;
    private Integer count1;
    private Integer count2;
}
