package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/7/26.
 */
@EqualsAndHashCode(callSuper =  false)
@Data
public class PortalBeanCurdImgListVo implements Serializable{
    private  Integer imgId;//图片ID
    private Integer entranceId;
    private  String entranceTitle;//标题
    private String entranceSubtitle;//副标题
    private Integer seq;//顺序
    private  Integer isEffective;//状态
    private  String entranceImg;//图片
}
