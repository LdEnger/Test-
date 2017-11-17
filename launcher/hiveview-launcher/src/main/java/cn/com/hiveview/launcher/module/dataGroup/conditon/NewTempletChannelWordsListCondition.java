package cn.com.hiveview.launcher.module.dataGroup.conditon;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/10/10.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NewTempletChannelWordsListCondition extends BaseCondition implements Serializable {
    private Integer id;
    private Integer type;//热词类型
    private Integer apkId;//模版apkId
    private Integer templetId;//模版Id
    private Integer apkChannelId;//模版APK频道id
    private Integer apkChannelType;//频道类型
    private String name;//热词名称
    private Integer seq;//顺序
    private Integer isEffective;//0禁用,1隐藏,2启用
    private  Integer comboxId;//标识Id
    private String createTime;
    private String updateTime;
    private String apkBagName;//APK报名
    private Integer hotId;
}
