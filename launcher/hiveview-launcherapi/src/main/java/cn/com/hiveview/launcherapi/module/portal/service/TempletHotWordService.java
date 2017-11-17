package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.portal.TempletHotWordList;
import cn.com.hiveview.launcherapi.module.portal.condition.TempletHotWordCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.TempletHotWordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2017/8/1.
 */
@Service
public class TempletHotWordService {
    @Autowired
    private TempletHotWordDao templetHotWordDao;

    public List<TempletHotWordList> getList(TempletHotWordCondition condition){
        return  templetHotWordDao.getList(condition);
    }
}
