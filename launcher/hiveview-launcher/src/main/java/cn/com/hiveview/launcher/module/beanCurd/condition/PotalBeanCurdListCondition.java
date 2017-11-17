package cn.com.hiveview.launcher.module.beanCurd.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/7/24.
 */
@EqualsAndHashCode(callSuper =  false)
@Data
public class PotalBeanCurdListCondition  extends BaseCondition implements Serializable{
    private  Integer curdID;//豆腐块ID
    private String curdName;//豆腐块名称
    private  String createTime;//创建时间
    private  String updateTime;//修改时间
    private  Integer isEffeCtice;//状态

}
