package cn.com.hiveview.launcher.module.portal.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by admin on 2017/7/31.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class ActivityFreeVipCondition implements Serializable {
    // 活动ID
    private Integer activityId;
    // 活动名称
    private String title;
    // logo图
    private String logoUrl;
    //
    private String bgUrl;
    //
    private String beginProfileUrl;
    //活动类型
    private Integer type;
    public int page ;
    public int rows;
}
