package cn.com.hiveview.entity.module.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by user on 2017/8/11.
 *
 * @author duan
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class UserGroupRefVo {
    private Long userId; //用户Id
    private Long groupId; //机构Id
    private String groupName; //机构名称
    private int checked; //是否选中 1选中，0未选中
}
