package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/7/5.
 */

@EqualsAndHashCode(callSuper = false)
@Data
public class LogoLicenseManageListVo implements Serializable{

    private Integer logoId;
    private String logoName;
    private String logoImg;
    private Integer isOnline;
    private String createTime;
    private String updateTime;

}
