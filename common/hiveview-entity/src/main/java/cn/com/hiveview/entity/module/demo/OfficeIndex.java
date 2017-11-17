package cn.com.hiveview.entity.module.demo;

import cn.com.hiveview.entity.module.common.BaseBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by chimeilong on 2017/6/27.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class OfficeIndex extends BaseBean implements Serializable {

    private Long id;
    /**
     * 推荐位title
     */
    private String indexTitle;
    /**
     * 推荐位类型
     */
    private Byte indexType;
    /**
     * 推荐位对象ID
     */
    private String indexObjectId;
}
