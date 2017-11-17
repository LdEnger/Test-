package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.PortalBeanCurdImgListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalBeanCurdImgListCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.PortalBeanCurdImgListMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by user on 2017/7/26.
 */
@Repository("/portalBeanCurdImgListDao")
public class PortalBeanCurdImgListDao extends SqlSessionDaoSupport{

    @Autowired
    PortalBeanCurdImgListMapper portalBeanCurdImgListMapper;

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplete){
        super.setSqlSessionTemplate(sqlSessionTemplete);
    }

    public  List<PortalBeanCurdImgListVo> getPageList(PortalBeanCurdImgListCondition portalBeanCurdImgListCondition){
        return portalBeanCurdImgListMapper.getPageList(portalBeanCurdImgListCondition);
    }

    public  Integer getCount(PortalBeanCurdImgListCondition portalBeanCurdImgListCondition){
        return  portalBeanCurdImgListMapper.getCount(portalBeanCurdImgListCondition);
    }

    public  Integer update(PortalBeanCurdImgListCondition portalBeanCurdImgListCondition){
        return  portalBeanCurdImgListMapper.update(portalBeanCurdImgListCondition);
    }
    public  Integer delete(PortalBeanCurdImgListCondition portalBeanCurdImgListCondition){
        return  portalBeanCurdImgListMapper.delete(portalBeanCurdImgListCondition);
    }
    public Integer save(PortalBeanCurdImgListCondition portalBeanCurdImgListCondition){
        return  portalBeanCurdImgListMapper.save(portalBeanCurdImgListCondition);
    }
    public PortalBeanCurdImgListVo getImgList(PortalBeanCurdImgListCondition portalBeanCurdImgListCondition){
        return  portalBeanCurdImgListMapper.getImgList(portalBeanCurdImgListCondition);
    }

}
