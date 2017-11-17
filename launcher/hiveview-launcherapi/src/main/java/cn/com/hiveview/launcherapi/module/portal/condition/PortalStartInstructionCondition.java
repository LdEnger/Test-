package cn.com.hiveview.launcherapi.module.portal.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/12.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class PortalStartInstructionCondition extends BaseCondition implements Serializable {
    private Integer id;
    private String appName;
    private String characterString;
    private Integer instructionType;
    //private Integer startType;
    private Integer isEffective;
}
