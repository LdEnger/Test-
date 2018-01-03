package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/12/8.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PortalAreaContentListEntity implements Serializable {
    private String contentName;//标题
    private String areaContent;//内容介绍
    private String recommendImg;//推荐位图片
    private Integer recommendType;//推荐位类型
    private String packageName;//包名
    private Integer channel;//频道
    private Integer contentId;
    private String version;
}
