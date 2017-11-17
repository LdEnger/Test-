package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
public class YouGouGoods implements Serializable {
    private static final long serialVersionUID = 8324763766553682988L;
    /**
     * 商城二级分类名称.
     */
    private String name;

    /**
     * 商品种类编号
     */
    private String goodsSn;
    /**
     * 商品图片
     */
    private String productImages;
}
