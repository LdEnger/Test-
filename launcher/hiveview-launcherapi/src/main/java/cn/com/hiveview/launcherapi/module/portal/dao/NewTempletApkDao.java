package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.NewTempletApkListVo;
import cn.com.hiveview.entity.module.portal.NewTempletChannelWordsListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.NewTempletApkCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.NewTmpletChannelWordsCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.NewTempletApkMapper;
import cn.com.hiveview.launcherapi.module.portal.mapper.NewTempletChannelWordsMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by user on 2017/10/11.
 */
@Repository("/NewTempletApkDao")
public class NewTempletApkDao  extends SqlSessionDaoSupport{

    @Autowired
    NewTempletApkMapper newTempletApkMapper;

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate){
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public List<NewTempletApkListVo> getPage(NewTempletApkCondition condition){
        return newTempletApkMapper.getPage(condition);
    }

}
