package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by admin on 2017/7/31.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class CloudAppSubjectList implements Serializable {
    private Integer pubId;
    private String pubName;
    private String pubImg;
    private Integer categoryId;
    private String descs;
    private String launcherId;
    private Integer cid;
    private String imgSize;
    private String subjectBgImg;
    private Integer videosetId;
    private Integer isEffective;
    private Integer epOrder;
    private Integer total;
    private Integer seriesType;
    private Integer currCount;
    private Integer subjectSize;
    private String appVideo;
}
