package cn.com.hiveview.launcherapi.module.portal.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by admin on 2017/8/2.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class AreaGroupCondition  extends BaseCondition implements Serializable {
    private Integer id; //主键ID
    private String areaCode;//所属城市Id
    private String areaName; //所属城市名字
}
