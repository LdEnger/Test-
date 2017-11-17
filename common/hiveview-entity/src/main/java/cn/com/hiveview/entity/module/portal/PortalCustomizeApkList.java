package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/19.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class PortalCustomizeApkList implements Serializable {
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
}
