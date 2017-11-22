package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.PortalCustomRecomContentApiVo;
import cn.com.hiveview.entity.module.portal.PortalCustomRecomContentVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalCustomRecomContentCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.PortalCustomRecomContentMapper;
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
@Repository("portalCustomRecomContentDao")
public class PortalCustomRecomContentDao extends SqlSessionDaoSupport{

    @Autowired
    PortalCustomRecomContentMapper portalCustomRecomContentMapper;


    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public List<PortalCustomRecomContentApiVo> getPortalCustomRecomContentAPIList(PortalCustomRecomContentCondition condition){
        return portalCustomRecomContentMapper.getPortalCustomRecomContentAPIList(condition);
    }

    public List<PortalCustomRecomContentVo> getList(PortalCustomRecomContentCondition condition){
        return portalCustomRecomContentMapper.getList(condition);
    }

    public List<PortalCustomRecomContentVo> getContentLayoutListByContentId(PortalCustomRecomContentCondition condition){
        return portalCustomRecomContentMapper.getContentLayoutListByContentId(condition);
    }

    public List<PortalCustomRecomContentVo> getContentListByTempletId(PortalCustomRecomContentCondition condition){
        return portalCustomRecomContentMapper.getContentListByTempletId(condition);
    }

    public Integer save(PortalCustomRecomContentCondition condition){
        return portalCustomRecomContentMapper.save(condition);
    }

    public Integer update(PortalCustomRecomContentCondition condition){
        return portalCustomRecomContentMapper.update(condition);
    }

    public Integer updateContentById(PortalCustomRecomContentCondition condition){
        return portalCustomRecomContentMapper.updateContentById(condition);
    }

    public Integer delete(PortalCustomRecomContentCondition condition){
        return portalCustomRecomContentMapper.delete(condition);
    }

    public Integer updateByLayoutIdAndTempleteId(PortalCustomRecomContentCondition condition){
        return portalCustomRecomContentMapper.updateByLayoutIdAndTempleteId(condition);
    }

    public Integer getCount(PortalCustomRecomContentCondition condition){
        return portalCustomRecomContentMapper.getCount(condition);
    }
    public Integer saveCopyContent(PortalCustomRecomContentCondition portalCustomRecomContentCondition){
        return portalCustomRecomContentMapper.saveCopyContent(portalCustomRecomContentCondition);
    }
    public Integer getVideoCount(PortalCustomRecomContentCondition portalCustomRecomContentCondition){
        return portalCustomRecomContentMapper.getVideoCount(portalCustomRecomContentCondition);
    }
    public Integer updateVersion(PortalCustomRecomContentCondition condition){
        return portalCustomRecomContentMapper.updateVersion(condition);
    }
    public Integer updateBackUpVersion(PortalCustomRecomContentCondition condition){
        return portalCustomRecomContentMapper.updateBackUpVersion(condition);
    }
    public List<PortalCustomRecomContentCondition> getPageList(PortalCustomRecomContentCondition condition){
        return portalCustomRecomContentMapper.getPageList(condition);
    }
}
