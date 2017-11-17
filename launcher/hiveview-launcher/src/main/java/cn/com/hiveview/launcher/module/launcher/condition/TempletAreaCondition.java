package cn.com.hiveview.launcher.module.launcher.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/27.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class TempletAreaCondition implements Serializable {
    private Integer id;
    private Integer templetId;
    private String areaCode;
    private Integer type;
    public int page;
    public int rows;
}
