package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.TempletApkChannelVo;
import cn.com.hiveview.launcherapi.module.portal.condition.TempletApkChannelCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.TempletApkChannelMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by user on 2017/7/27.
 */
@Repository("TempletApkChannelService")
public class TempletApkChannelDao extends SqlSessionDaoSupport{

    @Autowired
    TempletApkChannelMapper templetApkChannelMapper;

    public  void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public List<TempletApkChannelVo> getChannelList(TempletApkChannelCondition condition){
        return templetApkChannelMapper.getChannelList(condition);
    }
    public List<TempletApkChannelVo> getChannelTypeList(TempletApkChannelCondition condition){
        return templetApkChannelMapper.getChannelTypeList(condition);
    }
}
