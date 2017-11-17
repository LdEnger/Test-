package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.CustomRecomTempleteListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.CustomRecomTempleteCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.CustomRecomTempleteListMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by user on 2017/7/20.
 */
@Repository("/CustomRecomTempleteListDao")
public class CustomRecomTempleteListDao extends SqlSessionDaoSupport{

    @Autowired
    CustomRecomTempleteListMapper customRecomTempleteListMapper;

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name =  "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public List<CustomRecomTempleteListVo> getList(CustomRecomTempleteCondition customRecomTempleteCondition){
        return  customRecomTempleteListMapper.getList(customRecomTempleteCondition);
    }
    public Integer getCount(CustomRecomTempleteCondition customRecomTempleteCondition){
        return  customRecomTempleteListMapper.getCount(customRecomTempleteCondition);
    }
    public CustomRecomTempleteListVo get(CustomRecomTempleteCondition customRecomTempleteCondition){
        return  customRecomTempleteListMapper.get(customRecomTempleteCondition);
    }
    public Integer save(CustomRecomTempleteCondition customRecomTempleteCondition){
        return  customRecomTempleteListMapper.save(customRecomTempleteCondition);
    }
    public Integer update(CustomRecomTempleteCondition customRecomTempleteCondition){
        return  customRecomTempleteListMapper.update(customRecomTempleteCondition);
    }

    public Integer updateIsEffective(CustomRecomTempleteCondition customRecomTempleteCondition){
        return  customRecomTempleteListMapper.updateIsEffective(customRecomTempleteCondition);
    }
    public Integer delete(CustomRecomTempleteCondition customRecomTempleteCondition){
        return  customRecomTempleteListMapper.delete(customRecomTempleteCondition);
    }

    public Integer selectFatherId(CustomRecomTempleteCondition customRecomTempleteCondition){
        return  customRecomTempleteListMapper.selectFatherId(customRecomTempleteCondition);
    }

    public List<CustomRecomTempleteListVo> getFirstList(CustomRecomTempleteCondition customRecomTempleteCondition){
        return  customRecomTempleteListMapper.getFirstList(customRecomTempleteCondition);
    }

    public List<CustomRecomTempleteListVo> getTempleteByFatherId(Integer templeteId){
        return customRecomTempleteListMapper.getTempleteByFatherId(templeteId);
    }
}
