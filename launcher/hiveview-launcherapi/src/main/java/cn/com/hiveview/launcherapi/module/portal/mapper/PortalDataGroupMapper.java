package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.PortalDataGroupListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalDataGroupListCondition;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

/**
 * Created by user on 2017/10/9.
 */
@SqlMapper
public interface PortalDataGroupMapper  {
    List<PortalDataGroupListVo> getPage(PortalDataGroupListCondition condition);
    Integer getCount(PortalDataGroupListCondition condition);
    Integer save(PortalDataGroupListCondition condition);
    Integer update(PortalDataGroupListCondition condition);
    Integer delete(PortalDataGroupListCondition condition);
    Integer updateEffective(PortalDataGroupListCondition condition);
    PortalDataGroupListVo getGroupInfo(PortalDataGroupListCondition condition);
    PortalDataGroupListVo getGroupVo(PortalDataGroupListCondition condition);
}
