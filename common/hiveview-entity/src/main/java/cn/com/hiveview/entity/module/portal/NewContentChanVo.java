package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by admin on 2017/8/8.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class NewContentChanVo implements Serializable {
    public Integer chnId;
    public Integer programsetId;
    public String albumName;
}
