package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.PortalAreaAdministrationListEntity;
import cn.com.hiveview.entity.module.portal.PortalAreaAdministrationListVo;
import cn.com.hiveview.entity.module.portal.PortalAreaContentListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalAreaAdminirationListCondition;
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

    public PortalAreaContentListVo getMinContent(PortalAreaContentListCondition condition){
        return  portalAreaContentMapper.getMinContent(condition);
    }
    public PortalAreaContentListVo getMaxContent(PortalAreaContentListCondition condition){
        return portalAreaContentMapper.getMaxContent(condition);
    }
    public Integer getMaxSeq(PortalAreaContentListCondition condition){
        return portalAreaContentMapper.getMaxSeq(condition);
    };
    public PortalAreaContentListVo getTopSeq(PortalAreaContentListCondition condition){
        return portalAreaContentMapper.getTopSeq(condition);
    };

    public List<PortalAreaAdministrationListEntity> getAreaContentList(PortalAreaContentListCondition condition){
        return portalAreaContentMapper.getAreaContentList(condition);
    }
}
