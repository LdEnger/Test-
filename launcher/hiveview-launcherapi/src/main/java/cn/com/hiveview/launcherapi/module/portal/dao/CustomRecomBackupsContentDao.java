package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.CustomRecomBackupsContentVo;
import cn.com.hiveview.launcherapi.module.portal.condition.CustomRecomBackupsContentCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.CustomRecomBackupsContentMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by user on 2017/8/25.
 */
@Repository("customRecomBackupsContentDao")
public class CustomRecomBackupsContentDao extends SqlSessionDaoSupport {
    @Autowired
    CustomRecomBackupsContentMapper customRecomBackupsContentMapper;


    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public List<CustomRecomBackupsContentVo> getList(CustomRecomBackupsContentCondition condition){
        return customRecomBackupsContentMapper.getList(condition);
    }

    public CustomRecomBackupsContentVo get(CustomRecomBackupsContentCondition condition){
        return customRecomBackupsContentMapper.get(condition);
    }

    public CustomRecomBackupsContentVo getBySeq(CustomRecomBackupsContentCondition condition){
        return customRecomBackupsContentMapper.getBySeq(condition);
    }

    public Integer save(CustomRecomBackupsContentCondition condition){
        return customRecomBackupsContentMapper.save(condition);
    }

    public Integer update(CustomRecomBackupsContentCondition condition){
        return customRecomBackupsContentMapper.update(condition);
    }

    public Integer delete(CustomRecomBackupsContentCondition condition){
        return customRecomBackupsContentMapper.delete(condition);
    }

    public Integer getCount(CustomRecomBackupsContentCondition condition){
        return customRecomBackupsContentMapper.getCount(condition);
    }

    public Integer getMaxSeq(CustomRecomBackupsContentCondition condition){
        return customRecomBackupsContentMapper.getMaxSeq(condition);
    }

    public Integer getMinSeq(CustomRecomBackupsContentCondition condition){
        return customRecomBackupsContentMapper.getMinSeq(condition);
    }
    public CustomRecomBackupsContentVo getMinMapping(CustomRecomBackupsContentCondition condition){
        return customRecomBackupsContentMapper.getMinMapping(condition);
    }
    public CustomRecomBackupsContentVo getMaxMapping(CustomRecomBackupsContentCondition condition){
        return  customRecomBackupsContentMapper.getMaxMapping(condition);
    }
    public Integer insertBackUpList(CustomRecomBackupsContentCondition condition){
        return  customRecomBackupsContentMapper.insertBackUpList(condition);
    }
    public Integer updateIsUseredCount(CustomRecomBackupsContentCondition condition){
        return  customRecomBackupsContentMapper.updateIsUseredCount(condition);
    }
    public CustomRecomBackupsContentVo getDataIfPlay(CustomRecomBackupsContentCondition condition){
        return  customRecomBackupsContentMapper.getDataIfPlay(condition);
    }
}
