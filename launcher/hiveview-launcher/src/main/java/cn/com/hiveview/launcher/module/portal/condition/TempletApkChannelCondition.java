package cn.com.hiveview.launcher.module.portal.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by admin on 2017/8/1.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class TempletApkChannelCondition implements Serializable {
    private Integer id; //频道名称
    private Integer apkId; //关联的apk 包名
    private String apkBagName;
    private String  cName; //频道名称
    private Integer cType; //0 专题 1 专辑 2 活动 3热词 4 直播 5抽奖活动 6轮播 7产品包 8标签 9个性化频道10 基础频道
    private Integer showCategory;//展示类型 0常规类型1特殊类型
    private Integer isMultichip;//0单片1多片
    private Integer isHasDetail; //0无详情1有详情
    private Integer isHorizontal; //0竖图1横图
    private Integer isSpecific;//特殊类型之1综艺类2专题类3商品包4标签类
    private Integer seq; //顺序
    private Integer isEffective;
    private Integer comboxId;
    private Integer parentCid;
    private Integer parentCtype;
    private String parentApkName;
    private String createTime;//创建时间
    private String updateTime;//修改时间
    private Integer templetId;
    private String  cTypeName;
    public int page ;
    public int rows;
}
