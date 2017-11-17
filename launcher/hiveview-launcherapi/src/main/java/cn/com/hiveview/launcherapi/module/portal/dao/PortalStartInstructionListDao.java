package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.PortalStartInstructionList;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalBeanCurdEditListCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalStartInstructionCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.PortalStartInstructionListMapper;
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
@Repository("portalStartInstructionListDao")
public class PortalStartInstructionListDao extends SqlSessionDaoSupport {
    @Autowired
    PortalStartInstructionListMapper portalStartInstructionListMapper;
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public Integer add( PortalStartInstructionCondition condition){
        return portalStartInstructionListMapper.add(condition);
    }
    public List<PortalStartInstructionList> getPageList(PortalStartInstructionCondition condition){
        return portalStartInstructionListMapper.getPageList(condition);
    }
    public List<PortalStartInstructionList> getComboboxList(PortalStartInstructionCondition condition){
        return portalStartInstructionListMapper.getComboboxList(condition);
    }
    public Integer getPageCount( PortalStartInstructionCondition condition){
        return portalStartInstructionListMapper.getPageCount(condition);
    }
    public Integer delete( PortalStartInstructionCondition condition){
        return portalStartInstructionListMapper.delete(condition);
    }
    public Integer update( PortalStartInstructionCondition condition){
        return portalStartInstructionListMapper.update(condition);
    }
    public PortalStartInstructionList getList(PortalStartInstructionCondition condition){
        return portalStartInstructionListMapper.getList(condition);
    }
    public Integer getCountByString( PortalStartInstructionCondition condition){
        return portalStartInstructionListMapper.getCountByString(condition);
    }
    public Integer getCountByCurdEdit(PortalBeanCurdEditListCondition condition){
        return portalStartInstructionListMapper.getCountByCurdEdit(condition);
    }
}
