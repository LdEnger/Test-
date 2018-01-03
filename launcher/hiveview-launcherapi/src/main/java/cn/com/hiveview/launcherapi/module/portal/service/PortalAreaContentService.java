package cn.com.hiveview.launcherapi.module.portal.service;

import cn.com.hiveview.entity.module.common.ScriptPage;
import cn.com.hiveview.entity.module.portal.PortalAreaAdministrationListEntity;
import cn.com.hiveview.entity.module.portal.PortalAreaAdministrationListVo;
import cn.com.hiveview.entity.module.portal.PortalAreaContentListVo;
import cn.com.hiveview.launcherapi.module.portal.condition.NewContentChanCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalAreaAdminirationListCondition;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalAreaContentListCondition;
import cn.com.hiveview.launcherapi.module.portal.dao.NewContentChanDao;
import cn.com.hiveview.launcherapi.module.portal.dao.PortalAreaContentDao;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by user on 2017/10/8.
 */
@Service
public class PortalAreaContentService {

    @Autowired
    private PortalAreaContentDao portalAreaContentDao;
    @Autowired
    private NewContentChanDao newContentChanDao;

    public ScriptPage<PortalAreaContentListVo> getPage(PortalAreaContentListCondition condition) throws Exception {
        ScriptPage<PortalAreaContentListVo> scriptPage = new ScriptPage<PortalAreaContentListVo>();
        condition.setPageIndex(condition.getPage());
        condition.setPageSize(condition.getRows());
        List<PortalAreaContentListVo> rows = this.portalAreaContentDao.getPage(condition);
        for(int i=0;i<rows.size();i++){
            //定义传参的Chan
            NewContentChanCondition chanCondition=new NewContentChanCondition();
            if(rows.get(i).getContentId()!=null){
                chanCondition.setProgramsetId(rows.get(i).getContentId());
                //定义接收返回数据的Chan
                NewContentChanCondition chanCondition2=new NewContentChanCondition();
                chanCondition2=newContentChanDao.getOneChan(chanCondition);
                String relateContent=chanCondition2.getAlbumName();
                rows.get(i).setRelateContent(relateContent);
            }
        }
        Integer total = this.portalAreaContentDao.getCount(condition);
        scriptPage.setRows(rows);
        scriptPage.setTotal(total);
        return scriptPage;
    }

    public  Integer delete(PortalAreaContentListCondition condition)throws Exception{
        if(condition.getId()==null && condition.getAreaId()==null){
            return 0;
        }
        if(condition.getSeqIsTop()==1){
            PortalAreaContentListVo content=portalAreaContentDao.getMaxContent(condition);
            if(content.getId()!=null){
                PortalAreaContentListCondition content2=new PortalAreaContentListCondition();
                content2.setId(content.getId());
                content2.setSeqIsTop(1);
                portalAreaContentDao.update(content2);
                Integer result= this.portalAreaContentDao.delete(condition);
                return result;
            }
        }
        return this.portalAreaContentDao.delete(condition);
    }

    public Integer save(PortalAreaContentListCondition condition)throws Exception {
        Date date=new Date();
        condition.setCreateTime(date);
        condition.setUpdateTime(date);
        Integer result = 0;
        PortalAreaContentListVo minTab = portalAreaContentDao.getTopSeq(condition);
        PortalAreaContentListCondition condition2 = new PortalAreaContentListCondition();
        if (minTab == null) {
            condition.setSeq(100);
            condition.setSeqIsTop(1);
            if (portalAreaContentDao.getCount(condition) >= 1) {
                return -1;
            }
            result = this.portalAreaContentDao.save(condition);
        } else {
            Integer minSeq = minTab.getSeq();
            condition.setSeq(minSeq - 1);
            condition.setSeqIsTop(1);
            condition2.setId(minTab.getId());
            condition2.setSeqIsTop(0);
            if (portalAreaContentDao.getCount(condition) >= 1) {
                return -1;
            }
            result = this.portalAreaContentDao.save(condition);
            if (result == 0) {
                throw new RuntimeException();
            }
            result = this.portalAreaContentDao.update(condition2);
        }
        return result;
    }
    public Integer update(PortalAreaContentListCondition condition)throws Exception{
        Date date=new Date();
        condition.setUpdateTime(date);
        return this.portalAreaContentDao.update(condition);
    }

    public Integer updateMoveUp(PortalAreaContentListCondition condition)throws Exception{
        Date date=new Date();
        condition.setUpdateTime(date);
        Integer result=0;
        //当前Content的上面的
        PortalAreaContentListVo minContent = portalAreaContentDao.getMinContent(condition);
        //condition1:之前为上面的Tab
        PortalAreaContentListCondition condition1=new PortalAreaContentListCondition();
        if(minContent.getSeqIsTop()==1){
            condition1.setId(minContent.getId());
            condition1.setSeq(condition.getSeq());
            condition1.setSeqIsTop(0);
            result = portalAreaContentDao.update(condition1);
            if(result==0){
                throw new RuntimeException();
            }
            condition.setSeq(minContent.getSeq());
            condition.setSeqIsTop(1);
            result = portalAreaContentDao.update(condition);
        }else {
            condition1.setId(minContent.getId());
            condition1.setSeq(condition.getSeq());
            result = portalAreaContentDao.update(condition1);
            if(result == 0){
                throw new RuntimeException();
            }
            condition.setSeq(minContent.getSeq());
            result = portalAreaContentDao.update(condition);
        }
        return result;
    }

    public Integer updateMoveDown(PortalAreaContentListCondition condition) throws Exception{
        Date date=new Date();
        condition.setUpdateTime(date);
        Integer result=0;
        PortalAreaContentListVo maxContent= portalAreaContentDao.getMaxContent(condition);
        //condition1:之前为下面的Tab
        PortalAreaContentListCondition condition1=new PortalAreaContentListCondition();
        if(condition.getSeqIsTop()==1){
            condition1.setId(maxContent.getId());
            condition1.setSeq(condition.getSeq());
            condition1.setSeqIsTop(1);
            result = portalAreaContentDao.update(condition1);
            if(result==0){
                throw new RuntimeException();
            }
            condition.setSeq(maxContent.getSeq());
            condition.setSeqIsTop(0);
            result = portalAreaContentDao.update(condition);
        }else {
            condition1.setId(maxContent.getId());
            condition1.setSeq(condition.getSeq());
            result = portalAreaContentDao.update(condition1);
            if(result==0){
                throw new RuntimeException();
            }
            condition.setSeq(maxContent.getSeq());
            result = portalAreaContentDao.update(condition);
        }
        return result;
    }
    public Integer updateMoveTop(PortalAreaContentListCondition condition) throws Exception{
        Date date=new Date();
        condition.setUpdateTime(date);
        Integer result=0;
        PortalAreaContentListVo topCondition=portalAreaContentDao.getTopSeq(condition);
        //condition1:之前为最顶的Tab
        PortalAreaContentListCondition condition1=new PortalAreaContentListCondition();
        condition1.setId(topCondition.getId());
        condition1.setSeqIsTop(0);
        result = portalAreaContentDao.update(condition1);
        if(result==0){
            throw new RuntimeException();
        }
        condition.setSeqIsTop(1);
        int seq=topCondition.getSeq()-1;
        condition.setSeq(seq);
        result = portalAreaContentDao.update(condition);
        return result;
    }

    public List<PortalAreaAdministrationListEntity> getAreaContentList(PortalAreaContentListCondition condition)throws Exception{
        return this.portalAreaContentDao.getAreaContentList(condition);
    }
}
