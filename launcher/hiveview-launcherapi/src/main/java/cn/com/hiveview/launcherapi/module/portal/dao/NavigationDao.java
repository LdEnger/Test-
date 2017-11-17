package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.NavigationVo;
import cn.com.hiveview.entity.module.portal.PortalScreenRecommendList;
import cn.com.hiveview.entity.module.portal.PortalScreenRecommendListApiVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalScreenRecommendListCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.NavigationMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by admin on 2017/6/29.
 */
@Repository("NavigationDao")
public class NavigationDao extends SqlSessionDaoSupport{

    @Autowired
    NavigationMapper navigationMapper;
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public List<NavigationVo> getNavigationList(Integer templetId){
        return navigationMapper.getNavigationList(templetId);
    }
    public HashMap<String,String> getDefaultTapPic(){
        return navigationMapper.getDefaultTapPic();
    }
    public String getVipPic(String vipId){
        return navigationMapper.getVipPic(vipId);
    }


    public List<PortalScreenRecommendListApiVo> getList(PortalScreenRecommendListCondition condition){
        return navigationMapper.getList(condition);
    }
    public Integer getCount(PortalScreenRecommendListCondition condition){
        return navigationMapper.getCount(condition);
    }
    public Integer add(PortalScreenRecommendListCondition condition){
        return navigationMapper.add(condition);
    }
    public List<PortalScreenRecommendList> getPageList(PortalScreenRecommendListCondition condition){
        return navigationMapper.getPageList(condition);
    }
    public Integer getPageCount(PortalScreenRecommendListCondition condition){
        return navigationMapper.getPageCount(condition);
    }
    public Integer delete(PortalScreenRecommendListCondition condition){
        return navigationMapper.delete(condition);
    }
    public Integer update(PortalScreenRecommendListCondition condition){
        return navigationMapper.update(condition);
    }
    public List<PortalScreenRecommendList> getComboboxList(PortalScreenRecommendListCondition condition){
        return navigationMapper.getComboboxList(condition);
    }
}
