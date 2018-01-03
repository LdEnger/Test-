package cn.com.hiveview.entity.module.portal;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by admin on 2017/12/4.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PbsGoodsImage extends BaseCondition implements Serializable {
    private static final long serialVersionUID = -4941028587320888573L;

    private Integer goodsId;// 商品ID=pbs_goods.id

    private Integer type;// 图片大小(1-极清横图1920*1080,2-极清缩略图200*113,3-大麦商品横图1920*1080,4-大麦商品竖图530*730,5-大麦影视详情页图片1920*1080,6-大麦影视相关商品包图片1920*1080)

    private String imgUrl;// 图片地址
}
