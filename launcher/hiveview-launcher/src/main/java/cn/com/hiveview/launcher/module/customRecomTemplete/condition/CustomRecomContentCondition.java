package cn.com.hiveview.launcher.module.customRecomTemplete.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/8/9.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class CustomRecomContentCondition extends BaseCondition implements Serializable {

    private Integer id;
    private Integer contentId;
    private Integer contentType;
    private String contentName;
    private String contentSubtitle;
    private String contentImg;
    private String contentOutcropImg;
    private Integer categoryId;
    private Integer seq;
    private Integer isEffective;
    private Integer templeteId;
    private Integer layoutId;
    private Integer chnId;
    private Integer chnType;
    private Integer hotId;
    private Integer hotType;
    private Integer aqyIsVip;
    private String apkBagName;
    private Integer videoTempletId;
    private Integer recomTempletId;
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
}
