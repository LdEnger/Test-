package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.PortalFixedRecommendContentVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalFixedRecomContentCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.PortalFixedRecommendContentMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.ListResourceBundle;

/**
 * Created by user on 2017/7/14.
 */
@Repository("PortalFixedRecommendContentDao")
public class PortalFixedRecommendContentDao extends SqlSessionDaoSupport{

    @Autowired
    PortalFixedRecommendContentMapper portalFixedRecommendContentMapper;

    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public List<PortalFixedRecommendContentVo> getList(PortalFixedRecomContentCondition condition){
        return portalFixedRecommendContentMapper.getList(condition);
    }

    public Integer getCount(PortalFixedRecomContentCondition condition){
        return portalFixedRecommendContentMapper.getCount(condition);
    }

    public Integer save(PortalFixedRecomContentCondition condition){
        return portalFixedRecommendContentMapper.save(condition);
    }

    public Integer delete(PortalFixedRecomContentCondition condition){
        return portalFixedRecommendContentMapper.delete(condition);
    }

    public Integer update(PortalFixedRecomContentCondition condition){
        return portalFixedRecommendContentMapper.update(condition);
    }

    public PortalFixedRecommendContentVo getFixedRecommendContent(PortalFixedRecomContentCondition condition){
        return portalFixedRecommendContentMapper.getFixedRecommendContent(condition);
    }

    public List<PortalFixedRecommendContentVo> getFixedRecommendContentList(PortalFixedRecomContentCondition condition){
        return portalFixedRecommendContentMapper.getFixedRecommendContentList(condition);
    }

    public Integer getFixedRecommendContentCount(PortalFixedRecomContentCondition condition){
        return portalFixedRecommendContentMapper.getFixedRecommendContentCount(condition);
    }
    public PortalFixedRecommendContentVo getFixedRecommendContentOne(PortalFixedRecomContentCondition condition){
        return portalFixedRecommendContentMapper.getFixedRecommendContentOne(condition);
    }
    public Integer getFixedRecommendContentCountByContentId(PortalFixedRecomContentCondition condition){
        return portalFixedRecommendContentMapper.getFixedRecommendContentCountByContentId(condition);
    }
    public PortalFixedRecommendContentVo getMinMapping(PortalFixedRecomContentCondition portalBeanCurdMappingListCondition){
        return portalFixedRecommendContentMapper.getMinMapping(portalBeanCurdMappingListCondition);
    }
    public PortalFixedRecommendContentVo getMaxMapping(PortalFixedRecomContentCondition portalBeanCurdMappingListCondition){
        return  portalFixedRecommendContentMapper.getMaxMapping(portalBeanCurdMappingListCondition);
    }
    public Integer getMaxSeq(PortalFixedRecomContentCondition portalFixedRecomContentCondition){
        return portalFixedRecommendContentMapper.getMaxSeq(portalFixedRecomContentCondition);
    }
    public Integer getMinSeq(PortalFixedRecomContentCondition portalFixedRecomContentCondition){
        return portalFixedRecommendContentMapper.getMinSeq(portalFixedRecomContentCondition);
    }
}
