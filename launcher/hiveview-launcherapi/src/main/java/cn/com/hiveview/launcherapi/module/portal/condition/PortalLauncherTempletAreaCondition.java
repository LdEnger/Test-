package cn.com.hiveview.launcherapi.module.portal.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/7/19.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PortalLauncherTempletAreaCondition implements Serializable{

    private Integer id;
    private Integer templetId;
    private String areaCode;//区域代码
    private Integer type;//控制类型1 ip 2 mac,sn 3 硬件型号 4 ROM版本 5 硬件型号+IP地址
}
