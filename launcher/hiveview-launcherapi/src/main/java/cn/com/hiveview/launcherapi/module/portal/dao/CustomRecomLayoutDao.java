package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.CustomRecomLayoutVo;
import cn.com.hiveview.launcherapi.module.portal.condition.CustomRecomLayoutCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.CustomRecomLayoutMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by user on 2017/8/2.
 */
@Repository("/CustomRecomLayoutDao")
public class CustomRecomLayoutDao extends SqlSessionDaoSupport{

    @Autowired
    CustomRecomLayoutMapper customRecomLayoutMapper;

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name =  "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public List<CustomRecomLayoutVo> getList(CustomRecomLayoutCondition condition){
        return  customRecomLayoutMapper.getList(condition);
    }
    public Integer getCount(CustomRecomLayoutCondition condition){
        return  customRecomLayoutMapper.getCount(condition);
    }
    public CustomRecomLayoutVo get(CustomRecomLayoutCondition condition){
        return  customRecomLayoutMapper.getLayoutById(condition);
    }
    public Integer save(CustomRecomLayoutCondition condition){
        return  customRecomLayoutMapper.save(condition);
    }
    public Integer delete(CustomRecomLayoutCondition condition){
        return  customRecomLayoutMapper.delete(condition);
    }
    public List<CustomRecomLayoutVo> getListByPosition(CustomRecomLayoutCondition condition){
        return  customRecomLayoutMapper.getListByPosition(condition);
    }

    public Integer updateLayoutTeam(CustomRecomLayoutCondition condition){
        return  customRecomLayoutMapper.updateLayoutTeam(condition);
    }
}
