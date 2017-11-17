package cn.com.hiveview.launcher.module.portal.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by admin on 2017/10/15.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class YouGouGoodsCondition  extends BaseCondition implements Serializable {
    private static final long serialVersionUID = 8084901913995983088L;
    /**
     * 商品种类编号
     */
    private String categorySn;
    /**
     * 商品名(可选)
     */
    private String goodsName;
}
