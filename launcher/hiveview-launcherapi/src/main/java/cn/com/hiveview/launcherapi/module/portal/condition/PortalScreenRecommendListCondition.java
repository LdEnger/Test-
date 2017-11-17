package cn.com.hiveview.launcherapi.module.portal.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by admin on 2017/6/29.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class PortalScreenRecommendListCondition extends BaseCondition implements Serializable {
    private Integer templetId;
    private Integer type;
    private Integer recommendId;
    private String timeSpan;
    private String title;
    private String secondTitle;
    private Integer isPlay;
    private String bigPic;
    private Integer albumType;
    private String apkName;
    private Integer cid;
    private String contentFocus;
    private Integer contentId;
    private String recommendName;
    private Integer recommendType;
    private Integer count;
    private Integer imageType;
    private Integer isEffective;
}
