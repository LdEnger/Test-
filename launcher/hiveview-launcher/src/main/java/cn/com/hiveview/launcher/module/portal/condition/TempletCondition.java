package cn.com.hiveview.launcher.module.portal.condition;

/**
 * Created by admin on 2017/8/1.
 */

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
@EqualsAndHashCode(callSuper = false)
@Data
public class TempletCondition implements Serializable {
    private Integer id; // id
    private String name; // 名称
    private Integer isEffective;// 状态 0隐藏1禁用2启用
    private String createTime;// 创建时间
    private String updateTime;// 修改时间
    private Integer seq;// 顺序
    public int page ;
    public int rows;
}
