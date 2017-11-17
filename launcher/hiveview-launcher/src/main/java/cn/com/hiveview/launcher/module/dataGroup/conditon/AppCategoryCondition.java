package cn.com.hiveview.launcher.module.dataGroup.conditon;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/10/17.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AppCategoryCondition extends BaseCondition implements Serializable{
    private Integer categoryId;
    private String categoryName;
    private Integer seq;
    private Integer state;
}
