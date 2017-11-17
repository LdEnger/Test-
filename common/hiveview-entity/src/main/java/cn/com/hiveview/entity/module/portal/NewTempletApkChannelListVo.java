package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/10/12.
 */
@Data
@EqualsAndHashCode(callSuper =  false)
public class NewTempletApkChannelListVo implements Serializable {
    private Integer id;//频道ID
    private String apkBagName;//模版ID
    private Integer cType;//频道类型
    private String cName;//频道名称
}
