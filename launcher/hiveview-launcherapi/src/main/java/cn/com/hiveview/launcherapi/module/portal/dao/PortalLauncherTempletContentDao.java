package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.PortalLauncherTempletContentList;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalLauncherTempletContentCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.PortalLauncherTempletContentMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/7/27.
 */
@Repository("portalLauncherTempletContentDao")
public class PortalLauncherTempletContentDao extends SqlSessionDaoSupport {
    @Autowired
    PortalLauncherTempletContentMapper portalLauncherTempletContentMapper;
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public Integer add( PortalLauncherTempletContentCondition condition){
        return portalLauncherTempletContentMapper.add(condition);
    }
    public List<PortalLauncherTempletContentList> getPageList(PortalLauncherTempletContentCondition condition){
        return portalLauncherTempletContentMapper.getPageList(condition);
    }
    public Integer getPageCount( PortalLauncherTempletContentCondition condition){
        return portalLauncherTempletContentMapper.getPageCount(condition);
    }
    public Integer delete( PortalLauncherTempletContentCondition condition){
        return portalLauncherTempletContentMapper.delete(condition);
    }
    public Integer update( PortalLauncherTempletContentCondition condition){
        return portalLauncherTempletContentMapper.update(condition);
    }
    public Integer getMaxSeq( PortalLauncherTempletContentCondition condition){
        return portalLauncherTempletContentMapper.getMaxSeq(condition);
    }
    public PortalLauncherTempletContentList getMinContent(PortalLauncherTempletContentCondition condition){
        return portalLauncherTempletContentMapper.getMinContent(condition);
    }
    public PortalLauncherTempletContentList getMaxContent(PortalLauncherTempletContentCondition condition){
        return portalLauncherTempletContentMapper.getMaxContent(condition);
    }
    public Integer getCount(PortalLauncherTempletContentCondition condition){
        return  portalLauncherTempletContentMapper.getCount(condition);
    }

}
