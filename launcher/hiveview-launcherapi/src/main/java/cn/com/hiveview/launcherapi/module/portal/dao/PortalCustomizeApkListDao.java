package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.PortalCustomizeApkList;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalBeanCurdEditListCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalCustomizeApkCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.PortalCustomizeApkListMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */
@Repository("PortalCustomizeApkListDao")
public class PortalCustomizeApkListDao extends SqlSessionDaoSupport {
    @Autowired
    PortalCustomizeApkListMapper portalCustomizeApkListMapper;
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public Integer add( PortalCustomizeApkCondition condition){
        return portalCustomizeApkListMapper.add(condition);
    }
    public List<PortalCustomizeApkList> getPageList(PortalCustomizeApkCondition condition){
        return portalCustomizeApkListMapper.getPageList(condition);
    }
    public List<PortalCustomizeApkList> getComboboxList(PortalCustomizeApkCondition condition){
        return portalCustomizeApkListMapper.getComboboxList(condition);
    }
    public Integer getPageCount( PortalCustomizeApkCondition condition){
        return portalCustomizeApkListMapper.getPageCount(condition);
    }
    public Integer delete( PortalCustomizeApkCondition condition){
        return portalCustomizeApkListMapper.delete(condition);
    }
    public Integer update( PortalCustomizeApkCondition condition){
        return portalCustomizeApkListMapper.update(condition);
    }
    public Integer updateVersion(PortalCustomizeApkCondition condition){
        return portalCustomizeApkListMapper.updateVersion(condition);
    }
    public Integer getCountByCurdEdit(PortalBeanCurdEditListCondition condition){
        return portalCustomizeApkListMapper.getCountByCurdEdit(condition);
    }
}
