package lms.foodchainR.dao;

import java.util.ArrayList;
import java.util.List;

import lms.foodchainR.data.BillData;
import lms.foodchainR.data.CaseData;
import lms.foodchainR.data.OrderData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author 李梦思
 * @description 账单数据库辅助工具类
 * @createTime 2013-3-20
 */
public class Bill_DBHelper extends Base_DBHelper {

	private static int VERSION = 1;
	private final String BILLDATA = "billData";
	private final String ORDERDATA = "orderData";

	public Bill_DBHelper(Context context) {
		super(context, "fcr_bill.db", null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		this.db = db;
		createTable();
	}

	/** 生成表 */
	private void createTable() {
		if (db == null) {
			db = getWritableDatabase();
		}
		db.execSQL(createBillDataTable());
		db.execSQL(createOrderDataTable());
	}

	/** 生成账单表 */
	private String createBillDataTable() {
		return CREATE + BILLDATA + " (" + "billId" + AUTO_KEY
				+ "customerId varchar," + "createTime varchar,"
				+ "state integer," + "tip float," + "discount float,"
				+ "cost float" + ")";
	}

	/** 生成订单表 */
	private String createOrderDataTable() {
		return CREATE + ORDERDATA + " (" + "orderId" + AUTO_KEY
				+ "billId integer," + "caseId varchar," + "createTime varchar,"
				+ "state integer," + "type integer," + "remark varchar"
				+ ",cookerId integer" + ",waiterId integer" + ",count integer"
				+ ")";
	}

	/** 获取帐单 */
	public boolean getBillData(BillData b) {
		Cursor cursor = null;
		try {
			db = getReadableDatabase();
			selectArgs = new String[] { b.customer.id + "" };
			cursor = db.query(BILLDATA, null, "customerId=?", selectArgs, null,
					null, null);
			if (cursor.moveToNext()) {
				b.billId = cursor.getInt(cursor.getColumnIndex("billId"));
				b.customer.id = cursor.getInt(cursor
						.getColumnIndex("customerId"));
				b.createTime = cursor.getString(cursor
						.getColumnIndex("createTime"));
				b.state = cursor.getInt(cursor.getColumnIndex("state"));
				b.cost = cursor.getFloat(cursor.getColumnIndex("cost"));
				b.discount = cursor.getFloat(cursor.getColumnIndex("discount"));
				b.tip = cursor.getInt(cursor.getColumnIndex("tip"));
				return true;
			} else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
	}

	/** 获取订单 */
	public boolean getOrderData(BillData b) {
		Cursor cursor = null;
		try {
			db = getReadableDatabase();
			List<CaseData> list = new ArrayList<CaseData>();
			selectArgs = new String[] { b.billId + "" };
			cursor = db.query(ORDERDATA, null, "billId=?", selectArgs, null,
					null, null);
			if (cursor != null) {
				while (cursor.moveToNext()) {

				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
	}

	/** 获取订单 */
	public boolean getOrderList(ArrayList<OrderData> list) {
		Cursor cursor = null;
		try {
			db = getReadableDatabase();
			cursor = db.query(ORDERDATA, null, null, null, null, null,
					"orederId");
			if (cursor != null) {
				while (cursor.moveToNext()) {
					// TODO
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
	}

	/** 创建账单 */
	public boolean createBillData(BillData b) {
		if (db == null)
			db = getWritableDatabase();
		try {
			ContentValues values = new ContentValues();
			values.put("customerId", b.customer.id);
			values.put("createTime", b.createTime);
			db.insert(BILLDATA, null, values);
			createOrderData(b);
			db.setTransactionSuccessful();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			db.endTransaction();
		}
	}

	/** 创建订单 */
	private void createOrderData(BillData b) {
		try {
			for (OrderData c : b.getOrder()) {
				ContentValues values = new ContentValues();
				values.put("caseId", c.caseId);
				values.put("billId", b.billId);
				values.put("createTime", c.createTime);
				values.put("state", c.state);
				values.put("type", c.type);
				values.put("remark", c.remark);
				values.put("waiterId", c.waiterId);
				values.put("cookerId", c.cookerId);
				values.put("count", c.count);
				db.insert(ORDERDATA, null, values);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 删除账单 */
	public boolean deleteBill(BillData b) {
		db = getWritableDatabase();
		selectArgs = new String[] { b.billId + "" };
		try {
			db.delete(BILLDATA, "billId=?", selectArgs);
			db.delete(ORDERDATA, "billId=?", selectArgs);
			db.setTransactionSuccessful();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			db.endTransaction();
		}
	}

	/** 删除订单 */
	public boolean deleteOrder(OrderData c) {
		db = getWritableDatabase();
		selectArgs = new String[] { c.orderId + "" };
		try {
			db.delete(ORDERDATA, "oiderId=?", selectArgs);
			db.setTransactionSuccessful();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			db.endTransaction();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
