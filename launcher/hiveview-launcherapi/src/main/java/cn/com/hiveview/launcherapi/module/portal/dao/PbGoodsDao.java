package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.PbsGoodsList;
import cn.com.hiveview.launcherapi.module.portal.condition.PbGoodsCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.PbGoodsMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/12/4.
 */
@Repository("pbGoodsDao")
public class PbGoodsDao extends SqlSessionDaoSupport {
    @Autowired
    PbGoodsMapper pbGoodsMapper;
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }
    public Integer getPageCount(PbGoodsCondition condition){
        return pbGoodsMapper.getPageCount(condition);
    }
    public List<PbsGoodsList> getPageList(PbGoodsCondition condition){
        return pbGoodsMapper.getPageList(condition);
    }
}
