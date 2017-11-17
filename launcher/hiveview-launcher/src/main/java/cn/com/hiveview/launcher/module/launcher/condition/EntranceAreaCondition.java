package cn.com.hiveview.launcher.module.launcher.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by admin on 2017/8/2.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class EntranceAreaCondition implements Serializable {
    private Integer areaId;
    private String templeteName;
    private String citys;
    private String cityId;
    private Integer id;
    private Integer controllerType;
    private Integer DiffCityType;
    private String cityNames;
}
