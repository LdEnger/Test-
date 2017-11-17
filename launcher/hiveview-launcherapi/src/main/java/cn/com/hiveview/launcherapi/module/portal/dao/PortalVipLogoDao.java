package cn.com.hiveview.launcherapi.module.portal.dao;


import cn.com.hiveview.launcherapi.module.portal.condition.PortalVipLogoCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.PortalVipLogoMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xiach on 2017/10/6.
 */
@Repository("portalVipLogoDao")
public class PortalVipLogoDao extends SqlSessionDaoSupport {

    @Autowired
    PortalVipLogoMapper portalVipLogoMapper;
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public List<PortalVipLogoCondition> getPageList(PortalVipLogoCondition condition){return portalVipLogoMapper.getPageList(condition);}
    public Integer getCount(PortalVipLogoCondition condition){
        return portalVipLogoMapper.getCount(condition);
    };
    public Integer add(PortalVipLogoCondition condition){
        return portalVipLogoMapper.add(condition);
    }
    public Integer update(PortalVipLogoCondition condition){
        return portalVipLogoMapper.update(condition);
    }
    public Integer delete(PortalVipLogoCondition condition){
        return portalVipLogoMapper.delete(condition);
    }
    public Integer getMaxSeq(){return portalVipLogoMapper.getMaxSeq();}
    public Integer getMinSeq(){return portalVipLogoMapper.getMinSeq();}
    public PortalVipLogoCondition getMaxContent(PortalVipLogoCondition condition){return portalVipLogoMapper.getMaxContent(condition);}
    public PortalVipLogoCondition getMinContent(PortalVipLogoCondition condition){return portalVipLogoMapper.getMinContent(condition);}


}
