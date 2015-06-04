package lms.foodchainR.dao;

import lms.foodchainR.FoodchainRApplication;
import lms.foodchainR.data.CustomerData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author 李梦思
 * @description 顾客数据库辅助工具类
 * @createTime 2013-2-20
 */
public class Customer_DBHelper extends Base_DBHelper {
	public Customer_DBHelper(Context context) {
		super(context, "fcr_customer.db", null,
				FoodchainRApplication.DB_VERSION);
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
		db.execSQL(createCustomerDataTable());
	}

	/** 生成餐桌表 */
	private String createCustomerDataTable() {
		return CREATE + FoodchainRApplication.TABLE_CUSTOMER + " ("
				+ FoodchainRApplication.ID + AUTO_KEY + ","
				+ FoodchainRApplication.NAME + TEXT + ","
				+ FoodchainRApplication.TYPE + INTEGER + ","
				+ FoodchainRApplication.UNIT + TEXT + ","
				+ FoodchainRApplication.PRICE + FLOAT + ","
				+ FoodchainRApplication.SHELFTIME + TEXT + ","
				+ FoodchainRApplication.CREATETIME + TEXT + ","
				+ FoodchainRApplication.SHELFCONDATION + TEXT + ","
				+ FoodchainRApplication.COUNT + FLOAT + ","
				+ FoodchainRApplication.SEASON + TEXT + ")";
	}

	public void insert(CustomerData data) {
		ContentValues values = new ContentValues();
		values.put(FoodchainRApplication.ID, data.id);
		values.put(FoodchainRApplication.NAME, data.name);
		values.put(FoodchainRApplication.STATE, data.state);
		values.put(FoodchainRApplication.ADDRESS, data.address);
		values.put(FoodchainRApplication.PHONE, data.phone);
		values.put(FoodchainRApplication.POINT, data.point);
		values.put(FoodchainRApplication.MONEY, data.money);
		values.put(FoodchainRApplication.CREDIT, data.credit);
		values.put(FoodchainRApplication.EMAIL, data.email);
		values.put(FoodchainRApplication.STYLE, data.style);
		values.put(FoodchainRApplication.COMETIME, data.comeTime);
		values.put(FoodchainRApplication.LEAVETIME, data.leaveTime);
		values.put(FoodchainRApplication.LEVEL, data.level);
		values.put(FoodchainRApplication.GENDER, data.gender);
		values.put(FoodchainRApplication.COUNT, data.count);
		values.put(FoodchainRApplication.SEX, data.sex);
		values.put(FoodchainRApplication.SEAT, data.seatId);
		values.put(FoodchainRApplication.TABLE, data.tableId);
		getWritableDatabase();
		dbWrite.insert(FoodchainRApplication.TABLE_CUSTOMER, null, values);
		dbWrite.close();
	}

	public void delete(CustomerData data) {
		getWritableDatabase();
		dbWrite.delete(FoodchainRApplication.TABLE_CUSTOMER, "where id=?",
				new String[] { data.id + "" });
		dbWrite.close();
	}

	public void update(CustomerData data) {
		ContentValues values = new ContentValues();
		values.put(FoodchainRApplication.NAME, data.name);
		values.put(FoodchainRApplication.STATE, data.state);
		values.put(FoodchainRApplication.ADDRESS, data.address);
		values.put(FoodchainRApplication.PHONE, data.phone);
		values.put(FoodchainRApplication.POINT, data.point);
		values.put(FoodchainRApplication.MONEY, data.money);
		values.put(FoodchainRApplication.CREDIT, data.credit);
		values.put(FoodchainRApplication.EMAIL, data.email);
		values.put(FoodchainRApplication.STYLE, data.style);
		values.put(FoodchainRApplication.COMETIME, data.comeTime);
		values.put(FoodchainRApplication.LEAVETIME, data.leaveTime);
		values.put(FoodchainRApplication.LEVEL, data.level);
		values.put(FoodchainRApplication.GENDER, data.gender);
		values.put(FoodchainRApplication.COUNT, data.count);
		values.put(FoodchainRApplication.SEX, data.sex);
		values.put(FoodchainRApplication.SEAT, data.seatId);
		values.put(FoodchainRApplication.TABLE, data.tableId);
		getWritableDatabase();
		dbWrite.update(FoodchainRApplication.TABLE_CUSTOMER, values, "where "
				+ FoodchainRApplication.ID + "=?",
				new String[] { data.id + "" });
		dbWrite.close();
	}

	public CustomerData getById(int id) {
		getReadableDatabase();
		Cursor cursor = dbRead.query(FoodchainRApplication.TABLE_CUSTOMER,
				null, "where id=?", new String[] { id + "" }, null, null, null);
		dbRead.close();
		if (cursor.moveToNext()) {
			CustomerData data = new CustomerData();
			data.id = id;
			// TODO
			return data;
		} else
			return null;
	}
}
