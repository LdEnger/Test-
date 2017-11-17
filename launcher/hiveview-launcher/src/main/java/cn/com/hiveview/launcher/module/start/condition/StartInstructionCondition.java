package cn.com.hiveview.launcher.module.start.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Administrator on 2017/7/12.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class StartInstructionCondition {
    private Integer id;
    private String appName;
    private String characterString;
    private Integer instructionType;
    //private Integer startType;
    private Integer isEffective;
    public int page;
    public int rows;
}
