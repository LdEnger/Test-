package cn.com.hiveview.launcher.module.notStart.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/10/12.
 */
@Data
@EqualsAndHashCode(callSuper =  false)
public class PortalNotStartInstructionListCondition extends BaseCondition implements Serializable {
    private Integer id;
    private String appName;//启动指令名称
    private Integer startApk;//启动APK
    private String actionStr;//action指令
    private Integer effective;//状态
    private String createTime;
    private String updateTime;
    private String apkName;//启动APK名称
}
