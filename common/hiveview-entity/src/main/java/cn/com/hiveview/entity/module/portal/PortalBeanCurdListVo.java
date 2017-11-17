package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.omg.PortableInterceptor.INACTIVE;

import java.io.Serializable;

/**
 * Created by user on 2017/7/24.
 */
@EqualsAndHashCode (callSuper = false)
@Data
public class PortalBeanCurdListVo implements Serializable{
    private  Integer curdID;//豆腐块ID
    private String curdName;//豆腐块名称
    private  String createTime;//创建时间
    private  String updateTime;//修改时间
    private Integer isEffeCtice;//状态
}
