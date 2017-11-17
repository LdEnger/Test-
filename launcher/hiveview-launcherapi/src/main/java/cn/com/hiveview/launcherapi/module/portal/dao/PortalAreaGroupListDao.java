package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.PortalAreaGroupList;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalAreaGroupCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.PortalAreaGroupListMapper;
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
@Repository("PortalAreaGroupListDao")
public class PortalAreaGroupListDao extends SqlSessionDaoSupport {
    @Autowired
    PortalAreaGroupListMapper portalAreaGroupListMapper;
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public Integer add( PortalAreaGroupCondition condition){
        return portalAreaGroupListMapper.add(condition);
    }
    public List<PortalAreaGroupList> getPageList(PortalAreaGroupCondition condition){
        return portalAreaGroupListMapper.getPageList(condition);
    }
    public List<PortalAreaGroupList> getList(PortalAreaGroupCondition condition){
        return portalAreaGroupListMapper.getList(condition);
    }
    public PortalAreaGroupList getAreaGroupByCode(PortalAreaGroupCondition condition){
        return portalAreaGroupListMapper.getAreaGroupByCode(condition);
    }
    public Integer getPageCount( PortalAreaGroupCondition condition){
        return portalAreaGroupListMapper.getPageCount(condition);
    }
    public Integer getCountByAreaCode( PortalAreaGroupCondition condition){
        return portalAreaGroupListMapper.getCountByAreaCode(condition);
    }
    public Integer getCountByAreaName( PortalAreaGroupCondition condition){
        return portalAreaGroupListMapper.getCountByAreaName(condition);
    }
    public Integer delete( PortalAreaGroupCondition condition){
        return portalAreaGroupListMapper.delete(condition);
    }
    public Integer update( PortalAreaGroupCondition condition){
        return portalAreaGroupListMapper.update(condition);
    }
    public Integer updateMacAreaName( PortalAreaGroupCondition condition){
        return portalAreaGroupListMapper.updateMacAreaName(condition);
    }
}
