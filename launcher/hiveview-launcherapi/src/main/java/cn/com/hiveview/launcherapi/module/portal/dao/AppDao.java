package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.AppVo;
import cn.com.hiveview.launcherapi.module.portal.condition.AppCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.AppMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by user on 2017/7/27.
 */
@Repository("AppDao")
public class AppDao extends SqlSessionDaoSupport{

    @Autowired
    AppMapper appMapper;

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public List<AppVo> getList(AppCondition condition){
        return appMapper.getList(condition);
    }

    public Integer getCount(AppCondition condition){
        return appMapper.getCount(condition);
    }

    public AppVo get(AppCondition condition){
        return appMapper.get(condition);
    }

    public List<AppVo> getDataGroupList(AppCondition condition){
        return appMapper.getDataGroupList(condition);
    }
}
