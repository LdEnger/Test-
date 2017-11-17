package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.PortalBeanCurdEditListVo;
import cn.com.hiveview.entity.module.portal.PortalBeanCurdListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalBeanCurdListCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.PortalBeanCurdListMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by user on 2017/7/24.
 */
@Repository("/portalBeanCurdListDao")
public class PortalBeanCurdListDao extends SqlSessionDaoSupport{

    @Autowired
    PortalBeanCurdListMapper portalBeanCurdListMapper;

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate){
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public List<PortalBeanCurdEditListVo> getPageList(PortalBeanCurdListCondition portalBeanCurdListCondition){
        return  portalBeanCurdListMapper.getPageList(portalBeanCurdListCondition);
    }

    public Integer getCount(PortalBeanCurdListCondition portalBeanCurdListCondition){
        return portalBeanCurdListMapper.getCount(portalBeanCurdListCondition);
    }
    public Integer save(PortalBeanCurdListCondition portalBeanCurdListCondition){
        return portalBeanCurdListMapper.save(portalBeanCurdListCondition);
    }
    public Integer delete(PortalBeanCurdListCondition portalBeanCurdListCondition){
        return  portalBeanCurdListMapper.delete(portalBeanCurdListCondition);
    }
    public Integer update(PortalBeanCurdListCondition portalBeanCurdListCondition){
        return  portalBeanCurdListMapper.update(portalBeanCurdListCondition);
    }
    public  Integer updateIs(PortalBeanCurdListCondition portalBeanCurdListCondition){
        return  portalBeanCurdListMapper.updateIs(portalBeanCurdListCondition);
    }

    public List<PortalBeanCurdListVo> getCurdList(PortalBeanCurdListCondition portalBeanCurdListCondition){
        return portalBeanCurdListMapper.getCurdList(portalBeanCurdListCondition);
    }
}
