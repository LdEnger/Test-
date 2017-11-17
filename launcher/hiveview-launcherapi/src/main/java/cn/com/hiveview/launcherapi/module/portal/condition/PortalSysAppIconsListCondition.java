package cn.com.hiveview.launcherapi.module.portal.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by admin on 2017/7/10.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class PortalSysAppIconsListCondition extends BaseCondition implements Serializable {
    private Integer id;
    private String appName;
    private String appPackage;
    private Integer isShow;
}
