package cn.com.hiveview.entity.module.common;

import cn.com.hiveview.entity.module.portal.PortalScreenRecommendList;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Data
public class ScriptPage<T> {
	private Object rows;
	//private List<T> rows = new ArrayList<T>();
	private Integer total = -1;
	public int pageIndex ;
	public int pageSize;
}
