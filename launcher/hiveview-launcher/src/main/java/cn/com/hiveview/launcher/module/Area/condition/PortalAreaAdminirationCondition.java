package cn.com.hiveview.launcher.module.Area.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/10/6.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PortalAreaAdminirationCondition extends BaseCondition implements Serializable  {
    private  Integer id;
    private String areaName;//专区名称
    private String areaTitle;//专区标题名称
    private Integer effective;//状态
    private String areaIntroduce;//专区介绍
    private String areaImg;//专区外显图
    private String createTime;//创建时间
    private String updateTime;//修改时间
}
