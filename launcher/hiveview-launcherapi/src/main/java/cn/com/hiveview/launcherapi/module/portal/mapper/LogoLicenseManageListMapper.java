package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.LogoLicenseManageListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.LogoLicenseManageListCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by user on 2017/7/5.
 */

@SqlMapper
public interface LogoLicenseManageListMapper {
    List<LogoLicenseManageListVo> getList(LogoLicenseManageListCondition logoLicenseManageListCondition);
    Integer getCount(LogoLicenseManageListCondition logoLicenseManageListCondition);
    Integer save(LogoLicenseManageListCondition logoLicenseManageListCondition);
    Integer delete(LogoLicenseManageListCondition logoLicenseManageListCondition);
    Integer update(LogoLicenseManageListCondition logoLicenseManageListCondition);
    LogoLicenseManageListVo getLogo(LogoLicenseManageListCondition logoLicenseManageListCondition);
    LogoLicenseManageListVo getLogoInfoApi(@Param("templetId")Integer templetId);
    List<LogoLicenseManageListVo> getLogoList(LogoLicenseManageListCondition logoLicenseManageListCondition);

}
