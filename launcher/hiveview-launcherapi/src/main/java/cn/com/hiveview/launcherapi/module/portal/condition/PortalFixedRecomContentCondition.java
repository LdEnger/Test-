package cn.com.hiveview.launcherapi.module.portal.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/7/14.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PortalFixedRecomContentCondition extends BaseCondition implements Serializable{

    private Integer id;
    private Integer contentId;
    private Integer contentType;
    private String contentName;
    private String contentSubtitle;
    private String contentImg;
    private Integer fixedRecomId;
    private Integer operateContent;
    private Integer appCategory;
    private Integer chnId;
    private Integer chnType;
    private String apkBagName;
    private Integer templetId;
    private Integer qiyiIsVip;
    private String createTime;
    private String updateTime;
    private Integer seq;

}
