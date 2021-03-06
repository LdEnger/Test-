package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/8/2.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PortalBeanCurdMappingListVo implements Serializable {
    private Integer id;
    private  Integer entranceId;//应用Id
    private  String entranceName;//名称
    private Integer isEffective;//状态
    private Integer entranceType;//入口应用类型
    private Integer seq;//顺序
    private Integer beanCurdId;//豆腐块ID
    private String beanCurdName;//入口模版名称
    private String createTime;
    private String updateTime;
}
