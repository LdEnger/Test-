package cn.com.hiveview.launcherapi.module.portal.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xiach on 2017/10/9.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PortalTabGroupCondition extends BaseCondition implements Serializable {

    private Integer id;
    private Integer belongTabId;  //对应的tabId
    private Integer belongGroupId; //对应的groupId
    private Integer groupType; //group类
    private Integer contentType;//内容类型  1频道推荐 2 热词内容列表 3频道内容列表
    private String apkBagName;//包名
    private Integer chnId;//频道id
    private Integer chnType;//频道类型
    private Integer hotId;//热词Id
    private Integer isShow; //是否显示
    private String groupName; //group名称
    private String groupTitle; //Group标题
    private Integer seqIsTop; //置顶
    private Integer seq; //排序
    private Integer isEffective; //状态
    private Integer col; //列
    private Integer row; //行
    private Integer width; //宽度
    private Integer height; //高度
    private Date createTime; //创建时间
    private Date updateTime; //更新时间
    private Integer upAndDown; //group纵横
    private String groupBackground; //group背景图
    private String version;//版本号
}
