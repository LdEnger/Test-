package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/7/19.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PortalLauncherTempletVo implements Serializable {

    private Integer templetId;
    private Integer controllerType;
    private String templetName;
    private Integer isHide;
    private Integer logoId;
    private Integer autoId;
    private Integer blockId;
    private Integer bigImageId;
    private Integer smallImageId;
    private String cityNames;

}
