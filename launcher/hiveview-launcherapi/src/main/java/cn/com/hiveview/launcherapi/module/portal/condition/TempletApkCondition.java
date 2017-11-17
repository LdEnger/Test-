package cn.com.hiveview.launcherapi.module.portal.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by admin on 2017/8/1.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class TempletApkCondition extends BaseCondition implements Serializable {
    private Integer id;
    private String apkBagName;
    private Integer templetId; // 模板Id
    private String apkName;
    private Integer isEffective;
    private String notAcceptCp;// 剔除cp
    private String relateChannel;
    private String relateChannelName;
    private Integer seq;
    private Integer createTime;//创建时间
    private Integer updateTime;//修改时间
}
