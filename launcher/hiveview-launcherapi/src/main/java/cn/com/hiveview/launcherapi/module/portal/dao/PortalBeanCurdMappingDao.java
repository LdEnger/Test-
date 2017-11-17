package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.PortalBeanCurdMappingListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalBeanCurdMappingListCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.PortalBeanCurdMappingListMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by user on 2017/8/2.
 */
@Repository("/portalBeanCurdMappingDao")
public class PortalBeanCurdMappingDao extends SqlSessionDaoSupport {

    @Autowired
    PortalBeanCurdMappingListMapper portalBeanCurdMappingListMapper;

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public  void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplete){
        super.setSqlSessionTemplate(sqlSessionTemplete);
    }

    public List<PortalBeanCurdMappingListVo> getPageList(PortalBeanCurdMappingListCondition portalBeanCurdMappingListCondition){
        return  portalBeanCurdMappingListMapper.getPageList(portalBeanCurdMappingListCondition);
    }
    public  Integer getCount(PortalBeanCurdMappingListCondition portalBeanCurdMappingListCondition){
        return portalBeanCurdMappingListMapper.getCount(portalBeanCurdMappingListCondition);
    }


    public Integer save(PortalBeanCurdMappingListCondition portalBeanCurdMappingListCondition){
        return  portalBeanCurdMappingListMapper.save(portalBeanCurdMappingListCondition);
    }
    public Integer delete(PortalBeanCurdMappingListCondition portalBeanCurdMappingListCondition){
        return portalBeanCurdMappingListMapper.delete(portalBeanCurdMappingListCondition);
    }
    public Integer update(PortalBeanCurdMappingListCondition portalBeanCurdMappingListCondition){
        return  portalBeanCurdMappingListMapper.update(portalBeanCurdMappingListCondition);
    }
    public Integer getMaxSeq(PortalBeanCurdMappingListCondition portalBeanCurdMappingListCondition){
        return portalBeanCurdMappingListMapper.getMaxSeq(portalBeanCurdMappingListCondition);
    }
    public Integer getMappingCount(PortalBeanCurdMappingListCondition portalBeanCurdMappingListCondition){
        return  portalBeanCurdMappingListMapper.getMappingCount(portalBeanCurdMappingListCondition);
    }
    public PortalBeanCurdMappingListVo getMinMapping(PortalBeanCurdMappingListCondition portalBeanCurdMappingListCondition){
        return portalBeanCurdMappingListMapper.getMinMapping(portalBeanCurdMappingListCondition);
    }
    public PortalBeanCurdMappingListVo getMaxMapping(PortalBeanCurdMappingListCondition portalBeanCurdMappingListCondition){
        return  portalBeanCurdMappingListMapper.getMaxMapping(portalBeanCurdMappingListCondition);
    }
    public Integer getMinSeq(PortalBeanCurdMappingListCondition portalBeanCurdMappingListCondition){
        return portalBeanCurdMappingListMapper.getMinSeq(portalBeanCurdMappingListCondition);
    }

}
