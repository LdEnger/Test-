package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.AreaGroupList;
import cn.com.hiveview.entity.module.portal.EntranceAreaList;
import cn.com.hiveview.launcherapi.module.portal.condition.AreaGroupCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.EntranceAreaCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.AreaGroupMapper;
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
@Repository("areaGroupDao")
public class AreaGroupDao extends SqlSessionDaoSupport {
    @Autowired
    AreaGroupMapper areaGroupMapper;
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }
    public List<AreaGroupList> getAreaGroupList(AreaGroupCondition condition){
        return areaGroupMapper.getAreaGroupList(condition);
    }
}
