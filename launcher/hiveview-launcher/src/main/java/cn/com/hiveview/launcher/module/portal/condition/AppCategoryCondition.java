package cn.com.hiveview.launcher.module.portal.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by admin on 2017/7/31.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class AppCategoryCondition  implements Serializable {
    private Integer categoryId;
    private String categoryName;
    private Integer seq;
    private Integer state;
}
