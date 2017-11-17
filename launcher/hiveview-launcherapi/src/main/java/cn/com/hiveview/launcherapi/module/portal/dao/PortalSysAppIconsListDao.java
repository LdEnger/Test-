package cn.com.hiveview.launcherapi.module.portal.dao;
import cn.com.hiveview.entity.module.portal.PortalSysAppIconsList;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalSysAppIconsListCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.PortalSysAppIconsListMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/7/10.
 */
@Repository("portalSysAppIconsListDao")
public class PortalSysAppIconsListDao  extends SqlSessionDaoSupport {
    @Autowired
    PortalSysAppIconsListMapper portalSysAppIconsListMapper;
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public Integer add( PortalSysAppIconsListCondition condition){
        return portalSysAppIconsListMapper.add(condition);
    }
    public List<PortalSysAppIconsList> getPageList(PortalSysAppIconsListCondition condition){
        return portalSysAppIconsListMapper.getPageList(condition);
    }
    public Integer getPageCount( PortalSysAppIconsListCondition condition){
        return portalSysAppIconsListMapper.getPageCount(condition);
    }
    public Integer delete( PortalSysAppIconsListCondition condition){
        return portalSysAppIconsListMapper.delete(condition);
    }
    public Integer update( PortalSysAppIconsListCondition condition){
        return portalSysAppIconsListMapper.update(condition);
    }
    public List<PortalSysAppIconsList> getList(PortalSysAppIconsListCondition condition){
        return portalSysAppIconsListMapper.getList(condition);
    }
    public Integer getCountByPackage( PortalSysAppIconsListCondition condition){
        return portalSysAppIconsListMapper.getCountByPackage(condition);
    }
}
