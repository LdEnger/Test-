package cn.com.hiveview.entity.module.system;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by user on 2017/7/31.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class SystemPermissionVo {
    private Long id;
    private String name;
    private Long pId;
}
