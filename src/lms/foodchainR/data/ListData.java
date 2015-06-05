package lms.foodchainR.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 
 * @author Luke
 * @description 排号数据类
 * @createTime 2014/4/28
 */
@DatabaseTable(tableName = "tb_list")
public class ListData {
	/** id */
	@DatabaseField(id = true)
	public int id;
	/** 序号 */
	@DatabaseField
	public int number;
	/** 取号时间 */
	@DatabaseField
	public String comeTime;
	/** 订单Id */
	@DatabaseField
	public int billId;
	/** 等待的桌位类型 */
	@DatabaseField
	public int tableStyleId;
	/** 顾客Id */
	@DatabaseField
	public int customerId;
	/** 顾客 */
	public CustomerData customer;
}
