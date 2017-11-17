package cn.com.hiveview.entity.module.system;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/7/31.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PermissionVo extends BaseCondition implements Serializable {
    private Long permissionId;//权限id
    private String permissionName;//权限名称
    private Integer importantGrade;//重要级别
    private String idCode;//功能识别码
    private String url;
    private String remark;//备注
    private String createTime;
    private String updateTime;
    private Long parentId;//父节点id
    private Integer permissionCode;//权限code
    private Integer orderNumber;//排序
    private String path;//权限路径
}
