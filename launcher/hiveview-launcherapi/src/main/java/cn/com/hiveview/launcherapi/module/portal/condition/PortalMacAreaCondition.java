package cn.com.hiveview.launcherapi.module.portal.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/14.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class PortalMacAreaCondition  extends BaseCondition implements Serializable {
    private Integer id;
    private String mac;
    private String sn;
    private String areaCode;
    private String areaName;
}
