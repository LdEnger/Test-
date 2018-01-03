package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by admin on 2017/11/22.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class PortalJumpInstructionVo {
    private Integer id;
    private String type;
    private String actionName;
    private Integer effective;
    private Integer startApk;
    private String versionNo;//版本号
    private String versionUrl;//下载地址
    private String packageName;//包名
    private Integer installStyle;//安装方式  0 询问安装 1 静默安装
    private Integer appType;//应用类型，0系统应用，1定制应用
}
