package cn.com.hiveview.launcherapi.module.portal.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by admin on 2017/7/31.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class AppCategoryCondition extends BaseCondition implements Serializable {
    private Integer categoryId;
    private String categoryName;
    private Integer seq;
    private Integer state;
}
