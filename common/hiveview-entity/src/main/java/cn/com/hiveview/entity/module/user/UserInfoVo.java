package cn.com.hiveview.entity.module.user;

import cn.com.hiveview.entity.module.sso.group.GroupVo;
import cn.com.hiveview.entity.module.sso.position.PositionVo;
import cn.com.hiveview.entity.module.sso.role.RoleVo;
import cn.com.hiveview.entity.module.system.PermissionVo;
import cn.com.hiveview.entity.module.system.SystemVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by user on 2017/8/22.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class UserInfoVo {
    private Long  userId;
    private String  userName;
    private String  email;
    private String  passWord;
    private String  realName;
    private Long  telephone;
    private int  status;
    private String  remark;
    private List<GroupVo> groupVoList;
    private List<PositionVo> positionVoList;
    private List<RoleVo> roleVoList;
}
