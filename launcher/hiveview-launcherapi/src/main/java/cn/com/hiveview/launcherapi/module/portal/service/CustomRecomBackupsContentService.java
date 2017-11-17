package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.CustomRecomBackupsContentVo;
import cn.com.hiveview.launcherapi.module.portal.condition.CustomRecomBackupsContentCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.CustomRecomBackupsContentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2017/8/25.
 */
@Service
public class CustomRecomBackupsContentService {

    @Autowired
    private CustomRecomBackupsContentDao customRecomBackupsContentDao;

    public ScriptPage<CustomRecomBackupsContentVo> getList(CustomRecomBackupsContentCondition condition) throws Exception {
        ScriptPage<CustomRecomBackupsContentVo> scriptPage = new ScriptPage<CustomRecomBackupsContentVo>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<CustomRecomBackupsContentVo> rows = this.customRecomBackupsContentDao.getList(condition);
        int total = this.customRecomBackupsContentDao.getCount(condition);
        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        return scriptPage;
    }

    public Integer save(CustomRecomBackupsContentCondition condition) throws Exception {
        condition.setIsEffective(1);
        //condition.setTempleteId(1);
        Integer seq = 1;
        Integer maxSeq = customRecomBackupsContentDao.getMaxSeq(condition);
        if(maxSeq != null){
            seq = maxSeq + 1;
        }
        condition.setSeq(seq);
        return this.customRecomBackupsContentDao.save(condition);
    }
    public Integer delete(CustomRecomBackupsContentCondition condition) throws Exception {
        if(condition.getId() == null && condition.getTempleteId() == null){
            return -1;
        }else{
        return this.customRecomBackupsContentDao.delete(condition);}
    }
    public Integer update(CustomRecomBackupsContentCondition condition) throws Exception {
        if(condition.getId() == null && condition.getTempleteId() == null){
            return -1;
        }else{
        return this.customRecomBackupsContentDao.update(condition);
    }}
    public CustomRecomBackupsContentVo get(CustomRecomBackupsContentCondition condition) throws Exception{
        return this.customRecomBackupsContentDao.get(condition);
    }

    public CustomRecomBackupsContentVo getBySeq(CustomRecomBackupsContentCondition condition) throws Exception{
        return this.customRecomBackupsContentDao.getBySeq(condition);
    }

    public Integer getCount(CustomRecomBackupsContentCondition condition)throws  Exception{
        return  this.customRecomBackupsContentDao.getCount(condition);
    }

    public Integer getMaxSeq(CustomRecomBackupsContentCondition condition)throws  Exception{
        return  this.customRecomBackupsContentDao.getMaxSeq(condition);
    }

    public Integer getMinSeq(CustomRecomBackupsContentCondition condition)throws  Exception{
        return  this.customRecomBackupsContentDao.getMinSeq(condition);
    }
    public CustomRecomBackupsContentVo getMinMapping(CustomRecomBackupsContentCondition condition)throws Exception{
        return this.customRecomBackupsContentDao.getMinMapping(condition);
    }
    public CustomRecomBackupsContentVo getMaxMapping(CustomRecomBackupsContentCondition condition)throws Exception{
        return this.customRecomBackupsContentDao.getMaxMapping(condition);
    }
    public Integer insertBackUpList(CustomRecomBackupsContentCondition condition){
        return this.customRecomBackupsContentDao.insertBackUpList(condition);
    }
}
