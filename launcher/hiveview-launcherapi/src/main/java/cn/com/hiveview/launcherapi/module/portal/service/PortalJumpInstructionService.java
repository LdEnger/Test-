package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalJumpInstructionVo;
import cn.com.hiveview.launcherapi.module.portal.condition.*;
import cn.com.hiveview.launcherapi.module.portal.dao.CustomRecomBackupsContentDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalCustomRecomContentDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalJumpInstructionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xiach on 2017/10/8.
 */
@Service
public class PortalJumpInstructionService {

    @Autowired
    private PortalJumpInstructionDao portalJumpInstructionDao;
    @Autowired
    private PortalCustomRecomContentDao portalCustomRecomContentDao;
    @Autowired
    private CustomRecomBackupsContentDao customRecomBackupsContentDao;
    @Autowired
    private PortalNotStartInstructionService portalNotStartInstructionService;

    public ScriptPage<PortalJumpInstructionCondition> getPageList(PortalJumpInstructionCondition condition){
        ScriptPage<PortalJumpInstructionCondition> scriptPage = new ScriptPage<PortalJumpInstructionCondition>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<PortalJumpInstructionCondition> rows = this.portalJumpInstructionDao.getPageList(condition);
        int total = this.portalJumpInstructionDao.getCount(condition);
        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        return scriptPage;
    }

    public Integer add(PortalJumpInstructionCondition condition){
        return portalJumpInstructionDao.add(condition);
    }

    public Integer delete(PortalJumpInstructionCondition condition){
        //关联校验
        PortalCustomRecomContentCondition portalCustomRecomContentCondition = new PortalCustomRecomContentCondition();
        portalCustomRecomContentCondition.setContentType(condition.getId());
        Integer pcrcCount = portalCustomRecomContentDao.getCount(portalCustomRecomContentCondition);
        if(pcrcCount!=0){//已关联
            return -2;
        }
        CustomRecomBackupsContentCondition customRecomBackupsContentCondition = new CustomRecomBackupsContentCondition();
        customRecomBackupsContentCondition.setContentType(condition.getId());
        Integer crbcCount = customRecomBackupsContentDao.getCount(customRecomBackupsContentCondition);
        if(crbcCount!=0){//备份有关联
            return -3;
        }
        return portalJumpInstructionDao.delete(condition);
    }

    public Integer update(PortalJumpInstructionCondition condition) throws Exception{
        Integer result = portalJumpInstructionDao.update(condition);
        PortalNotStartInstructionListConditon updateBean =new PortalNotStartInstructionListConditon();
        updateBean.setStartApk(condition.getStartApk());
        portalNotStartInstructionService.updateNotice(updateBean);
        return result;
    }

    public PortalJumpInstructionCondition getOne(PortalJumpInstructionCondition condition){

        return portalJumpInstructionDao.getOne(condition);
    }
    public PortalJumpInstructionVo getActionById(PortalJumpInstructionCondition condition){

        return portalJumpInstructionDao.getActionById(condition);
    }
}
