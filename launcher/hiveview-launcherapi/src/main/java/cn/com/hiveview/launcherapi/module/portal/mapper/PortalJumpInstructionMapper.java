package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.AppVo;
import cn.com.hiveview.entity.module.portal.PortalJumpInstructionVo;
import cn.com.hiveview.launcherapi.module.portal.condition.AppCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalJumpInstructionCondition;

import java.util.List;

/**
 * Created by user on 2017/7/27.
 */
@SqlMapper
public interface PortalJumpInstructionMapper {

    List<PortalJumpInstructionCondition> getPageList(PortalJumpInstructionCondition condition);

    Integer add(PortalJumpInstructionCondition condition);

    Integer delete(PortalJumpInstructionCondition condition);

    Integer update(PortalJumpInstructionCondition condition);

    Integer getCount(PortalJumpInstructionCondition condition);

    PortalJumpInstructionCondition getOne(PortalJumpInstructionCondition condition);

    PortalJumpInstructionVo getActionById(PortalJumpInstructionCondition condition);

    List<PortalJumpInstructionVo> getInfoByStartApk(PortalJumpInstructionCondition condition);

}
