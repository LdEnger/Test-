package cn.com.hiveview.launcherapi.module.portal.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/7/11.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PortalScreenBigPicsListCondition extends BaseCondition implements Serializable{
    private Integer imgId;
    private String imgName;
    private String imgAddr;
    private Integer tabBackground;
    private Integer isOnline;
    private String createTime;
    private String updateTime;

}
