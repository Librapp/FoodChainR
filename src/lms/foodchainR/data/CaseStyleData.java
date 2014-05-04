package lms.foodchainR.data;

import java.util.List;

/**
 * @author 李梦思
 * @description 菜单分类数据类
 * @createTime 2013-3-26
 * @version 1.0
 * 
 */
public class CaseStyleData {
	/** 类型Id */
	public int styleId;
	/** 名称 */
	public String name;
	List<CaseData> list;

	public CaseStyleData() {

	}

	public CaseStyleData(int styleId, String name) {
		this.styleId = styleId;
		this.name = name;
	}

	public void setList(List<CaseData> list) {
		this.list = list;
	}

	public List<CaseData> getList() {
		return list;
	}
}
