package cn.com.hiveview.entity.module.sso.role;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/8/7.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class RolePermissionVo implements Serializable {
    private Long roleId;//角色id
    private Long permissionId;// 权限id
}
