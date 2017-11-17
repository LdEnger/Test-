package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.launcherapi.module.portal.condition.PortalTabCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.PortalTabMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xiach on 2017/10/9.
 */
@Repository("portalTabDao")
public class PortalTabDao extends SqlSessionDaoSupport{
    @Autowired
    PortalTabMapper portalTabMapper;
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public List<PortalTabCondition> getPageList(PortalTabCondition condition){return portalTabMapper.getPageList(condition);}

    public Integer getCount(PortalTabCondition condition){return portalTabMapper.getCount(condition);}

    public Integer getMaxSeq(){return portalTabMapper.getMaxSeq();};

    public Integer add(PortalTabCondition condition){return portalTabMapper.add(condition);}

    public Integer delete(PortalTabCondition condition){return portalTabMapper.delete(condition);}

    public Integer update(PortalTabCondition condition){return portalTabMapper.update(condition);}

    public PortalTabCondition getOne(PortalTabCondition condition){return portalTabMapper.getOne(condition);}
}
