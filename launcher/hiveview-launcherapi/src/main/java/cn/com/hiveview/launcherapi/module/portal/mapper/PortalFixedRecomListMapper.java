package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.PortalFiexdRecomListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalFixedRecomListCondition;

import java.util.List;

/**
 * Created by user on 2017/7/14.
 */
@SqlMapper
public interface PortalFixedRecomListMapper {
    List<PortalFiexdRecomListVo> getList(PortalFixedRecomListCondition portalFiexdRecomListCondition);
    Integer getCount(PortalFixedRecomListCondition portalFiexdRecomListCondition);
    Integer save(PortalFixedRecomListCondition portalFiexdRecomListCondition);
    Integer delete(PortalFixedRecomListCondition portalFiexdRecomListCondition);
    Integer update(PortalFixedRecomListCondition portalFiexdRecomListCondition);
    List<PortalFiexdRecomListVo> getTypeList(PortalFixedRecomListCondition portalFiexdRecomListCondition);
    Integer updateEffective(PortalFixedRecomListCondition portalFiexdRecomListCondition);
    PortalFiexdRecomListVo getFixedRecomListOne(PortalFixedRecomListCondition portalFiexdRecomListCondition);
}
