package cn.com.hiveview.launcher.module.portal.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by admin on 2017/7/31.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class CloudAppSubjectCondition implements Serializable {
    private Integer subjectId;//专题id
    private String subjectName;//专题名称
    private String subjectPic;//专题图片
    private String subjectDesc;//专题描述
    private String subjectBgImg;//专题背景图片
    private String imgSize;//专辑图片大小
    private Integer subjectSize;//专题大小容量
    private Integer categoryId;//应用分类[1:游戏,2:应用]
    private Integer seq;
    private Integer isEffective;
    private Long createTime;
    private Long updateTime;
    public int page ;
    public int rows;
}
