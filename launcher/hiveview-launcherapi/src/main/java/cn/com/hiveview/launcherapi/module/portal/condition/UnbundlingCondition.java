package cn.com.hiveview.launcherapi.module.portal.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by admin on 2017/8/17.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class UnbundlingCondition extends BaseCondition implements Serializable {
    private Integer id;
    private Integer contentType;//内容类型
    private Integer contentId;//内容id
    private String apkBagName;//包名
    private String backup;//备用字段
    private Integer isEffective;//有效状态
    private Integer groupId;//所属groupId
}
