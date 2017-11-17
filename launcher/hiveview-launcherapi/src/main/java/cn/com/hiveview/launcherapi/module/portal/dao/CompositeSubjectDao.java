package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.ActivityList;
import cn.com.hiveview.launcherapi.module.portal.condition.ActivityCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.CompositeSubjectCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.ActivityMapper;
import cn.com.hiveview.launcherapi.module.portal.mapper.CompositeSubjectMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/7/31.
 */
@Repository("compositeSubjectDao")
public class CompositeSubjectDao extends SqlSessionDaoSupport {
    @Autowired
    CompositeSubjectMapper compositeSubjectMapper;
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public Integer getPageCount( CompositeSubjectCondition condition){
        return compositeSubjectMapper.getPageCount(condition);
    }
    public List<ActivityList> getPageList(CompositeSubjectCondition condition){
        return compositeSubjectMapper.getPageList(condition);
    }
}
