package cn.com.hiveview.launcher.module.jumpInstruction.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by xiach on 2017/10/8.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class JumpInstructionCondition extends BaseCondition implements Serializable {
    private Integer id;
    private String type;
    private String actionName;
    private Integer effective;
}
