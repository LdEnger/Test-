package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by admin on 2017/8/2.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class AreaCodeList  implements Serializable {
    private String code;
    private String name;
    private int type;
    public AreaCodeList() {
    }
    public AreaCodeList(String code, String name, int type) {
        this.code = code;
        this.name = name;
        this.type = type;
    }
}
