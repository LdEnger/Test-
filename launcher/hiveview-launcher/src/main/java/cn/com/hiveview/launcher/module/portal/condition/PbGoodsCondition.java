package cn.com.hiveview.launcher.module.portal.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by admin on 2017/12/4.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PbGoodsCondition extends BaseCondition implements Serializable {
    private Integer id;// 自增Id

    private String goodsName;// 商品包名称

    private String contentName;// 节目集名称

    private Integer saleStyle;// 销售样式1单个内容2多个内容

    private Integer priceStyle;// 价格样式1按次售卖2按段售卖

    private String saleStartTime;// 销售开始时间

    private String saleEndTime;// 销售结束时间

    private Integer isEffective;// 0下线1上线2待上线3初始状态

    private Integer vipType; // 0:非VIP，1:大麦影视VIP，2:课堂VIP
}
