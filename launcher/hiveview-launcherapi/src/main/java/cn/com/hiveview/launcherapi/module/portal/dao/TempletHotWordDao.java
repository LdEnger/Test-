package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.TempletHotWordList;
import cn.com.hiveview.entity.module.portal.TempletList;
import cn.com.hiveview.launcherapi.module.portal.condition.TempletCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.TempletHotWordCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.TempletHotWordMapper;
import cn.com.hiveview.launcherapi.module.portal.mapper.TempletMapper;
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
@Repository("templetHotWordDao")
public class TempletHotWordDao extends SqlSessionDaoSupport {
    @Autowired
    private TempletHotWordMapper templetHotWordMapper;
    public  void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public List<TempletHotWordList> getList(TempletHotWordCondition condition){
        return templetHotWordMapper.getList(condition);
    }

}
