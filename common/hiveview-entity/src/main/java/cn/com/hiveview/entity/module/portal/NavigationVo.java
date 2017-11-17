package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by admin on 2017/8/2.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class NavigationVo implements Serializable {
    private Integer id; // 导航栏id
    private String title;// 名称
    private String titleBackgroud;//名称背景
    private String navBackgroud;//  导航栏背景

}
