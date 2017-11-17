package cn.com.hiveview.launcherapi.module.portal.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/7/17.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PortalTempletControllerTypeCondition extends BaseCondition implements Serializable{

    private String ip;
    private String mac;
    private String sn;
    private String equipmentNo;//硬件型号
    private String version;
    private String romversion;//ROM版本
    private Integer type;//类型
    private String userId;

}
