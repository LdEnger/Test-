package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.LauncherHomeApiVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by user on 2017/7/26.
 */
@SqlMapper
public interface PortalBeanCurdMapper {
    List<LauncherHomeApiVo> getLauncherHome(@Param("curdId")Integer curdId);
}
