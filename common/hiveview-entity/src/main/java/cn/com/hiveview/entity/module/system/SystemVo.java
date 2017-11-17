package cn.com.hiveview.entity.module.system;

import cn.com.hiveview.entity.module.sso.role.RoleVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.security.PermissionCollection;
import java.util.List;

/**
 * Created by user on 2017/7/26.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class SystemVo {
    private Long systemId;
    private String systemName;
    private String appId;
    private String appSecret;
    private String createTime;
    private String updateTime;
    private Integer orderNumber;
    private String systemAddress;
    private List<PermissionVo>permissionConditions;
    private List<RoleVo> roleVoList;
}
