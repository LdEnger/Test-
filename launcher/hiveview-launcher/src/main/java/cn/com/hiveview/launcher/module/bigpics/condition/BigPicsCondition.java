package cn.com.hiveview.launcher.module.bigpics.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/7/13.
 */
@EqualsAndHashCode(callSuper =  false)
@Data
public class BigPicsCondition  extends BaseCondition implements Serializable{
    private Integer imgId;
    private  String imgName;
    private String imgAddr;
    private Integer tabBackground;
    private  String careateTime;
    private String updateTime;

}
