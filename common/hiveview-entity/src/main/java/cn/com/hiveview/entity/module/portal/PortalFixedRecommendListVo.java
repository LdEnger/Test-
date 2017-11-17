package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/7/14.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PortalFixedRecommendListVo implements Serializable {

    private Integer fixedRecomId;
    private String fixedRecomName;
    private Integer operateType;
    private Integer operateContent;
    private Integer appCategory;
    private Integer chnId;
    private Integer chnType;
    private String apkBagName;
    private Integer templetId;
    private String createTime;
    private String updateTime;

}
