package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.NewTempletApkChannelListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.NewTempletApkChannelCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.NewTempletApkChannelMapper;
import cn.com.hiveview.launcherapi.module.portal.mapper.NewTempletApkMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by user on 2017/10/12.
 */
@Repository("/NewTempletApkChannelDao")
public class NewTempletApkChannelDao extends SqlSessionDaoSupport {
    @Autowired
    NewTempletApkChannelMapper newTempletApkChannelMapper;

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate){
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public List<NewTempletApkChannelListVo> getChannelPage(NewTempletApkChannelCondition condition){
        return newTempletApkChannelMapper.getChannelPage(condition);
    }
}
