package cn.com.hiveview.launcher.module.apk.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created by Administrator on 2017/7/19.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class ApkVersionCondition {
    private Integer id;
    private Integer apkId;
    private String versionNo;
    private Integer isLatestVersion;
    private String packageName;
    private String versionDescribe;
    private String versionUrl;
    private Date createTime;
    private Date updateTime;
    public int page;
    public int rows;
}
