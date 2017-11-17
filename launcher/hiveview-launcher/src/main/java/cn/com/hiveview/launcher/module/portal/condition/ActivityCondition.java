package cn.com.hiveview.launcher.module.portal.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by admin on 2017/7/31.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class ActivityCondition extends BaseCondition implements Serializable {
    private Integer activityId;
    // 活动名称
    private String activityName;
    // 活动海报图
    private String activityImg;
    // 活动状态 0未发布 1已发布
    private Integer activityStatus;
    //是否是VIP 0 否 1是
    private Integer isVip;
    // 创建时间
    private String createTime;
    // 更新时间
    private String updateTime;
    //模板id
    private Integer templetId;
    //apk包名
    private String apkBagName;
}
