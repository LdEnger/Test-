package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.PortalLauncherTempletContentList;
import cn.com.hiveview.entity.module.portal.PortalLauncherTempletTabList;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalLauncherTempletContentCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalLauncherTempletTabCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.PortalLauncherTempletContentMapper;
import cn.com.hiveview.launcherapi.module.portal.mapper.PortalLauncherTempletTabMapper;
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
@Repository("portalLauncherTempletTabDao")
public class PortalLauncherTempletTabDao extends SqlSessionDaoSupport {
    @Autowired
    PortalLauncherTempletTabMapper portalLauncherTempletTabMapper;
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public Integer add(PortalLauncherTempletTabCondition condition){
        return portalLauncherTempletTabMapper.add(condition);
    }
    public List<PortalLauncherTempletTabList> getPageList(PortalLauncherTempletTabCondition condition){
        return portalLauncherTempletTabMapper.getPageList(condition);
    }
    public List<PortalLauncherTempletTabList> getComboboxList(PortalLauncherTempletTabCondition condition){
        return portalLauncherTempletTabMapper.getComboboxList(condition);
    }
    public Integer getPageCount( PortalLauncherTempletTabCondition condition){
        return portalLauncherTempletTabMapper.getPageCount(condition);
    }
    public Integer delete( PortalLauncherTempletTabCondition condition){
        return portalLauncherTempletTabMapper.delete(condition);
    }
    public Integer update( PortalLauncherTempletTabCondition condition){
        return portalLauncherTempletTabMapper.update(condition);
    }
    public Integer getMaxSeq( PortalLauncherTempletTabCondition condition){
        return portalLauncherTempletTabMapper.getMaxSeq(condition);
    }
    public PortalLauncherTempletTabList getMinTab(PortalLauncherTempletTabCondition condition){
        return portalLauncherTempletTabMapper.getMinTab(condition);
    }
    public PortalLauncherTempletTabList getMaxTab(PortalLauncherTempletTabCondition condition){
        return portalLauncherTempletTabMapper.getMaxTab(condition);
    }
    public Integer getCount(PortalLauncherTempletTabCondition condition){
        return  portalLauncherTempletTabMapper.getCount(condition);
    }
    public PortalLauncherTempletTabList getTopSeq(PortalLauncherTempletTabCondition condition){
        return portalLauncherTempletTabMapper.getTopSeq(condition);
    }

}
