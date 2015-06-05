package lms.foodchainR.data;

import java.util.List;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 
 * @author 李梦思
 * @version 1.0
 * @createTime 2012-11-17
 * @description 账单数据类
 * 
 */
@DatabaseTable(tableName = "tb_bill")
public class BillData {
	/** 账单Id */
	@DatabaseField(id = true)
	public int id;
	/** 顾客Id */
	@DatabaseField
	public int customerId;
	/** 创建时间 */
	@DatabaseField
	public String createTime = "2012-11-17 20:45";
	public static final int UNPAID = 0, PAID = 1;
	/** 状态:UNPAID未付账,PAID已付帐 */
	@DatabaseField
	public int state = UNPAID;
	/** 折扣 */
	@DatabaseField
	public float discount = 0.88f;
	/** 消费 */
	@DatabaseField
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
