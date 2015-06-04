package lms.foodchainR.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 
 * @author Luke
 * @description 订单数据类
 * @createTime 2014/5/4
 */
@DatabaseTable(tableName = "tb_order")
public class OrderData {
	/** 订单Id */
	@DatabaseField(id = true)
	public int id;
	public static final int HERE = 0, AWAY = 1, PACK = 2;
	/** 类型:HERE即食AWAY外卖PACK带走 */
	@DatabaseField
	public int type = HERE;
	/** 菜品Id */
	@DatabaseField
	public int caseId;
	/** 顾客Id */
	@DatabaseField
	public int customerId;
	/** 厨师Id */
	@DatabaseField
	public int cookerId;
	/** 服务员Id(可选) */
	@DatabaseField
	public int waiterId;
	/** 份数 */
	@DatabaseField
	public int count = 1;
	/** 下单时间 */
	@DatabaseField
	public String createTime;
	/** 备注(可选) */
	@DatabaseField
	public String remark;
	/** 菜名 */
	@DatabaseField
	public String name;
	/** 状态 */
	@DatabaseField
	public int state;
	/** 价格 */
	@DatabaseField
	public float price;
	/** 对应菜品 */
	public CaseData caseData;
}
