package lms.foodchainR.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李梦思
 * @version 1.0
 * @createTime 2012-11-17
 * @description 桌子类型数据类
 * 
 */
public class TableStyleData {
	// 有多少张桌子
	public int count = 10;
	// 桌子有多少座位
	public int seatCount = 4;
	// 类型Id
	public String id = "A1";
	// 图标
	public String icon = "";
	// 图片
	public String pic = "";
	// 桌子列表
	private List<TableData> table = new ArrayList<TableData>();
	// 是否为新建
	public boolean isNew = false;

	public TableStyleData(String id, int seatCount, int count) {
		this.id = id;
		this.seatCount = seatCount;
		this.count = count;
	}

	public TableStyleData() {

	}

	public void setTable(List<TableData> table) {
		this.table = table;
	}

	public List<TableData> getTable() {
		return table;
	}
}
