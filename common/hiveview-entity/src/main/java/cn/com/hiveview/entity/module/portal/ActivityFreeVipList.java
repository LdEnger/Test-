package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by admin on 2017/7/31.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class ActivityFreeVipList implements Serializable {
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

    private String beginTime;

    private String endTime;
}
