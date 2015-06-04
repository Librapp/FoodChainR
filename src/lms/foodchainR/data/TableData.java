package lms.foodchainR.data;

import java.util.ArrayList;
/**
 * 
 * @author 李梦思
 * @version 1.0
 * @createTime 2012-11-17
 * @description 桌子数据类
 * 
 */
import java.util.List;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tb_table")
public class TableData {
	// 桌号
	@DatabaseField(id = true)
	public int id;
	public static final int DIRTY = 1, AVAILIABLE = 2, OCCUPY = 3, HAVING = 4,
			WAITING = 5, PAY = 6;
	// 状态
	@DatabaseField
	public int state = AVAILIABLE;
	/** 类别 */
	@DatabaseField
	public int type = 0;
	// 类型
	@DatabaseField
	public int styleId;
	// 座位数
	@DatabaseField
	public int seatCount = 4;
	// 预定时间
	@DatabaseField
	public String bookTime = "";
	/** 顾客Id */
	@DatabaseField
	public int customerId;
	// 服务生Id
	@DatabaseField
	public int waiterId = 0;
	/** 空座位数 */
	public int freeSeat = 4;
	/** 第一个来的顾客 */
	public CustomerData customer;
	/** 座位 */
	private List<SeatData> seat = new ArrayList<SeatData>();
	private static TableData current;

}
