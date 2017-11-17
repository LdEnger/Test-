package cn.com.hiveview.launcherapi.module.portal.dao;

import cn.com.hiveview.entity.module.portal.PortalCustomizeApkVersionList;
import cn.com.hiveview.launcherapi.module.portal.condition.PortalCustomizeApkVersionCondition;
import cn.com.hiveview.launcherapi.module.portal.mapper.PortalCustomizeApkVersionListMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */
@Repository("PortalCustomizeApkVersionListDao")
public class PortalCustomizeApkVersionListDao extends SqlSessionDaoSupport {
    @Autowired
    PortalCustomizeApkVersionListMapper portalCustomizeApkVersionListMapper;
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Resource(name = "jdbcTemplate")
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    public Integer add( PortalCustomizeApkVersionCondition condition){
        return portalCustomizeApkVersionListMapper.add(condition);
    }
    public List<PortalCustomizeApkVersionList> getPageList(PortalCustomizeApkVersionCondition condition){
        return portalCustomizeApkVersionListMapper.getPageList(condition);
    }
    public Integer getPageCount( PortalCustomizeApkVersionCondition condition){
        return portalCustomizeApkVersionListMapper.getPageCount(condition);
    }
    public Integer delete( PortalCustomizeApkVersionCondition condition){
        return portalCustomizeApkVersionListMapper.delete(condition);
    }
    public Integer update( PortalCustomizeApkVersionCondition condition){
        return portalCustomizeApkVersionListMapper.update(condition);
    }
    public PortalCustomizeApkVersionList getApkPageName(PortalCustomizeApkVersionCondition condition){
        return portalCustomizeApkVersionListMapper.getApkPageName(condition);
    }
    public Integer getCountByApkId( PortalCustomizeApkVersionCondition condition){
        return portalCustomizeApkVersionListMapper.getCountByApkId(condition);
    }
    public Integer getCountByVersion( PortalCustomizeApkVersionCondition condition){
        return portalCustomizeApkVersionListMapper.getCountByVersion(condition);
    }
    public String getMaxVersion(PortalCustomizeApkVersionCondition condition){
        /*List<String> list=portalCustomizeApkVersionListMapper.getMaxVersion(condition);
        if(list.size()==0){
            return null;
        }
        List<String[]> newList=new ArrayList<>();
        for (String str:list) {
            String[] strs = str.split("\\.");
            System.out.println(strs.length);
            newList.add(strs);
        }
        System.out.println(list.toString());
        int maxIndex = 0;

        for(int i = 1;i<newList.size();i++){
            String[] strsa = newList.get(i);
            String[] strsb = newList.get(maxIndex);
            int la = strsa.length;
            int lb = strsb.length;
            for(int j=0;j< Math.max(la, lb);j++){
                int va = 0;
                int vb = 0;
                if (j < la) {
                    va = Integer.parseInt(strsa[j]);
                }
                if (j < lb) {
                    vb = Integer.parseInt(strsb[j]);
                }
                if (va < vb) {
                    break;
                } else if (va > vb){
                    maxIndex = i;
                    break;
                }
            }
        }

        return list.get(maxIndex);*/
        return portalCustomizeApkVersionListMapper.getMaxVersion(condition);
    }
}
