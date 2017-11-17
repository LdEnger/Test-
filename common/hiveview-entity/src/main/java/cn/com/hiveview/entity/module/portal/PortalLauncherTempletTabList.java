package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/27.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class PortalLauncherTempletTabList implements Serializable {
    private Integer id;
    private Integer belongTempletId;
    private Integer tabId;
    private String tabName;
    private String tabIcon;
    private Integer seq;
    private Integer seqIsTop;
    private Integer isEffective;
    private Date createTime;
    private Integer groupCount;
    private String tabTitle;
}
