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
public class EntranceAreaCondition extends BaseCondition implements Serializable {
    private Integer areaId;
    private String templeteName;
    private String citys;
    private String cityId;
    private Integer id;
    private Integer controllerType;
    private Integer DiffCityType;
    private String cityNames;
}
