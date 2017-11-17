package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.launcherapi.module.portal.condition.PortalSpeGroupCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.PortalSpeGroupMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xiach on 2017/10/10.
 */
@Repository("portalSpeGroupDao")
public class PortalSpeGroupDao extends SqlSessionDaoSupport {
    @Autowired
    PortalSpeGroupMapper portalSpeGroupMapper;
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }
    public List<PortalSpeGroupCondition> getPageList(PortalSpeGroupCondition condition){return portalSpeGroupMapper.getPageList(condition);}

    public Integer getCount(PortalSpeGroupCondition condition){return portalSpeGroupMapper.getCount(condition);}
}

