package cn.com.hiveview.launcherapi.module.portal.dao;

import java.util.List;
import cn.com.hiveview.entity.module.portal.MergeVideoDataVo;
import cn.com.hiveview.launcherapi.module.portal.condition.MergeVideoData;
import cn.com.hiveview.launcherapi.module.portal.mapper.ProgramMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
@Repository("ProgramDao")
public class ProgramDao extends SqlSessionDaoSupport {
	@Autowired
	ProgramMapper programMapper;
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	@Resource(name = "jdbcTemplate")
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	public Integer getPageCount(MergeVideoData condition){
		return programMapper.getPageCount(condition);
	}
	public List<MergeVideoDataVo> getPageList(MergeVideoData condition){
		return programMapper.getPageList(condition);
	}
}
