package cn.com.hiveview.launcherapi.module.portal.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/8/2.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class CustomRecomLayoutCondition extends BaseCondition implements Serializable{

    private Integer layoutId;
    private Integer layoutX;
    private Integer layoutY;
    private Integer layoutW;
    private Integer layoutH;
    private Integer templeteId;
    private Integer positionSeq;
    private Integer layoutTeam;
    private Integer layoutTeamType;
    private Integer col;
    private Integer row;
}
