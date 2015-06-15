package lms.foodchainR.data;

import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author 李梦思
 * @version 1.0
 * @createTime 2012-11-17
 * @description 桌子类型数据类
 * 
 */
@DatabaseTable(tableName = "tb_tablestyle")
public class TableStyleData {
	@DatabaseField(generatedId = true)
	public int id;
	@DatabaseField
	public String name;
	// 有多少张桌子
	@DatabaseField
	public int tableCount = 10;
	// 桌子有多少座位
	@DatabaseField
	public int seatCount = 4;
	// 图标
	@DatabaseField
	public String icon = "";
	// 图片
	@DatabaseField
	public String pic = "";
	// 桌子列表
	private List<TableData> table = new ArrayList<TableData>();
	// 是否为新建
	public boolean isNew = false;

	public void setTable(List<TableData> table) {
		this.table = table;
	}

	public List<TableData> getTable() {
		return table;
	}
}
