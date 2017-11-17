package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/10/12.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PortalNotStartInstructionListVo implements Serializable {
    private Integer id;
    private String appName;//启动指令名称
    private Integer startApk;//启动APK
    private String actionStr;//action指令
    private Integer effective;//状态
    private String apkName;//apk名称
    private String versionNo;//版本号
    private String versionUrl;//下载地址
    private String packageName;//包名
    private String createTime;
    private String updateTime;
    private String apkNames;//启动APK名称
    private Integer installStyle;//安装方式  0 询问安装 1 静默安装
    private Integer appType;//应用类型，0系统应用，1定制应用
}
