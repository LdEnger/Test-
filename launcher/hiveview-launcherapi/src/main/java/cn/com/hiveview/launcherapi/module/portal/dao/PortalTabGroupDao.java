package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.PortalTabPageEntity;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalTabGroupCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.PortalTabGroupMapper;
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
@Repository("portalTabGroupDao")
public class PortalTabGroupDao extends SqlSessionDaoSupport {
    @Autowired
    PortalTabGroupMapper portalTabGroupMapper;
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public List<PortalTabGroupCondition> getPageList(PortalTabGroupCondition condition){return portalTabGroupMapper.getPageList(condition);}

    public Integer getCount(PortalTabGroupCondition condition){return portalTabGroupMapper.getCount(condition);}

    public Integer getMaxSeq(PortalTabGroupCondition condition){return portalTabGroupMapper.getMaxSeq(condition);};

    public Integer add(PortalTabGroupCondition condition){return portalTabGroupMapper.add(condition);}

    public Integer delete(PortalTabGroupCondition condition){return portalTabGroupMapper.delete(condition);}

    public Integer update(PortalTabGroupCondition condition){return portalTabGroupMapper.update(condition);}

    public PortalTabGroupCondition getMinContent(PortalTabGroupCondition condition){return portalTabGroupMapper.getMinContent(condition);}

    public PortalTabGroupCondition getMaxContent(PortalTabGroupCondition condition){return portalTabGroupMapper.getMaxContent(condition);}

    public List<PortalTabPageEntity> getList(PortalTabGroupCondition condition){
        return portalTabGroupMapper.getList(condition);
    }
    public Integer getMinSeq(PortalTabGroupCondition condition){return portalTabGroupMapper.getMinSeq(condition);}
    public List<PortalTabGroupCondition> getTabListByGroupId(PortalTabGroupCondition condition){
        return portalTabGroupMapper.getTabListByGroupId(condition);
    }
}
