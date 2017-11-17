package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.PortalNotStartInstructionListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalNotStartInstructionListConditon;
import cn.com.hiveview.launcherapi.module.portal.mapper.PortalNotStartInstructionMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by user on 2017/10/13.
 */
@Repository("/PortalNotStartInstructionDao")
public class PortalNotStartInstructionDao extends SqlSessionDaoSupport{

    @Autowired
    PortalNotStartInstructionMapper portalNotStartInstructionMapper;

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate){
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public List<PortalNotStartInstructionListVo > getPage(PortalNotStartInstructionListConditon conditon){
        return portalNotStartInstructionMapper.getPage(conditon);
    }
    public Integer getCount(PortalNotStartInstructionListConditon conditon){
        return  portalNotStartInstructionMapper.getCount(conditon);
    }
    public Integer save(PortalNotStartInstructionListConditon conditon){
        return  portalNotStartInstructionMapper.save(conditon);
    }
    public Integer delete(PortalNotStartInstructionListConditon conditon){
        return portalNotStartInstructionMapper.delete(conditon);
    }
    public Integer update(PortalNotStartInstructionListConditon conditon){
        return  portalNotStartInstructionMapper.update(conditon);
    }
    public Integer updateEffice(PortalNotStartInstructionListConditon conditon){
        return  portalNotStartInstructionMapper.updateEffice(conditon);
    }


    public List<PortalNotStartInstructionListVo > getPageList(PortalNotStartInstructionListConditon conditon){
        return portalNotStartInstructionMapper.getPageList(conditon);
    }
    public Integer getPageCount(PortalNotStartInstructionListConditon conditon){
        return  portalNotStartInstructionMapper.getPageCount(conditon);
    }
}
