package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.launcherapi.module.portal.condition.PortalSysAppIconsListCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.UnbundlingCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.UnbundlingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 2017/8/17.
 */
@Repository("unbundlingDao")
public class UnbundlingDao {
    @Autowired
    private UnbundlingMapper unbundlingMapper;
    public Integer deleteScreentRecommendContent(UnbundlingCondition condition){
        return unbundlingMapper.deleteScreentRecommendContent(condition);
    }
    public Integer deletefixedRecomContent(UnbundlingCondition condition){
        return unbundlingMapper.deletefixedRecomContent(condition);
    }
    public Integer deleteCustomRecomContent(UnbundlingCondition condition){
        return unbundlingMapper.deleteCustomRecomContent(condition);
    }
    public Integer deleteBackupsContent(UnbundlingCondition condition){
        return unbundlingMapper.deleteBackupsContent(condition);
    }
}
