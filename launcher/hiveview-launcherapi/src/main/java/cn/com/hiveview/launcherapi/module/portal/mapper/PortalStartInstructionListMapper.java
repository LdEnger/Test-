package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.PortalStartInstructionList;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalBeanCurdEditListCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalStartInstructionCondition;

import java.util.List;

/**
 * Created by admin on 2017/7/10.
 */
@SqlMapper
public interface PortalStartInstructionListMapper {
    Integer add(PortalStartInstructionCondition condition);
    Integer getPageCount(PortalStartInstructionCondition condition);
    Integer update(PortalStartInstructionCondition condition);
    Integer delete(PortalStartInstructionCondition condition);
    List<PortalStartInstructionList> getPageList(PortalStartInstructionCondition condition);
    List<PortalStartInstructionList> getComboboxList(PortalStartInstructionCondition condition);
    PortalStartInstructionList getList(PortalStartInstructionCondition condition);
    Integer getCountByString(PortalStartInstructionCondition condition);
    Integer getCountByCurdEdit(PortalBeanCurdEditListCondition condition);
}

