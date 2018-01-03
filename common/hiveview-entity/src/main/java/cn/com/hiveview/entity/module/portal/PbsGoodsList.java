package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by admin on 2017/12/4.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class PbsGoodsList implements Serializable {
    private static final long serialVersionUID = -7255358990227041398L;
    private Integer id;
    private String goodsCode;// 商品包编号,自定义唯一Id

    private String goodsName;// 商品包名称

    private Integer saleStyle;// 销售样式1单个内容2多个内容

    private Integer priceStyle;// 价格样式1按次售卖2按段售卖

    private Integer isBooking;// 是否预售0否1是

    private Integer vipType; // 0:非VIP，1:大麦影视VIP，2:课堂VIP

    private Integer templetId;// 模板id,展示的位置

    private String templetName;// 模板名称

    private Integer seq;// 顺序

    private Integer isEffective;// 0下线1上线2待上线3初始状态

    private Date onlineTime;// 上线时间

    private Date offlineTime;// 下线时间

    private String label;// 备注

    private String features;// 商品包看点

    private String goodsDesc;// 详情

    private Integer goodsVersion;// 商品包适用版本（0:大麦极清，1:鹏云教育）

    private String imgUrl;//商品包频道列表页760*950

}
