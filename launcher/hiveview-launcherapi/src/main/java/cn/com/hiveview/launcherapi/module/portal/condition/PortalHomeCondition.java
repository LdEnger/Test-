package cn.com.hiveview.launcherapi.module.portal.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper=false)
@Data
public class PortalHomeCondition extends BaseCondition implements Serializable {
    private String ip;
    private String mac;
    private String sn;
    private String model;
    private Integer templetId;
    private String userId;
    private String romversion;
    private String version;//版本

}
