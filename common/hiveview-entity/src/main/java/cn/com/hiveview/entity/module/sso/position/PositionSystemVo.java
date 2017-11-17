package cn.com.hiveview.entity.module.sso.position;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by xiaolong on 2017/8/3.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PositionSystemVo {
    private Long positionId;
    private Long systemId;

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public Long getSystemId() {
        return systemId;
    }

    public void setSystemId(Long systemId) {
        this.systemId = systemId;
    }
}
