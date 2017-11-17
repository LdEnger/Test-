package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.TempletApkList;
import cn.com.hiveview.launcherapi.module.portal.condition.TempletApkCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.TempletApkMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/8/1.
 */
@Repository("templetApkDao")
public class TempletApkDao extends SqlSessionDaoSupport {
    @Autowired
    private TempletApkMapper templetApkMapper;
    public  void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public List<TempletApkList> getList(TempletApkCondition condition){
        return templetApkMapper.getList(condition);
    }
}
