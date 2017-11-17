package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by admin on 2017/10/18.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PortalRecommendEntity implements Serializable {
    private Integer id;
    private Integer contentId;
    private Integer contentType;//内容类型（专辑详情：1、影视专题：0、应用详情:2、应用专题:3、活动详情:4、全屏大图:6、商品包:7 优购专题: 8 优购详情: 9 tab页:10 会员活动:11 启动应用:12）
    private String contentName;
    private String contentSubtitle;//副标题
    private String contentImg;
    private String contentOutcropImg;//露头图
    private String contentBigPic;//大图
    private Integer templeteId;
    private Integer layoutId;
    private Integer chnId;
    private Integer chnType;
    private Integer hotId;
    private Integer hotType;
    private String apkBagName;
    private Integer layoutX;
    private Integer layoutY;
    private Integer layoutW;
    private Integer layoutH ;
    private Integer videoId;
    private Integer isFocusable;
    private String action;
    private String apkVersionCode;
    private String apkDownUrl;
    private String specSn;
    private Integer col;
    private Integer row;
    private String videoUrl;
    private Integer installStyle;//安装方式  0 询问安装 1 静默安装
    private Integer appType;//应用类型，0系统应用，1定制应用
    private String apk;
}
