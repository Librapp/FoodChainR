package lms.foodchainR.dao;

import lms.foodchainR.FoodchainRApplication;
import lms.foodchainR.data.OrderData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Order_DBHelper extends Base_DBHelper {
	public Order_DBHelper(Context context) {
		super(context, "fcr_order.db", null, FoodchainRApplication.DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		createTable(db);
	}

	/**
	 * 生成表
	 * 
	 * @param db
	 */
	private void createTable(SQLiteDatabase db) {
		db.execSQL(createOrderDataTable());
	}

	/** 生成自定义类型表 */
	private String createOrderDataTable() {
		return CREATE + FoodchainRApplication.TABLE_ORDER + " ("
				+ FoodchainRApplication.ID + AUTO_KEY + ","
				+ FoodchainRApplication.CUSTOMER + INTEGER + ","
				+ FoodchainRApplication.CASE + INTEGER + ","
				+ FoodchainRApplication.COUNT + INTEGER + ","
				+ FoodchainRApplication.REMARK + TEXT + ","
				+ FoodchainRApplication.CREATETIME + TEXT + ","
				+ FoodchainRApplication.PRICE + FLOAT + ","
				+ FoodchainRApplication.COOKER + INTEGER + ","
				+ FoodchainRApplication.STATE + INTEGER + ","
				+ FoodchainRApplication.NAME + TEXT + ")";
	}

	public void insert(OrderData data) {
		ContentValues values = new ContentValues();
		values.put(FoodchainRApplication.CUSTOMER, data.customerId);
		values.put(FoodchainRApplication.CASE, data.caseId);
		values.put(FoodchainRApplication.COUNT, data.count);
		values.put(FoodchainRApplication.REMARK, data.remark);
		values.put(FoodchainRApplication.CREATETIME, data.createTime);
		values.put(FoodchainRApplication.PRICE, data.price);
		values.put(FoodchainRApplication.COOKER, data.cookerId);
		values.put(FoodchainRApplication.STATE, data.state);
		values.put(FoodchainRApplication.NAME, data.name);
		values.put(FoodchainRApplication.TYPE, data.type);
		getWritableDatabase();
		dbWrite.insert(FoodchainRApplication.TABLE_ORDER, null, values);
		dbWrite.close();
	}

	public void delete(OrderData data) {
		getWritableDatabase();
		dbWrite.delete(FoodchainRApplication.TABLE_ORDER, "where "
				+ FoodchainRApplication.ID + "=?",
				new String[] { data.id + "" });
		dbWrite.close();
	}

	public void update(OrderData data) {
		ContentValues values = new ContentValues();
		values.put(FoodchainRApplication.CUSTOMER, data.customerId);
		values.put(FoodchainRApplication.CASE, data.caseId);
		values.put(FoodchainRApplication.COUNT, data.count);
		values.put(FoodchainRApplication.REMARK, data.remark);
		values.put(FoodchainRApplication.CREATETIME, data.createTime);
		values.put(FoodchainRApplication.PRICE, data.price);
		values.put(FoodchainRApplication.COOKER, data.cookerId);
		values.put(FoodchainRApplication.STATE, data.state);
		values.put(FoodchainRApplication.NAME, data.name);
		values.put(FoodchainRApplication.TYPE, data.type);
		getWritableDatabase();
		dbWrite.update(FoodchainRApplication.TABLE_ORDER, values, "where "
				+ FoodchainRApplication.ID + "=?",
				new String[] { data.id + "" });
		dbWrite.close();
	}

	public OrderData getById(int id) {
		dbRead = getReadableDatabase();
		Cursor cursor = dbRead.query(FoodchainRApplication.TABLE_ORDER, null,
				"where " + FoodchainRApplication.ID + "=?", new String[] { id
						+ "" }, null, null, null);
		dbRead.close();
		if (cursor.moveToNext()) {
			OrderData data = new OrderData();
			data.id = id;
			data.customerId = cursor.getInt(cursor
					.getColumnIndex(FoodchainRApplication.CUSTOMER));
			// TODO
			return data;
		} else
			return null;
	}
}