package lms.foodchainR.data;

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
	public int billId = 0;
	/** 顾客Id */
	public int customerId = 0;
	/** 创建时间 */
	public String createTime = "2012-11-17 20:45";
	/** 状态:UNPAID未付账,PAID已付帐 */
	public int state = 0;
	/** 小费 */
	public int tip = 10;
	/** 折扣 */
	public float discount = 0.88f;
	/** 消费 */
	public float cost = 99.1f;

	public static BillData current = new BillData();
}
