package cn.com.hiveview.launcher.module.dataGroup.conditon;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/10/12.
 */
@Data
@EqualsAndHashCode(callSuper =  false)
public class NewTempletAkpChannelCondition extends BaseCondition implements Serializable {
    private Integer id;//频道ID
    private String apkBagName;//模版ID
    private Integer cType;//频道类型
    private String cName;//频道名称
}
