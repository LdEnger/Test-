package cn.com.hiveview.launcherapi.module.portal.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

/**
 * Created by admin on 2017/8/2.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class OnlineGoodsCondition  extends BaseCondition implements Serializable {
    private Integer id; // 商品包Id
    private String name;// 商品包名称
    private String saleStartTime;//销售开始时间
    private String saleEndTime;// 销售截止时间
    private Integer saleStyle;//销售样式1单一产品包2集合产品包
    private Integer priceStyle;//价格样式1单片2包月
    private Integer isBooking;//是否预售0否1是
    private String bookingStartTime;//预售起始时间
    private String bookingEndTime;//预售截止时间
    private String desc;//描述
    private Integer templetId;//模板Id
    private String templetName;
    private String firstPosterPic;//商品海报图1---极清首映背景大海报（预告片处）海报尺寸1920*1080
    private String secondPosterPic;//商品海报图2---极清首映节目集列表页海报尺寸200*113
    private Integer seq; //顺序
    private Integer isEffective;//0下线1上线2待上线
    private String apkName;
    private String onlineTime; //上线时间
    private String price;   //价格
    private Integer days;    //天数
    private String partnerId;
    private String partnerUrl;
    private String damaiPosterPic1; //大麦海报图
    private String damaiPosterPic2; //大麦海报图
    private String damaiPosterPic3; //大麦海报图
    private String label;//备注
    private Integer productId;//产品包Id
    private String endTime;//商品包截止时间
    private Integer goodsVersion;//商品包适用版本（0:大麦极清，1:鹏云教育）
}
