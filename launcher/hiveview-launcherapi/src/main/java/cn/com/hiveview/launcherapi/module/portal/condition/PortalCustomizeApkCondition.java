package cn.com.hiveview.launcherapi.module.portal.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/19.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class PortalCustomizeApkCondition extends BaseCondition implements Serializable {
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
