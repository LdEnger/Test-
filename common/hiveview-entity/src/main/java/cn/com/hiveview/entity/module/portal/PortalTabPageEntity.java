package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2017/10/10.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class PortalTabPageEntity implements Serializable {
    private Integer tabId;
    private String backgroud;
    private List<PortalGroupEntity> list;
    private Integer page;
    private Integer size;
}
