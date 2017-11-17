package cn.com.hiveview.entity.module.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/8/7.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class UserPositionRefVo implements Serializable {
    private Long userId;//用户id
    private Long positionId;// 职位id
    private String positionName;//职位名称
    private int checked;//是否选中，1选中，0未选中
}
