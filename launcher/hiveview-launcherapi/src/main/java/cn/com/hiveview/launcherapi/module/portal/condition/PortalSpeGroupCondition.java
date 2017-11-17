package cn.com.hiveview.launcherapi.module.portal.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xiach on 2017/10/10.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class PortalSpeGroupCondition extends BaseCondition implements Serializable {

    private Integer id;
    private String speGroupName; //名称
    private String speGroupTitle; //标题
    private Date createTime;

}
