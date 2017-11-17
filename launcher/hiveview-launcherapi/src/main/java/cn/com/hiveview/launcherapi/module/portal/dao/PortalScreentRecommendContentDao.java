package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.PortalScreentRecommendContent;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalScreentRecommendContentCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.PortalScreentRecommendContentMapper;
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
@Repository("portalScreentRecommendContentDao")
public class PortalScreentRecommendContentDao extends SqlSessionDaoSupport {
    @Autowired
    PortalScreentRecommendContentMapper portalScreentRecommendContentMapper;
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public Integer add(PortalScreentRecommendContentCondition condition){
        return portalScreentRecommendContentMapper.add(condition);
    }
    public List<PortalScreentRecommendContent> getPageList(PortalScreentRecommendContentCondition condition){
        return portalScreentRecommendContentMapper.getPageList(condition);
    }
    public Integer getPageCount( PortalScreentRecommendContentCondition condition){
        return portalScreentRecommendContentMapper.getPageCount(condition);
    }
    public Integer delete( PortalScreentRecommendContentCondition condition){
        return portalScreentRecommendContentMapper.delete(condition);
    }
    public Integer update( PortalScreentRecommendContentCondition condition){
        return portalScreentRecommendContentMapper.update(condition);
    }
    public Integer getMaxSeq( PortalScreentRecommendContentCondition condition){
        return portalScreentRecommendContentMapper.getMaxSeq(condition);
    }
    public Integer getMinSeq( PortalScreentRecommendContentCondition condition){
        return portalScreentRecommendContentMapper.getMinSeq(condition);
    }
    public PortalScreentRecommendContent getMaxContent(PortalScreentRecommendContentCondition condition){
        return portalScreentRecommendContentMapper.getMaxContent(condition);
    }
    public PortalScreentRecommendContent getMinContent(PortalScreentRecommendContentCondition condition){
        return portalScreentRecommendContentMapper.getMinContent(condition);
    }
    public List<PortalScreentRecommendContent> getHasList(PortalScreentRecommendContentCondition condition){
        return portalScreentRecommendContentMapper.getHasList(condition);
    }
    public Integer getBigPic(PortalScreentRecommendContentCondition condition){
        return  portalScreentRecommendContentMapper.getBigPic(condition);
    }
}
