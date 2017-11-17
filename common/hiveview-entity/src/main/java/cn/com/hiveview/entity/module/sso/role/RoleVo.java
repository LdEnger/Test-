package cn.com.hiveview.entity.module.sso.role;

import cn.com.hiveview.entity.module.user.UserVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xiaolong on 2017/7/18.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class RoleVo implements Serializable {
    private Long roleId;//自增角色主键
    private String roleName;
    private String remark;
    private String createTime;
    private String updateTime;
    private Long systemId;
    private Integer orderNumber;
    private Integer checked;//1表示选中，0表示未选中
    private String id;//节点id
    private String pId;//父节点pId I必须大写
    private String name;//节点名称
    private String open = "false";//是否展开树节点，默认不展开
    private Long permissionId;
    private List<UserVo> userVoList;

}
