package lms.foodchainR.dao;

import lms.foodchainR.FoodchainRApplication;
import lms.foodchainR.data.BillData;
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
	public Bill_DBHelper(Context context) {
		super(context, "fcr_bill.db", null, FoodchainRApplication.DB_VERSION);
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
		db.execSQL(createStyleDataTable());
	}

	/** 生成自定义类型表 */
	private String createStyleDataTable() {
		return CREATE + FoodchainRApplication.TABLE_BILL + " ("
				+ FoodchainRApplication.ID + AUTO_KEY + ","
				+ FoodchainRApplication.CUSTOMER + INTEGER + ","
				+ FoodchainRApplication.MONEY + FLOAT + ","
				+ FoodchainRApplication.DISCOUNT + FLOAT + ")";
	}

	public void insert(BillData data) {
		ContentValues values = new ContentValues();
		values.put(FoodchainRApplication.CUSTOMER, data.customerId);
		values.put(FoodchainRApplication.MONEY, data.cost);
		values.put(FoodchainRApplication.DISCOUNT, data.discount);
		getWritableDatabase();
		dbWrite.insert(FoodchainRApplication.TABLE_BILL, null, values);
		dbWrite.close();
	}

	public void delete(BillData data) {
		getWritableDatabase();
		dbWrite.delete(FoodchainRApplication.TABLE_BILL, "where "
				+ FoodchainRApplication.ID + "=?",
				new String[] { data.id + "" });
		dbWrite.close();
	}

	public void update(BillData data) {
		ContentValues values = new ContentValues();
		values.put(FoodchainRApplication.CUSTOMER, data.customerId);
		values.put(FoodchainRApplication.MONEY, data.cost);
		values.put(FoodchainRApplication.DISCOUNT, data.discount);
		getWritableDatabase();
		dbWrite.update(FoodchainRApplication.TABLE_BILL, values, "where "
				+ FoodchainRApplication.ID + "=?",
				new String[] { data.id + "" });
		dbWrite.close();
	}

	public BillData getById(int id) {
		dbRead = getReadableDatabase();
		Cursor cursor = dbRead.query(FoodchainRApplication.TABLE_BILL, null,
				"where " + FoodchainRApplication.ID + "=?", new String[] { id
						+ "" }, null, null, null);
		dbRead.close();
		if (cursor.moveToNext()) {
			BillData data = new BillData();
			data.id = id;
			data.customerId = cursor.getInt(cursor
					.getColumnIndex(FoodchainRApplication.CUSTOMER));
			data.cost = cursor.getFloat(cursor
					.getColumnIndex(FoodchainRApplication.MONEY));
			data.discount = cursor.getFloat(cursor
					.getColumnIndex(FoodchainRApplication.DISCOUNT));
			return data;
		} else
			return null;
	}
}
