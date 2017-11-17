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
public class PortalLauncherTempletContentCondition extends BaseCondition implements Serializable{

    private Integer id;
    private Integer templetId;
    private Integer recommendId;
    private String recomName;
    private Integer operType;
    private Integer recommendType;
    private Integer seq;
    private Date createTime;
}
