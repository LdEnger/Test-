package cn.com.hiveview.launcher.module.fixedRecom.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/7/13.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class FixedRecomListCondition extends BaseCondition implements Serializable{
    private  Integer recomId;//固定推荐位ID
    private  String recomName;//固定推荐位名称
    private  Integer operateType;//运营类型
    private  Integer category;//应用类型
    private  Integer chnId;//频道ID
    private  Integer chnType;//频道类型
    private  String apkbagName;//APK包名
    private  Integer templetId;//模版ID
    private  String  createTime;
    private  String updateTime;
    private  Integer isEffective;
    private  Integer operateContent;//运营内容
    private  Integer appCategory;//应用类型
}
