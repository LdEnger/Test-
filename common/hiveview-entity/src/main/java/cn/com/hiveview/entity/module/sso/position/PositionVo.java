package cn.com.hiveview.entity.module.sso.position;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by xiaolong on 2017/7/18.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PositionVo implements Serializable {
    private Long positionId;   //主键
    private String positionName;//职位名称
    private String positionLevel; //职位级别
    private String remark;  //备注
    private String createTime;
    private String updateTime;
    private Long permissionId;
    private Long systemId;
    private Integer checked;//1表示选中，0表示未选中

}
