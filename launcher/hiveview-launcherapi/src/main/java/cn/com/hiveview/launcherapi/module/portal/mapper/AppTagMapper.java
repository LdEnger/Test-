package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.AppTagListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.AppTagListConditon;

import java.util.List;

/**
 * Created by user on 2017/10/17.
 */
@SqlMapper
public interface AppTagMapper {
    List<AppTagListVo> getPage(AppTagListConditon conditon);
    Integer getCount(AppTagListConditon conditon);
}
