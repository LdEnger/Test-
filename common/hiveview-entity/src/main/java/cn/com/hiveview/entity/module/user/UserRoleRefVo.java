package cn.com.hiveview.entity.module.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/8/7.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class UserRoleRefVo implements Serializable {
    private Long userId;//用户id
    private Long roleId;// 角色id
}
