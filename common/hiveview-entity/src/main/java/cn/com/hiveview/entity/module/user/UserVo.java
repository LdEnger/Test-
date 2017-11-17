package cn.com.hiveview.entity.module.user;

import cn.com.hiveview.entity.module.sso.role.RoleVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xiaolong on 2017/7/18.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class UserVo implements Serializable {
    private Long userId;
    private String userName;
    private String email;
    private String passWord;
    private String realName;
    private Long telephone;
    private String remark;
    private String createTime;
    private String updateTime;
    private Integer checked;//1表示选中，0表示未选中
    private int status;
    private String lastLoginTime;
    private String ip;
    private Long permissionId;
    private Long systemId;
    private List<UserGroupRefVo> group;
    private List<UserPositionRefVo> position;
    private List<RoleVo> role;
}
