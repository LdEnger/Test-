package cn.com.hiveview.launcherapi.module.portal.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/10/6.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PortalAreaContentListCondition extends BaseCondition implements Serializable {
    private Integer id;
    private String areaName;//标题
    private Integer seq;//顺序
    private Integer areaId;//专区管理ID
    private String areaContent;//内容介绍
    private String recommendImg;//推荐位图片
    private String backgroundImg;//背景图片
    private Integer areaType;//跳转状态
    private Integer recommendType;//推荐位类型
    private String packageName;//包名
    private String areaTemplate;//模版
    private Integer channelType;//频道类型
    private Integer channel;//频道
    private Integer payState;//付费(0全部,1免费,2大麦付费,3爱奇异付费)
    private Integer vipState;//vip(0全部,1非VIP,2大麦VIP,3爱奇异VIP)
}
