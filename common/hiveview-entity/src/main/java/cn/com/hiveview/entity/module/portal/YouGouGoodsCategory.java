package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
public class YouGouGoodsCategory implements Serializable {
    private static final long serialVersionUID = 2037196107956831693L;
    /**
     * 商城二级分类名称.
     */
    private String name;

    /**
     * 商品种类编号
     */
    private String categorySn;
}
