package cn.com.hiveview.launcher.module.customRecomTemplete.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/8/28.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class CustomRecomBackupsCondition  extends BaseCondition implements Serializable {

    private Integer id;
    private Integer contentId;
    private Integer contentType;//专辑详情：1、影视专题：0、应用详情:11、应用专题:12、活动详情:2、全屏大图:13、商品包:7
    private String contentName;
    private String contentSubtitle;//副标题
    private String contentImg;
    private String contentOutcropImg;//露头图
    private String contentBigPic;//大图
    private Integer customContentImgSel;
    private Integer categoryId;//应用类型
    private Integer seq;
    private Integer isEffective;
    private Integer templeteId;
    private Integer chnId;
    private Integer chnType;
    private Integer hotId;
    private Integer hotType;
    private Integer aqyIsVip;
    private String apkBagName;
    private Integer layoutX;
    private Integer layoutY;
    private Integer layoutW;
    private Integer layoutH ;
    private String createTime;
    private String updateTime;
    private Integer layoutType;
    private Integer layoutId;
    private String templeteName;
    private Integer videoId;
    private Integer isFocusable;
    private String action;
    private Integer isPlay;
    private String apk;
    private String apkVersionCode;
    private String apkDownUrl;
    private Integer col;
    private Integer row;
    private String specSn;
    private String videoUrl;
    private Integer installStyle;
    private Integer appType;
}
