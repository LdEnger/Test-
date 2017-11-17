package cn.com.hiveview.launcher.module.launcher.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/27.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class TempletCondition implements Serializable {
    private Integer id;
    private Integer type;
    private String templetName;
    private Integer isHide;
    private Integer logoId;
    private Integer autoId;
    private Integer blockId;
    private Integer bigImageId;
    private Integer smallImageId;
    private String cityNames;
    public int page;
    public int rows;

}
