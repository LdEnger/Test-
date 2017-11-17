package cn.com.hiveview.entity.module.website.product;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by user on 2017/9/19.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class ProductVo {
    private Long productId; //商品id
    private String productName ;   //产品名
    private String shortImgAdress;   //外部展示缩略图
    private String longImgAdress;   //外部展示长图
    private String detialImgAdress;   //详情页顶部大图
    private String productNorm;   //产品规格参数
    private String productIntroduce;   //产品介绍
    private String productDetail;   //产品详情介绍
    private String productDetailAddress;   //商品购买地址
    private int status;  //显示状态   0显示，-1隐藏，默认0
    private int sort;  //排序，若排序相同，后创建优先
    private String createTime;  //创建时间
    private String updateTime;  //修改时间
    private int deleteStatus;  //删除状态 0为删除，-1为不删除，默认为0
    private int productClass; //产品分类,1是硬件，2是软件
}
