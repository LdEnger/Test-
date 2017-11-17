package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by user on 2017/7/25.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class LauncherHomeApiVo {
    private Integer entranceId;//入口Id
    private String entranceName; //入口名称
    private Integer seq; //顺序
    private Integer entranceType;//入口类别 0系统 1定制
    private String entranceAppAction; //入口应用
    private String entranceUrl; //定制应用url
    private Integer installStyle; //定制应用升级方式 0 询问  1 静默
    private String entranceAppName; //定制应用包名；
    private String entranceAppVersion; //定制应用版本
    private Integer controllerType;//控制类型
    private Integer areaId;
    private List<EntranceAppContentApiVo> contentList;

}
