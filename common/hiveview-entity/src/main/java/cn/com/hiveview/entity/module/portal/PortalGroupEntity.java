package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by admin on 2017/10/10.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class PortalGroupEntity {
        private Integer groupId;
        private Integer groupType;
        private Integer isShow;
        private String groupName;
        private String groupTitle;
        private Integer contentType;//内容类型 0频道推荐 1 热词内容列表 2频道内容列表
        private String apkBagName;//包名
        private Integer chnId;//频道Id
        private Integer chnType;//频道类型
        private Integer hotId;//热词Id
        private Integer templetId;//模板Id
        private Integer col;
        private Integer row;
        private Integer width;
        private Integer height;
        private Integer upAndDown;
        private String groupBackground;//group背景图片
        private String version;
        private List<PortalRecommendEntity> list;
}
