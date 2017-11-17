package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.NewTempletChannelWordsListVo;
import cn.com.hiveview.entity.module.portal.PortalAreaAdministrationListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.NewTmpletChannelWordsCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalAreaAdminirationListCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.NewTempletChannelWordsMapper;
import cn.com.hiveview.launcherapi.module.portal.mapper.PortalAreaAdminirationMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by user on 2017/10/10.
 */
@Repository("/NewTempletChannelWordsDao")
public class NewTempletChannelWordsDao extends SqlSessionDaoSupport {

    @Autowired
    NewTempletChannelWordsMapper newTempletChannelWordsMapper;

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate){
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public List<NewTempletChannelWordsListVo> getPage(NewTmpletChannelWordsCondition condition){
        return newTempletChannelWordsMapper.getPage(condition);
    }
    public Integer getCount(NewTmpletChannelWordsCondition condition){
        return newTempletChannelWordsMapper.getCount(condition);
    }
    public List<NewTempletChannelWordsListVo> getPageList(NewTmpletChannelWordsCondition condition){
        return newTempletChannelWordsMapper.getPageList(condition);
    }
    public Integer getApkCount(NewTmpletChannelWordsCondition condition){
        return newTempletChannelWordsMapper.getApkCount(condition);
    }
}
