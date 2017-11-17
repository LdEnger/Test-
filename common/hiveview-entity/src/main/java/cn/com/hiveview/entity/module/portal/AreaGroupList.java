package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by admin on 2017/8/2.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class AreaGroupList  implements Serializable {
    private Integer id; //主键ID
    private String areaCode;//所属城市Id
    private String areaName; //所属城市名字
}
