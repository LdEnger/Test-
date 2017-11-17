package cn.com.hiveview.entity.module.common;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by chimeilong on 2017/6/27.
 */
@Data
public class BaseBean implements Serializable {
    /**
     * 是否删除; 0-未删除;1-已删除
     */
    private Integer isDel;

    /**
     * 创建用户
     */
    private Long createUser;
    private String createUsername;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 更新用户
     */
    private Long modifyUser;
    private String modifyUsername;

    /**
     * 更新时间
     */
    private Timestamp modifyTime;
}
