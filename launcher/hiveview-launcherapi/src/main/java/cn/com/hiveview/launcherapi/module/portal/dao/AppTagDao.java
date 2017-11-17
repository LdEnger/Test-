package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.AppTagListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.AppTagListConditon;
import cn.com.hiveview.launcherapi.module.portal.mapper.AppTagMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by user on 2017/10/17.
 */
@Repository("AppTagDao")
public class AppTagDao extends SqlSessionDaoSupport{
    @Autowired
    AppTagMapper appTagMapper;


    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public List<AppTagListVo> getPage(AppTagListConditon conditon){
        return appTagMapper.getPage(conditon);
    }
    public Integer getCoun(AppTagListConditon conditon){
        return  appTagMapper.getCount(conditon);
    }
}
