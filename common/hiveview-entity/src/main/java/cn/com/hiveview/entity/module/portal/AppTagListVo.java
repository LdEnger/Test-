package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by user on 2017/10/17.
 */
@Data
@EqualsAndHashCode(callSuper =  false)
public class AppTagListVo implements Serializable{
    private Integer tagId;
    private Integer categoryId;
    private String tagName;
    private Integer tagCount;
}
