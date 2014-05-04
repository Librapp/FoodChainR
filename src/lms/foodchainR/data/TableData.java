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

public class TableData {
	public static final int DIRTY = 1;
	public static final int AVAILIABLE = 2;
	public static final int OCCUPY = 3;
	public static final int HAVING = 4;
	public static final int WAITING = 5;
	public static final int PAY = 6;
	// 服务生Id
	public int waiterId;
	// 座位数
	public int seatCount = 4;
	// 状态
	public int state = AVAILIABLE;
	/** 座位 */
	private List<SeatData> seat = new ArrayList<SeatData>();
	// 桌号
	public String tableId;
	// 类型
	public String styleId;
	// 预定时间
	public String bookTime;
	/** 顾客Id */
	public int customerId;
	private static TableData current;
	/** 空座位数 */
	public int freeSeat = 4;
	/** 第一个来的顾客 */
	public CustomerData customer;

	public TableData() {

	}

	public TableData(TableStyleData ts, int i) {
		String id = i + "";
		if (i < 10) {
			id = "00" + id;
		} else if (i < 100) {
			id = "0" + id;
		}
		this.tableId = ts.styleId + id;
		this.styleId = ts.styleId;
		this.seatCount = ts.seatCount;
	}

	public static TableData current() {
		if (current == null) {
			current = new TableData();
		}
		return current;
	}

	public void setSeat(List<SeatData> seat) {
		this.seat = seat;
	}

	public List<SeatData> getSeat() {
		return seat;
	}
}
