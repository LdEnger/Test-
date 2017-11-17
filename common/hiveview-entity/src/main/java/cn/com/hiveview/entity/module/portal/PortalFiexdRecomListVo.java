package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/7/14.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PortalFiexdRecomListVo implements Serializable{
    private  Integer recomId;//推荐位ID
    private  String recomName;//推荐位名称
    private  Integer operateType;//运行类型
    private  Integer operateContent;//运营内容
    private  Integer appCategory;//应用类型
    private  Integer chnId;//频道ID
    private  Integer chnType;//频道类型
    private  String apkBagName;//APK包名
    private  Integer templetId;//模板ID
    private  String createTime;//创建时间
    private  String updateTime;//修改时间
    private  Integer isEffective;//是否有效
}
