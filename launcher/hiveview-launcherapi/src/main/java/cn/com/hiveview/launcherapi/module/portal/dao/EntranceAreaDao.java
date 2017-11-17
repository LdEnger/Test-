package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.ActivityList;
import cn.com.hiveview.entity.module.portal.EntranceAreaList;
import cn.com.hiveview.launcherapi.module.portal.condition.CompositeSubjectCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.EntranceAreaCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalSysAppIconsListCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.CompositeSubjectMapper;
import cn.com.hiveview.launcherapi.module.portal.mapper.EntranceAreaMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/8/2.
 */
@Repository("entranceAreaDao")
public class EntranceAreaDao extends SqlSessionDaoSupport {
    @Autowired
    EntranceAreaMapper entranceAreaMapper;
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public List<EntranceAreaList> getCityByAreaId(EntranceAreaCondition condition){
        return entranceAreaMapper.getCityByAreaId(condition);
    }

    public Integer add( EntranceAreaCondition condition){
        return entranceAreaMapper.add(condition);
    }
    public Integer update( EntranceAreaCondition condition){
        return entranceAreaMapper.update(condition);
    }
    public Integer deleteCitys( EntranceAreaCondition condition){
        return entranceAreaMapper.deleteCitys(condition);
    }
    public Integer ifCount(EntranceAreaCondition condition){
        return entranceAreaMapper.ifCount(condition);
    }
}
