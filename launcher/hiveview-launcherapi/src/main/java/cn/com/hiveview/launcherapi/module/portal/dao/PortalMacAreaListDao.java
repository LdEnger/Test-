package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.PortalMacAreaList;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalMacAreaCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.PortalMacAreaListMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/7/14.
 */
@Repository("PortalMacAreaListDao")
public class PortalMacAreaListDao extends SqlSessionDaoSupport {
    @Autowired
    PortalMacAreaListMapper portalMacAreaListMapper;
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public Integer add( PortalMacAreaCondition condition){
        return portalMacAreaListMapper.add(condition);
    }
    public List<PortalMacAreaList> getPageList(PortalMacAreaCondition condition){
        //System.out.println("apiDao"+condition);
        return portalMacAreaListMapper.getPageList(condition);
    }
    public Integer getPageCount( PortalMacAreaCondition condition){
        return portalMacAreaListMapper.getPageCount(condition);
    }
    public Integer getCountByCode( PortalMacAreaCondition condition){
        return portalMacAreaListMapper.getCountByCode(condition);
    }
    public Integer delete( PortalMacAreaCondition condition){
        return portalMacAreaListMapper.delete(condition);
    }
    public Integer update( PortalMacAreaCondition condition){
        return portalMacAreaListMapper.update(condition);
    }
    public PortalMacAreaList get(PortalMacAreaCondition condition){
        return portalMacAreaListMapper.get(condition);
    }
    public Integer getCountByMacSn( PortalMacAreaCondition condition){
        return portalMacAreaListMapper.getCountByMacSn(condition);
    }
}
