package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.PortalScreenBigPicsListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalScreenBigPicsListCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.PortalScreenBigPicsListMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by user on 2017/7/11.
 */
@Repository("PortalScreenBigPicsListDao")
public class PortalScreenBigPicsListDao extends SqlSessionDaoSupport{

    @Autowired
    PortalScreenBigPicsListMapper portalScreenBigPicsListMapper;

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public List<PortalScreenBigPicsListVo> getList(PortalScreenBigPicsListCondition portalScreenBigPicsListCondition){
        return portalScreenBigPicsListMapper.getList(portalScreenBigPicsListCondition);
    }

    public Integer getCount(PortalScreenBigPicsListCondition portalScreenBigPicsListCondition){
        return portalScreenBigPicsListMapper.getCount(portalScreenBigPicsListCondition);
    }

    public Integer save(PortalScreenBigPicsListCondition portalScreenBigPicsListCondition){
        return portalScreenBigPicsListMapper.save(portalScreenBigPicsListCondition);
    }

    public Integer delete(PortalScreenBigPicsListCondition portalScreenBigPicsListCondition){
        return portalScreenBigPicsListMapper.delete(portalScreenBigPicsListCondition);
    }

    public Integer update(PortalScreenBigPicsListCondition portalScreenBigPicsListCondition){
        return portalScreenBigPicsListMapper.update(portalScreenBigPicsListCondition);
    }
    public Integer updateBig(PortalScreenBigPicsListCondition condition){
        return  portalScreenBigPicsListMapper.updateBig(condition);
    }
}
