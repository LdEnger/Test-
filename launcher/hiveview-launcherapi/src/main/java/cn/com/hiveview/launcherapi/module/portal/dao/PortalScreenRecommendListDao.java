package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.PortalScreenRecommendList;
import cn.com.hiveview.entity.module.portal.PortalScreenRecommendListApiVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalScreenRecommendListCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.PortalScreenRecommendListMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/6/29.
 */
@Repository("portalScreenRecommendListDao")
public class PortalScreenRecommendListDao extends SqlSessionDaoSupport{

    @Autowired
    PortalScreenRecommendListMapper portalScreenRecommendListMapper;
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }
    public List<PortalScreenRecommendListApiVo> getList(PortalScreenRecommendListCondition condition){
        return portalScreenRecommendListMapper.getList(condition);
    }
    public Integer getCount(PortalScreenRecommendListCondition condition){
        return portalScreenRecommendListMapper.getCount(condition);
    }
    public Integer add(PortalScreenRecommendListCondition condition){
        return portalScreenRecommendListMapper.add(condition);
    }
    public List<PortalScreenRecommendList> getPageList(PortalScreenRecommendListCondition condition){
        return portalScreenRecommendListMapper.getPageList(condition);
    }
    public Integer getPageCount(PortalScreenRecommendListCondition condition){
        return portalScreenRecommendListMapper.getPageCount(condition);
    }
    public Integer delete(PortalScreenRecommendListCondition condition){
        return portalScreenRecommendListMapper.delete(condition);
    }
    public Integer update(PortalScreenRecommendListCondition condition){
        return portalScreenRecommendListMapper.update(condition);
    }
    public List<PortalScreenRecommendList> getComboboxList(PortalScreenRecommendListCondition condition){
        return portalScreenRecommendListMapper.getComboboxList(condition);
    }
}
