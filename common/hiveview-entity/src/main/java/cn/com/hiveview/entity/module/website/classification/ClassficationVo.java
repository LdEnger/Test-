package cn.com.hiveview.entity.module.website.classification;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by user on 2017/9/19.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class ClassficationVo {
    private Long positionClassficationId;
    private String positionTypeName;
}
