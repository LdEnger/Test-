package cn.com.hiveview.entity.module.sso.group;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by xiaolong on 2017/7/25.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class GroupVo {
    private Long orgId;
    private String orgName;
    private String orgLeader;
    private String remark;
    private String createTime;
    private String updateTime;
    private Long parentId;
    private String indentation;//变更组织的缩进效果

    public String getIndentation() {
        return indentation;
    }

    public void setIndentation(String indentation) {
        this.indentation = indentation;
    }
}
