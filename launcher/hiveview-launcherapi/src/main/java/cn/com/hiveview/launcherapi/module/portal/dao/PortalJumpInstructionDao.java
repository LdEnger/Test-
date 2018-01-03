package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.PortalJumpInstructionVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalJumpInstructionCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.PortalJumpInstructionMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by xiach on 2017/10/8.
 */
@Repository("PortalJumInstructionDao")
public class PortalJumpInstructionDao extends SqlSessionDaoSupport {
    @Autowired
    PortalJumpInstructionMapper portalJumpInstructionMapper;

    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public List<PortalJumpInstructionCondition> getPageList(PortalJumpInstructionCondition condition){return portalJumpInstructionMapper.getPageList(condition);}

    public Integer getCount(PortalJumpInstructionCondition condition){return portalJumpInstructionMapper.getCount(condition);}

    public Integer add(PortalJumpInstructionCondition condition){return portalJumpInstructionMapper.add(condition);}

    public Integer delete(PortalJumpInstructionCondition condition){return portalJumpInstructionMapper.delete(condition);}

    public Integer update(PortalJumpInstructionCondition condition){return portalJumpInstructionMapper.update(condition);}

    public PortalJumpInstructionCondition getOne(PortalJumpInstructionCondition condition){return portalJumpInstructionMapper.getOne(condition);};
    public PortalJumpInstructionVo getActionById(PortalJumpInstructionCondition condition){return portalJumpInstructionMapper.getActionById(condition);};
    public List<PortalJumpInstructionVo> getInfoByStartApk(PortalJumpInstructionCondition condition){return portalJumpInstructionMapper.getInfoByStartApk(condition);}

}
