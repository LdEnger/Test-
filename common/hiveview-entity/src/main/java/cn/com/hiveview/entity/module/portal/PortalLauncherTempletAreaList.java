package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/27.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class PortalLauncherTempletAreaList implements Serializable {
    private Integer id;
    private Integer templetId;
    private String areaCode;
    private Integer type;
}
