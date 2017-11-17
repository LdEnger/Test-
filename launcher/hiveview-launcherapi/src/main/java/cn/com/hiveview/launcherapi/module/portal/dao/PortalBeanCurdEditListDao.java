package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.PortalBeanCurdEditListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalBeanCurdEditListCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.PortalBeanCurdEditListMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by user on 2017/7/25.
 */
@Repository("/portalBeanCurdEditListDao")
public class PortalBeanCurdEditListDao extends SqlSessionDaoSupport{

    @Autowired
    PortalBeanCurdEditListMapper portalBeanCurdEditListMapper;

    public  void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate){
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public List<PortalBeanCurdEditListVo> getPageList(PortalBeanCurdEditListCondition portalBeanCurdEditListCondition){
        return portalBeanCurdEditListMapper.getPageList(portalBeanCurdEditListCondition);
    }
    public List<PortalBeanCurdEditListVo> getList(PortalBeanCurdEditListCondition portalBeanCurdEditListCondition){
        return  portalBeanCurdEditListMapper.getList(portalBeanCurdEditListCondition);
    }
    public List<PortalBeanCurdEditListVo> getEditList(PortalBeanCurdEditListCondition portalBeanCurdEditListCondition){
        return portalBeanCurdEditListMapper.getEditList(portalBeanCurdEditListCondition);
    }
    public PortalBeanCurdEditListVo getEdit(PortalBeanCurdEditListCondition portalBeanCurdEditListCondition){
        return portalBeanCurdEditListMapper.getEdit(portalBeanCurdEditListCondition);
    }
    public  Integer getCount(PortalBeanCurdEditListCondition portalBeanCurdEditListCondition){
        return  portalBeanCurdEditListMapper.getCount(portalBeanCurdEditListCondition);
    }

    public Integer getCountList(PortalBeanCurdEditListCondition portalBeanCurdEditListCondition){
        return  portalBeanCurdEditListMapper.getCountList(portalBeanCurdEditListCondition);
    }
    public Integer delete(PortalBeanCurdEditListCondition portalBeanCurdEditListCondition){
        return  portalBeanCurdEditListMapper.delete(portalBeanCurdEditListCondition);
    }

    public Integer save(PortalBeanCurdEditListCondition portalBeanCurdEditListCondition){
        return  portalBeanCurdEditListMapper.save(portalBeanCurdEditListCondition);
    }
    public Integer update(PortalBeanCurdEditListCondition portalBeanCurdEditListCondition){
        return portalBeanCurdEditListMapper.update(portalBeanCurdEditListCondition);
    }
    public Integer getEditCount(PortalBeanCurdEditListCondition portalBeanCurdEditListCondition){
        return portalBeanCurdEditListMapper.getEditCount(portalBeanCurdEditListCondition);
    }
    public Integer updateTime(PortalBeanCurdEditListCondition condition){
        return  portalBeanCurdEditListMapper.updateTime(condition);
    }
}
