package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.LogoLicenseManageListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.LogoLicenseManageListCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.LogoLicenseManageListMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by user on 2017/7/5.
 */
@Repository("LogoLicenseManageListDao")
public class LogoLicenseManageListDao extends SqlSessionDaoSupport {

    @Autowired
    LogoLicenseManageListMapper logoLicenseManageListMapper;

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public List<LogoLicenseManageListVo> getList(LogoLicenseManageListCondition logoLicenseManageListCondition){
        return logoLicenseManageListMapper.getList(logoLicenseManageListCondition);
    }

    public Integer getCount(LogoLicenseManageListCondition logoLicenseManageListCondition){
        return logoLicenseManageListMapper.getCount(logoLicenseManageListCondition);
    }

    public Integer save(LogoLicenseManageListCondition logoLicenseManageListCondition){
        return logoLicenseManageListMapper.save(logoLicenseManageListCondition);
    }

    public Integer delete(LogoLicenseManageListCondition logoLicenseManageListCondition){
        return logoLicenseManageListMapper.delete(logoLicenseManageListCondition);
    }

    public Integer update(LogoLicenseManageListCondition logoLicenseManageListCondition){
        return logoLicenseManageListMapper.update(logoLicenseManageListCondition);
    }

    public LogoLicenseManageListVo getLogo(LogoLicenseManageListCondition logoLicenseManageListCondition){
        return logoLicenseManageListMapper.getLogo(logoLicenseManageListCondition);
    }

    public LogoLicenseManageListVo getLogoInfoApi(Integer templetId){
        return logoLicenseManageListMapper.getLogoInfoApi(templetId);
    }

    public List<LogoLicenseManageListVo> getLogoList(LogoLicenseManageListCondition logoLicenseManageListCondition){
        return logoLicenseManageListMapper.getLogoList(logoLicenseManageListCondition);
    }
}
