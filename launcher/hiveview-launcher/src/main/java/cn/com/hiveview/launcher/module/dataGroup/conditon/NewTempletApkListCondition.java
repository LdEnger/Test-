package cn.com.hiveview.launcher.module.dataGroup.conditon;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/10/11.
 */
@Data
@EqualsAndHashCode(callSuper =  false)
public class NewTempletApkListCondition extends BaseCondition implements Serializable {
    private  Integer id;
    private String apkBagName;//apk包名
    private String apkName;//apk名称
    private Integer templetId;//模版Id
    private Integer isEffective;//状态
    private String createTime;
    private String updateTime;
    private String notAcceptCp;//不包含CP
    private String relateChannel;//相关频道id
    private String relateChannleName;//相关频道名称
    private Integer seq;//顺序

    private Integer channelId;//频道ID
    private String apkBagChannelName;//模版apk包名
    private Integer cType;//频道类型
    private String cName;//频道名称

}
