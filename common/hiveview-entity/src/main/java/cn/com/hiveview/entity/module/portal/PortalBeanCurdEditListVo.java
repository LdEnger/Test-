package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/7/24.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PortalBeanCurdEditListVo implements Serializable{
    private Integer entranceId;
    private String entranceName;//入口名称
    private Integer isEffeCtice;//状态
    private Integer entranceType;//入口应用类型
    private Integer seq;//顺序
    private Integer entranceTempleteId;//入口模版ID
    private Integer customizeAppId;//定制APKID
    private String custommizeAppName;//定制应用名称
    private Integer entranceAppId;//系统APK ID
    private String entranceAppName;//应用名称
    private String entranceAppStr;//关联字符串
    private String entranceAppVersion;//最新版本
    private String entranceAppInstall;//安装方式
    private String entranceAppUrl;//定制APKurl
    private  Integer curdID;//豆腐块ID
    private String curdName;//豆腐块名称
    private  String createTime;//创建时间
    private  String updateTime;//修改时间
    private  String enName;//别名
    private Integer id;
}
