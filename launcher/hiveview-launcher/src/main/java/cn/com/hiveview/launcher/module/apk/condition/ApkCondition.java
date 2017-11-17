package cn.com.hiveview.launcher.module.apk.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created by Administrator on 2017/7/19.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class ApkCondition {
    private Integer id;
    private String apkName;
    private String lastVersion;
    private Integer isEffective;
    private String equipmentNos;
    private Integer installStyle;
    private Date createTime;
    private Date updateTime;
    private String packageName;
    private Integer appType;
    public int page;
    public int rows;
}
