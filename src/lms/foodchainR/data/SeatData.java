package lms.foodchainR.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author 李梦思
 * @version 1.0
 * @createTime 2012-11-17
 * @description 座位数据类
 */
@DatabaseTable(tableName = "tb_seat")
public class SeatData {
	// 座位号
	@DatabaseField(id = true)
	public int id;
	public static final int DIRTY = 1, AVAILIABLE = 2, OCCUPY = 3, HAVING = 4,
			WAITING = 5, PAY = 6;
	@DatabaseField
	public int state = AVAILIABLE;
	@DatabaseField
	public int styleId;
	// 桌号
	@DatabaseField
	public int tableId;
	// 图标
	@DatabaseField
	public String icon;
	/** 顾客Id */
	@DatabaseField
	public int customerId;
	// 服务生Id
	@DatabaseField
	public int waiterId = 0;
	/** 顾客实体 */
	public CustomerData customer;

}
