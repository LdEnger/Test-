package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/7/20.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class CustomRecomTempleteListVo implements Serializable{
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


    private String layoutJson;
    private Integer layoutX;
    private Integer layoutY;
    private Integer layoutW;
    private Integer layoutH;
    private Integer col;
    private Integer row;
}
