package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalSysAppIconsList;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalSysAppIconsListCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalVipLogoCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalVipLogoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xiach on 2017/10/6.
 */
@Service
public class PortalVipLogoService {
    @Autowired
    private PortalVipLogoDao portalVipLogoDao;

    /**
     *
     * @param condition
     * @return  查询一页数据
     * @throws Exception
     */
    public ScriptPage<PortalVipLogoCondition> getPageList(PortalVipLogoCondition condition) throws Exception {
        ScriptPage<PortalVipLogoCondition> scriptPage = new ScriptPage<PortalVipLogoCondition>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<PortalVipLogoCondition> rows = this.portalVipLogoDao.getPageList(condition);
        int total = this.portalVipLogoDao.getCount(condition);
        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        return scriptPage;
    }

    public Integer add(PortalVipLogoCondition condition){
        Integer count = portalVipLogoDao.getCount(condition);
        if(count!=0){//判断是否已存在logoId
            return -2;
        }
        Integer maxSeq = portalVipLogoDao.getMaxSeq();
        if(maxSeq==null){//当表中没有数据从一开始排序
            condition.setSeq(1);
        }else {
            condition.setSeq(maxSeq+1);
        }
        return portalVipLogoDao.add(condition);
    }

    public Integer update(PortalVipLogoCondition condition){
        return portalVipLogoDao.update(condition);
    }

    public Integer delete(PortalVipLogoCondition condition){
        return portalVipLogoDao.delete(condition);
    }

    public Integer getMaxSeq(){return portalVipLogoDao.getMaxSeq();}

    public Integer getMinSeq(){return portalVipLogoDao.getMinSeq();}

    public PortalVipLogoCondition getMaxContent(PortalVipLogoCondition condition){return portalVipLogoDao.getMaxContent(condition);}

    public PortalVipLogoCondition getMinContent(PortalVipLogoCondition condition){return portalVipLogoDao.getMinContent(condition);}

}
