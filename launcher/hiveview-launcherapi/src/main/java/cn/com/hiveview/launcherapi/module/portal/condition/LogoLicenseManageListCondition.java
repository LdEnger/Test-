package cn.com.hiveview.launcherapi.module.portal.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/7/5.
 */

@EqualsAndHashCode(callSuper = false)
@Data
public class LogoLicenseManageListCondition extends BaseCondition implements Serializable{

    private Integer logoId;
    private String logoName;
    private String logoImg;
    private Integer isOnline;
    private String createTime;
    private String updateTime;

}
