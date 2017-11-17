package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.NewVipActivityVo;
import cn.com.hiveview.entity.module.portal.OnlineGoodsList;
import cn.com.hiveview.launcherapi.module.portal.condition.NewVipActivityCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.OnlineGoodsCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.NewVipActivityMapper;
import cn.com.hiveview.launcherapi.module.portal.mapper.OnlineGoodsMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/10/13.
 */
@Repository("/newVipActivityDao")
public class NewVipActivityDao extends SqlSessionDaoSupport{
    @Autowired
    NewVipActivityMapper newVipActivity;
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }
    public Integer getPageCount(NewVipActivityCondition condition){
        return newVipActivity.getPageCount(condition);
    }
    public List<NewVipActivityVo> getPageList(NewVipActivityCondition condition){
        return newVipActivity.getPageList(condition);
    }
}
