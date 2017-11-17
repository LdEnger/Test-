package cn.com.hiveview.launcher.module.appIcon.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/10.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class SysAppIconListCondition implements Serializable {
    private Integer id;//ID
    private String appName;//应用名称
    private String appPackage;//包名
    private Integer isShow;//是否显示
    public int page;
    public int rows;
}
