package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by admin on 2017/8/2.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class EntranceAreaList implements Serializable {
    private Integer areaId;
    private String templeteName;
    private String cityId;
    private String citys;
    private Integer id ;
    private Integer controllerType;
}
