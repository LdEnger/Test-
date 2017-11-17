package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.PortalFiexdRecomListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalFixedRecomListCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.PortalFixedRecomListMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by user on 2017/7/14.
 */
@Repository("PortalFiexdRecomListDao")
public class PortalFixedRecomListDao extends SqlSessionDaoSupport{

    @Autowired
    PortalFixedRecomListMapper portalFixedRecomListMapper;

    public  void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }
    public List<PortalFiexdRecomListVo> getList(PortalFixedRecomListCondition portalFiexdRecomListCondition){
        return  portalFixedRecomListMapper.getList(portalFiexdRecomListCondition);
    }
    public Integer getCount(PortalFixedRecomListCondition portalFiexdRecomListCondition){
        return  portalFixedRecomListMapper.getCount(portalFiexdRecomListCondition);
    }
    public  Integer save(PortalFixedRecomListCondition portalFiexdRecomListCondition){
        return  portalFixedRecomListMapper.save(portalFiexdRecomListCondition);
    }
    public  Integer delete(PortalFixedRecomListCondition portalFiexdRecomListCondition){
        return  portalFixedRecomListMapper.delete(portalFiexdRecomListCondition);
    }
    public  Integer update(PortalFixedRecomListCondition portalFiexdRecomListCondition){
        return  portalFixedRecomListMapper.update(portalFiexdRecomListCondition);
    }
    public  List<PortalFiexdRecomListVo> getTypeList(PortalFixedRecomListCondition portalFiexdRecomListCondition){
        return  portalFixedRecomListMapper.getTypeList(portalFiexdRecomListCondition);
    }
    public Integer updateEffective(PortalFixedRecomListCondition portalFiexdRecomListCondition){
        return  portalFixedRecomListMapper.updateEffective(portalFiexdRecomListCondition);
    }
    public  PortalFiexdRecomListVo getFixedRecomListOne(PortalFixedRecomListCondition portalFiexdRecomListCondition){
        return  portalFixedRecomListMapper.getFixedRecomListOne(portalFiexdRecomListCondition);
    }
}
