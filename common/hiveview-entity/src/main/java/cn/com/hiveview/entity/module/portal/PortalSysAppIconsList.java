package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by admin on 2017/7/10.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class PortalSysAppIconsList implements Serializable {
    private Integer id;
    private String appName;
    private String appPackage;
    private Integer isShow;

}
