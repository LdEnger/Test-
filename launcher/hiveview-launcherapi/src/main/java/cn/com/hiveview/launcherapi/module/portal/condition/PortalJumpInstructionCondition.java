package cn.com.hiveview.launcherapi.module.portal.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by xiach on 2017/10/6.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PortalJumpInstructionCondition extends BaseCondition implements Serializable {
    private Integer id;
    private String type;
    private String actionName;
    private Integer effective;
    private Integer startApk;
    private String startApkName;
}
