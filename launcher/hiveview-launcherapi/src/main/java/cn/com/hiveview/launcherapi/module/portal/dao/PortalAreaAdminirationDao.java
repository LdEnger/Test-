package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.PortalAreaAdministrationListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalAreaAdminirationListCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.PortalAreaAdminirationMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by user on 2017/10/6.
 */
@Repository("/PortalAreaAdminirationDao")
public class PortalAreaAdminirationDao extends SqlSessionDaoSupport {

    @Autowired
    PortalAreaAdminirationMapper  portalAreaAdminirationMapper;

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate){
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public List<PortalAreaAdministrationListVo> getPage(PortalAreaAdminirationListCondition condition){
        return portalAreaAdminirationMapper.getPage(condition);
    }
    public Integer getCount(PortalAreaAdminirationListCondition condition){
        return portalAreaAdminirationMapper.getCount(condition);
    }
    public Integer save(PortalAreaAdminirationListCondition condition){
        return portalAreaAdminirationMapper.save(condition);
    }
    public Integer delete(PortalAreaAdminirationListCondition condition){
        return portalAreaAdminirationMapper.delete(condition);
    }
    public Integer update(PortalAreaAdminirationListCondition condition){
        return portalAreaAdminirationMapper.update(condition);
    }
}
