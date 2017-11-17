package cn.com.hiveview.launcher.module.launcher.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/27.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class TempletContentCondition implements Serializable {
    private Integer id;
    private Integer templetId;
    private Integer recommendId;
    private String recomName;
    private Integer operType;
    private Integer recommendType;
    private Integer seq;
    private Date createTime;
    public int page;
    public int rows;
}
