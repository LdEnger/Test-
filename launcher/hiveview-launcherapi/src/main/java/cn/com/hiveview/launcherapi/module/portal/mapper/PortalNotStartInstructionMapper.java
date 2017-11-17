package cn.com.hiveview.launcherapi.module.portal.mapper;

import cn.com.hiveview.core.mybatis.SqlMapper;
import cn.com.hiveview.entity.module.portal.PortalNotStartInstructionListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalNotStartInstructionListConditon;

import java.util.List;

/**
 * Created by user on 2017/10/13.
 */
@SqlMapper
public interface PortalNotStartInstructionMapper {
    List<PortalNotStartInstructionListVo> getPage(PortalNotStartInstructionListConditon conditon);
    Integer getCount(PortalNotStartInstructionListConditon conditon);
    Integer save(PortalNotStartInstructionListConditon conditon);
    Integer update(PortalNotStartInstructionListConditon conditon);
    Integer delete(PortalNotStartInstructionListConditon conditon);
    List<PortalNotStartInstructionListVo> getPageList(PortalNotStartInstructionListConditon conditon);
    Integer getPageCount(PortalNotStartInstructionListConditon conditon);
    Integer updateEffice(PortalNotStartInstructionListConditon conditon);

}
