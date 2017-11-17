package cn.com.hiveview.launcher.module.customRecomTemplete.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/7/20.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class CustomRecomTempleteCondition extends BaseCondition implements Serializable{
    private Integer templeteId;
    private String templeteName;
    private Integer isEffective;
    private String updateTime;
    private String updateUser;
    private String contentName;
    private Integer templeteLeve;
    private Integer fatherId;
    private Integer layoutCount;
    private String  templeteTitle;
    private String  backPic;
    private Integer col;
    private Integer row;

    private String layoutJson;
    private Integer layoutX;
    private Integer layoutY;
    private Integer layoutW;
    private Integer layoutH;
}
