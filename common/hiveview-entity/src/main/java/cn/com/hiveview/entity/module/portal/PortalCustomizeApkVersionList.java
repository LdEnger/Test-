package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/19.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class PortalCustomizeApkVersionList implements Serializable {
    private Integer id;
    private Integer apkId;
    private String versionNo;
    private Integer isLatestVersion;
    private String packageName;
    private String versionDescribe;
    private String versionUrl;
    private Date createTime;
    private Date updateTime;

}
