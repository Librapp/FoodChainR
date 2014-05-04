package lms.foodchainR.data;

/**
 * 
 * @author Luke
 * @description 订单数据类
 * @createTime 2014/5/4
 */
public class OrderData {
	public static final int HERE = 0;
	public static final int AWAY = 1;
	public static final int PACK = 2;
	/** 类型:HERE即食AWAY外卖PACK带走 */
	public int type = HERE;
	/** 订单Id */
	public int orderId;
	/** 菜品Id */
	public int caseId;
	/** 账单Id */
	public int billId;
	/** 厨师Id */
	public int cookerId;
	/** 服务员Id(可选) */
	public int waiterId;
	/** 份数 */
	public int count = 1;
	/** 下单时间 */
	public String createTime;
	/** 备注(可选) */
	public String remark;
}
