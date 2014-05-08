package lms.foodchainR.data;

import java.util.List;

/**
 * 
 * @author 李梦思
 * @version 1.0
 * @createTime 2012-11-17
 * @description 账单数据类
 * 
 */
public class BillData {
	public static final int UNPAID = 0;
	public static final int PAID = 1;
	/** 账单Id */
	public int billId;
	/** 创建时间 */
	public String createTime = "2012-11-17 20:45";
	/** 状态:UNPAID未付账,PAID已付帐 */
	public int state = UNPAID;
	/** 小费 */
	public float tip = 10;
	/** 折扣 */
	public float discount = 0.88f;
	/** 消费 */
	public float cost = 99.1f;
	/** 顾客 */
	public CustomerData customer;
	public static BillData current = new BillData();

	private List<OrderData> order;

	public List<OrderData> getOrder() {
		return order;
	}

	public void setOrder(List<OrderData> order) {
		this.order = order;
	}
}
