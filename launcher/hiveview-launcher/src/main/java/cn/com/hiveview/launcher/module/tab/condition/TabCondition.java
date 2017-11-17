package cn.com.hiveview.launcher.module.tab.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xiach on 2017/10/9.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class TabCondition extends BaseCondition implements Serializable{

    private Integer id;
    private String tabName; //tab名称
    private String tabTitle; //tab标题名称
    private Integer seqIsTop; //置顶顺序
    private Integer seq; //排序
    private Integer isEffective; //有效状态
    private String tip ;//备用
    private String tabBackPic; //tab背景图
    private String tabIcon; //tab图标
    private Date createTime; //创建时间
    private Date updateTime; //更新时间
}
