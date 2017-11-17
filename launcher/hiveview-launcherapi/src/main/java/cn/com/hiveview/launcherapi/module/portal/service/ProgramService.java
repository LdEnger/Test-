package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.MergeVideoDataVo;
import cn.com.hiveview.launcherapi.module.portal.condition.MergeVideoData;
import cn.com.hiveview.launcherapi.module.portal.dao.ProgramDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2017/10/13.
 */
@Service
public class ProgramService {
    @Autowired
    private ProgramDao programDao;
    public ScriptPage<MergeVideoDataVo> getPageList(MergeVideoData condition) throws Exception {
        ScriptPage<MergeVideoDataVo> scriptPage = new ScriptPage<MergeVideoDataVo>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<MergeVideoDataVo> rows = this.programDao.getPageList(condition);
        int total = this.programDao.getPageCount(condition);
        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        return scriptPage;
    }
}
