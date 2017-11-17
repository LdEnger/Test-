package cn.com.hiveview.launcherapi.module.portal.condition;

import cn.com.hiveview.entity.module.common.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/10/9.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PortalDataGroupListCondition extends BaseCondition implements Serializable {
    private  Integer id;
    private String dataGroupName;//DataGroup名称
    private String dataGroupTitle;//DataGroup标题名称
    private Integer row;//行
    private Integer col;//列
    private Integer contentType;//内容类型(0频道推荐,1热词内容列表,2相关推荐,3专区推荐,4Group推荐)
    private Integer effective;//状态(0下线,1上线)
    private Integer packageName;//包名(0应用游戏,1大麦影视)
    private Integer channel;//频道(0电视剧,1电影,2儿童,3动漫,4综艺,5游戏,6应用,7教育)
    private Integer width;//宽度
    private Integer height;//高度
    private String apkPackageName;//APK包名
    private Integer categoryId;//APKid
    private Integer hotId;//热词ID
    private Integer channelType;//频道类型 10基础频道 9自定义频道
    private String hotName;
    private Integer upAndDown;
}
