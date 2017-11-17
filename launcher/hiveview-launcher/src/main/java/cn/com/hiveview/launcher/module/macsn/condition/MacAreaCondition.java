package cn.com.hiveview.launcher.module.macsn.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Administrator on 2017/7/13.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class MacAreaCondition {
    private Integer id;
    private String mac;
    private String sn;
    private String areaCode;
    private String areaName;
    public int page;
    public int rows;
}
