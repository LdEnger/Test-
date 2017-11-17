package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.NewContentChanList;
import cn.com.hiveview.launcherapi.module.portal.condition.NewContentChanCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalSysAppIconsListCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.NewContentChanMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/7/18.
 */
@Repository("newContentChanDao")
public class NewContentChanDao extends SqlSessionDaoSupport {
    @Autowired
    NewContentChanMapper newContentChanMapper;
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }
    public Integer getPageCount( NewContentChanCondition condition){
        return newContentChanMapper.getPageCount(condition);
    }
    public List<NewContentChanList> getPageList(NewContentChanCondition condition){
        return newContentChanMapper.getPageList(condition);
    }
    public List<NewContentChanList> getPageListByPremiere(NewContentChanCondition condition){
        return newContentChanMapper.getPageListByPremiere(condition);
    }
    public Integer getCountByPremiere( NewContentChanCondition condition){
        return newContentChanMapper.getCountByPremiere(condition);
    }

    public NewContentChanList get(NewContentChanCondition condition){
        return newContentChanMapper.get(condition);
    }
    public List<NewContentChanList> getList(NewContentChanCondition condition){
        return newContentChanMapper.getList(condition);
    }
    public List<NewContentChanList> getDataGroupList(NewContentChanCondition condition){
        return newContentChanMapper.getDataGroupList(condition);
    }
}
