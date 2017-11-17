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
public class TempletHotWordCondition extends BaseCondition implements Serializable {
    private Integer id;
    private String name; //热词名称
    private Integer type;//0自定义 1标签2商品包
    private Integer apkId;//apkid
    private String apkBagName;//APK包名
    private Integer apkChannelId;//频道id, 主键
    private Integer apkChannelType;
    private Integer templetId;
    private Integer seq;//顺序
    private Integer isEffective; //状态 0隐藏1禁用2启用
    private Integer comboxId;
    private Integer createTime;//创建时间
    private Integer updateTime;//修改时间
    private String apkName;
    private Integer chnId;
}