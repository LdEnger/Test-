package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 2017/12/8.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PortalAreaAdministrationListEntity implements Serializable{
    private  Integer areaId;
    private String areaName;//专区名称
    private String areaTitle;//专区标题名称
    private String areaIntroduce;//专区介绍
    private String backgroundImg;//专区外显图
    private List<PortalAreaContentListEntity> list;
    private String areaTemplate;//专区模板
    private Integer albumPicType;//推荐位样式
    private Integer page;
    private Integer size;
    private String version;
}
