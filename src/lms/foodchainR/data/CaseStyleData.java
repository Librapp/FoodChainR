package lms.foodchainR.data;

import java.util.List;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author 李梦思
 * @description 菜单分类数据类
 * @createTime 2013-3-26
 * @version 1.0
 * 
 */
@DatabaseTable(tableName = "tb_casestyle")
public class CaseStyleData {
	/** 类型Id */
	@DatabaseField(id = true)
	public int id;
	/** 名称 */
	@DatabaseField
	public String name;
	List<CaseData> list;

	public CaseStyleData() {

	}

	public CaseStyleData(int styleId, String name) {
		this.id = styleId;
		this.name = name;
	}

	public void setList(List<CaseData> list) {
		this.list = list;
	}

	public List<CaseData> getList() {
		return list;
	}
}
