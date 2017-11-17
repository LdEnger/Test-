package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
public class YouGouSpecials implements Serializable {
    private static final long serialVersionUID = 4317415227213615916L;
    /**
     * 专题名称.
     */
    private String name;

    /**
     * 专题编码.
     */
    private String specSn;

    /**
     * 专题图片.
     */
    private String imgUrl;
}
