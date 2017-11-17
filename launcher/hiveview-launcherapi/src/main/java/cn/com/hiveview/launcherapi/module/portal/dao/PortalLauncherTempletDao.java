package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.PortalLauncherTempletList;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalLauncherTempletCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.PortalLauncherTempletMapper;
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
@Repository("portalLauncherTempletDao")
public class PortalLauncherTempletDao extends SqlSessionDaoSupport {
    @Autowired
    PortalLauncherTempletMapper portalLauncherTempletMapper;

    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public Integer add( PortalLauncherTempletCondition condition){
        return portalLauncherTempletMapper.add(condition);
    }
    public List<PortalLauncherTempletList> getPageList(PortalLauncherTempletCondition condition){
        return portalLauncherTempletMapper.getPageList(condition);
    }
    public List<PortalLauncherTempletList> getComboboxList(PortalLauncherTempletCondition condition){
        List<PortalLauncherTempletList> rows=portalLauncherTempletMapper.getComboboxList(condition);
        return rows;
    }
    public Integer getPageCount( PortalLauncherTempletCondition condition){
        return portalLauncherTempletMapper.getPageCount(condition);
    }
    public Integer getCount( PortalLauncherTempletCondition condition){
        return portalLauncherTempletMapper.getCount(condition);
    }
    public Integer delete( PortalLauncherTempletCondition condition){
        return portalLauncherTempletMapper.delete(condition);
    }
    public Integer update( PortalLauncherTempletCondition condition){
        return portalLauncherTempletMapper.update(condition);
    }
    public Integer updateLogoId( PortalLauncherTempletCondition condition){
        return portalLauncherTempletMapper.updateLogoId(condition);
    }

}
