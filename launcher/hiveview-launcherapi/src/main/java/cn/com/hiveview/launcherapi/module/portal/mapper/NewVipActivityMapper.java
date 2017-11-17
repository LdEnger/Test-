package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.NewVipActivityVo;
import cn.com.hiveview.launcherapi.module.portal.condition.NewVipActivityCondition;

import java.util.List;

/**
 * Created by admin on 2017/10/13.
 */
@SqlMapper
public interface NewVipActivityMapper {
    List<NewVipActivityVo> getPageList(NewVipActivityCondition condition);
    Integer getPageCount(NewVipActivityCondition condition);
}
