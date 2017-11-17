package cn.com.hiveview.launcherapi.module.portal.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
public class YouGouSpecialsCondition extends BaseCondition implements Serializable {
    private static final long serialVersionUID = -2095053487680817224L;
    private String name;
}
