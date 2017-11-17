package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalSpeGroupCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalTabGroupCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalSpeGroupDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xiach on 2017/10/10.
 */
@Service
public class PortalSpeGroupService {
    @Autowired
    private PortalSpeGroupDao portalSpeGroupDao;
    /**
     *
     * @param condition
     * @return  查询一页数据
     * @throws Exception
     */
    public ScriptPage<PortalSpeGroupCondition> getPageList(PortalSpeGroupCondition condition) throws Exception {
        ScriptPage<PortalSpeGroupCondition> scriptPage = new ScriptPage<PortalSpeGroupCondition>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<PortalSpeGroupCondition> rows = this.portalSpeGroupDao.getPageList(condition);
        int total = this.portalSpeGroupDao.getCount(condition);
        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        return scriptPage;
    }
}
