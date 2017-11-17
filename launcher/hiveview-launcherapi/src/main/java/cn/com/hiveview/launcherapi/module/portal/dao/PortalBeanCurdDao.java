package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.LauncherHomeApiVo;
import cn.com.hiveview.launcherapi.module.portal.mapper.PortalBeanCurdMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by user on 2017/7/26.
 */
@Repository("PortalBeanCurdDao")
public class PortalBeanCurdDao extends SqlSessionDaoSupport{

    @Autowired
    PortalBeanCurdMapper portalBeanCurdMapper;

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public List<LauncherHomeApiVo> getLauncherHome(Integer curdId){
        return portalBeanCurdMapper.getLauncherHome(curdId);
    }
}
