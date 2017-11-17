package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/7/11.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PortalScreenBigPicsListVo implements Serializable{

    private Integer imgId;
    private String imgName;
    private String imgAddr;
    private Integer tabBackground;
    private Integer isOnline;
    private String createTime;
    private String updateTime;
}
