package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.ActivityList;
import cn.com.hiveview.entity.module.portal.CloudAppSubjectList;
import cn.com.hiveview.launcherapi.module.portal.condition.CloudAppSubjectCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.CompositeSubjectCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.CloudAppSubjectMapper;
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
@Repository("cloudAppSubjectDao")
public class CloudAppSubjectDao extends SqlSessionDaoSupport {
    @Autowired
    CloudAppSubjectMapper cloudAppSubjectMapper;
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public Integer getPageCount( CloudAppSubjectCondition condition){
        return cloudAppSubjectMapper.getPageCount(condition);
    }
    public List<CloudAppSubjectList> getPageList(CloudAppSubjectCondition condition){
        return cloudAppSubjectMapper.getPageList(condition);
    }
}
