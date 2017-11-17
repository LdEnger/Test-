package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by admin on 2017/8/1.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class TempletHotWordList implements Serializable {
    private Integer id;
    private String name; //热词名称
    private Integer type;//0自定义 1标签2商品包
    private Integer apkId;//apkid
    private String apkBagName;//APK包名
    private Integer apkChannelId;//频道id, 主键
    private Integer apkChannelType;
    private Integer templetId;
    private Integer seq;//顺序
    private Integer comboxId;
    private Integer isEffective; //状态 0隐藏1禁用2启用
    private String createTime;//创建时间
    private String updateTime;//修改时间
}
