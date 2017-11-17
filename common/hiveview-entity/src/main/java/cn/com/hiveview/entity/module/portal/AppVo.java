package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/7/27.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class AppVo implements Serializable{

    private Integer appId;
    private String appName;
    private String appIcon;
    private String appLongIcon;
    private String developer;//开发商名称
    private String developerId;
    private String appDescribe;
    private String bundleId;
    private String tagName;
    private String tagInfo;
    private Integer seq;
    private Integer state;
    private String createTime;
    private String updateTime;
    private Integer appType;//0:系统应用 1:安装应用
    private String latestVersion;
    private Integer isPay;
    private String pyName;
    private String usbDevice;
    private Integer categoryId;
    private Integer pkgId;
    private Integer tag_id;
    private Long downloads;
    private Integer platform;
    private String appVideo;
}
