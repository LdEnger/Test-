package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/7/21.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PortalRecommendListVo implements Serializable{

    private Integer id;
    private Integer templetId;
    private Integer recommendId;
    private String recommendName;
    private Integer recommendType;
    private Integer seq;
    private String createTime;
}
