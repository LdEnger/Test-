package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.OnlineGoodsList;
import cn.com.hiveview.launcherapi.module.portal.condition.OnlineGoodsCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.OnlineGoodsMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/8/2.
 */
@Repository("onlineGoodsDao")
public class OnlineGoodsDao extends SqlSessionDaoSupport {
    @Autowired
    OnlineGoodsMapper onlineGoodsMapper;
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }
    public Integer getPageCount( OnlineGoodsCondition condition){
        return onlineGoodsMapper.getPageCount(condition);
    }
    public List<OnlineGoodsList> getPageList(OnlineGoodsCondition condition){
        return onlineGoodsMapper.getPageList(condition);
    }
}
