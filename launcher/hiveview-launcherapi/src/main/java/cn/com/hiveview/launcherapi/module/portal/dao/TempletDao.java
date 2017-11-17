package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.TempletList;
import cn.com.hiveview.launcherapi.module.portal.condition.TempletCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.TempletMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/8/1.
 */
@Repository("templetDao")
public class TempletDao extends SqlSessionDaoSupport {
    @Autowired
    private TempletMapper templetMapper;
    public  void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public List<TempletList> getList(TempletCondition condition){
        return templetMapper.getList(condition);
    }
}
