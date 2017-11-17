package cn.com.hiveview.launcher.module.vipLogo.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by xiach on 2017/10/6.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class VipLogoCondition extends BaseCondition implements Serializable {
    private Integer id;
    private Integer seq;
    private String logoId;
    private String vipName;
    private String vipLogo;
    private Integer effective;
}
