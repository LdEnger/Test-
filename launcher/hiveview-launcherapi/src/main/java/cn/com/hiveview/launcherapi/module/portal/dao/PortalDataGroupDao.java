package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.PortalDataGroupListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalAreaAdminirationListCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalDataGroupListCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.PortalDataGroupMapper;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by user on 2017/10/9.
 */
@Repository("/PortalDataGroupDao")
public class PortalDataGroupDao extends SqlSessionDaoSupport{

    @Autowired
    PortalDataGroupMapper portalDataGroupMapper;


    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }
    @Resource(name ="jdbcTemplate" )
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate template){
        super.setSqlSessionTemplate(template);
    }


    public List<PortalDataGroupListVo> getPage(PortalDataGroupListCondition condition){
        return portalDataGroupMapper.getPage(condition);
    }

    public Integer getCount(PortalDataGroupListCondition condition){
        return portalDataGroupMapper.getCount(condition);
    }
    public Integer updateEffective(PortalDataGroupListCondition condition){
        return  portalDataGroupMapper.updateEffective(condition);
    }
    public Integer save(PortalDataGroupListCondition condition){
        return portalDataGroupMapper.save(condition);
    }
    public Integer delete(PortalDataGroupListCondition condition){
        return portalDataGroupMapper.delete(condition);
    }
    public Integer update(PortalDataGroupListCondition condition){
        return portalDataGroupMapper.update(condition);
    }
    public PortalDataGroupListVo getGroupInfo(PortalDataGroupListCondition condition){
        return portalDataGroupMapper.getGroupInfo(condition);
    }
    public PortalDataGroupListVo getGroupVo(PortalDataGroupListCondition condition){
        return portalDataGroupMapper.getGroupVo(condition);
    }
}
