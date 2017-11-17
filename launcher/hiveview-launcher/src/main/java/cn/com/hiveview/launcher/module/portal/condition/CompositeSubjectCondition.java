package cn.com.hiveview.launcher.module.portal.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 影视专题
 * Created by admin on 2017/7/31.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class CompositeSubjectCondition implements Serializable {
    // 专题id
    private Integer subjectId;
    // 专题名称
    private String subjectName;
    // 专题图片(横图)
    private String subjectHorPic;
    // 专题图片（竖图）
    private String subjectVerPic;
    // 专题描述
    private String subjectDesc;
    // 专题背景图片(横图)
    private String subjectHorBgImg;
    //专题背景图片（竖图）
    private String subjectVerBgImg;
    // 专辑图片大小
    private String imgSize;
    // 顺序
    private Integer seq;
    // 是否上线 0下线 1上线
    private Integer isOnline;
    //创建时间
    private Long createTime;
    //修改时间
    private Long updateTime;
    // 专题大小
    private Integer subjectSize;
    //热词关联专题（热词ID）
    private Integer wordId;
    private Integer cornerId;		//角标关联专辑（角标id）
    private String apkName;			//模板下apk名称
    private Integer templateId;		//模板id
    private String apkBagName;		//模板下apk包名
    public int page ;
    public int rows;
}
