package cn.com.hiveview.entity.module.portal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 2017/7/28.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PortalCustomRecomContentApiVo implements Serializable{

    private Integer layoutTeam;
    private Integer layoutTeamType;
    private List<PortalCustomRecomContentVo> layoutList;
}
