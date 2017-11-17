package cn.com.hiveview.launcherapi.module.portal.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by user on 2017/7/25.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PortalLauncherTempletTabCondition extends BaseCondition implements Serializable{
    private Integer id;
    private Integer belongTempletId;
    private Integer tabId;
    private String tabName;
    private String tabIcon;
    private Integer seq;
    private Integer seqIsTop;
    private Integer isEffective;
    private Date createTime;
}
