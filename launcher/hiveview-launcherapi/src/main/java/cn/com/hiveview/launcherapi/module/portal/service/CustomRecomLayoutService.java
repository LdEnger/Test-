package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.portal.CustomRecomLayoutVo;
import cn.com.hiveview.launcherapi.module.portal.condition.CustomRecomLayoutCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.CustomRecomLayoutDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2017/8/2.
 */
@Service
public class CustomRecomLayoutService {

    @Autowired
    private CustomRecomLayoutDao customRecomLayoutDao;

    public List<CustomRecomLayoutVo> getList(CustomRecomLayoutCondition condition){
        return  this.customRecomLayoutDao.getList(condition);
    }
    public List<CustomRecomLayoutVo> getListByPosition(CustomRecomLayoutCondition condition){
        return  this.customRecomLayoutDao.getListByPosition(condition);
    }

    public Integer getCount(CustomRecomLayoutCondition condition){
        return this.customRecomLayoutDao.getCount(condition);
    }

    public Integer save(CustomRecomLayoutCondition condition)throws  Exception{
        return  this.customRecomLayoutDao.save(condition);
    }

    public Integer delete(CustomRecomLayoutCondition condition)throws  Exception{
        return  this.customRecomLayoutDao.delete(condition);
    }

    public Integer updateLayoutTeam(CustomRecomLayoutCondition condition)throws Exception{
        return this.customRecomLayoutDao.updateLayoutTeam(condition);
    }

    public Integer update(List<CustomRecomLayoutCondition> layouts, int templeteId){
        int addFlag = 0;
        CustomRecomLayoutCondition layout = new CustomRecomLayoutCondition();
        layout.setTempleteId(templeteId);
        Integer delFlag = this.customRecomLayoutDao.delete(layout);
        for (CustomRecomLayoutCondition layoutPo2 : layouts) {
            layoutPo2.setTempleteId(templeteId);
            addFlag =customRecomLayoutDao.save(layoutPo2);
        }
        return addFlag;
    }
}
