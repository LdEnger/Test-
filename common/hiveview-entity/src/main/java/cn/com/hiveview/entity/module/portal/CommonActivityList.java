package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by admin on 2017/7/31.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class CommonActivityList implements Serializable {
    // 活动类型code
    private Integer typeCode;
    // 活动名称
    private String typeName;
}
