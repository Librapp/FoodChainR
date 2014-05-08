package lms.foodchainR.data;

/**
 * 
 * @author Luke
 * @description 排号数据类
 * @createTime 2014/4/28
 */
public class ListData {
	/** id */
	public int listId;
	/** 序号 */
	public int number;
	/** 取号时间 */
	public String comeTime;
	/** 订单Id */
	public String billId;
	/** 等待的桌位类型 */
	public String styleId;
	/** 顾客 */
	public CustomerData customer;
}
