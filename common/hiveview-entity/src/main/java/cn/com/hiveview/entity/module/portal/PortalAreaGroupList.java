package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Administrator on 2017/7/14.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class PortalAreaGroupList {
    private Integer id;
    private String areaCode;
    private String areaName;
}