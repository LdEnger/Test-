package cn.com.hiveview.launcherapi.module.portal.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by admin on 2017/7/31.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class ActivityFreeVipCondition extends BaseCondition implements Serializable {
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
}
