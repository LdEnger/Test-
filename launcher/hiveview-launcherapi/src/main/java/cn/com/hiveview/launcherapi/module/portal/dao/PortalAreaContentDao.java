package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.PortalAreaContentListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalAreaContentListCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.PortalAreaContentMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by user on 2017/10/8.
 */
@Repository("/PortalAreaContentDao")
public class PortalAreaContentDao extends SqlSessionDaoSupport {

    @Autowired
    PortalAreaContentMapper portalAreaContentMapper;

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate){
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public List<PortalAreaContentListVo> getPage(PortalAreaContentListCondition condition){
       return portalAreaContentMapper.getPage(condition);
    }

    public Integer getCount(PortalAreaContentListCondition condition){
        return  portalAreaContentMapper.getCount(condition);
    }
    public Integer delete(PortalAreaContentListCondition condition){
        return  portalAreaContentMapper.delete(condition);
    }

    public Integer save(PortalAreaContentListCondition condition){
        return  portalAreaContentMapper.save(condition);
    }

    public Integer update(PortalAreaContentListCondition condition){
        return  portalAreaContentMapper.update(condition);
    }

    public PortalAreaContentListVo getAreaMinSeq(PortalAreaContentListCondition condition){
        return  portalAreaContentMapper.getAreaMinSeq(condition);
    }
    public PortalAreaContentListVo getAreaMaxSeq(PortalAreaContentListCondition condition){
        return portalAreaContentMapper.getAreaMaxSeq(condition);
    }

}
