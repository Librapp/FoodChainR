package lms.foodchainR.dao;

import lms.foodchainR.FoodchainRApplication;
import lms.foodchainR.data.TableData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author 李梦思
 * @description 桌椅数据库辅助工具类
 * @createTime 2013-2-20
 */
public class Table_DBHelper extends Base_DBHelper {

	public Table_DBHelper(Context context) {
		super(context, "fcr_table.db", null, FoodchainRApplication.DB_VERSION);
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
		db.execSQL(createTableDataTable());
	}

	/** 生成餐桌表 */
	private String createTableDataTable() {
		return CREATE + FoodchainRApplication.TABLE_TABLE + " ("
				+ FoodchainRApplication.ID + AUTO_KEY + ","
				+ FoodchainRApplication.NAME + TEXT + ")";
	}

	public void insert(TableData data) {
		ContentValues values = new ContentValues();
		values.put(FoodchainRApplication.STYLE, data.styleId);
		values.put(FoodchainRApplication.SEATCOUNT, data.seatCount);
		values.put(FoodchainRApplication.STATE, data.state);
		values.put(FoodchainRApplication.TYPE, data.type);
		getWritableDatabase();
		dbWrite.insert(FoodchainRApplication.TABLE_TABLE, null, values);
		dbWrite.close();
	}

	public void delete(TableData data) {
		getWritableDatabase();
		dbWrite.delete(FoodchainRApplication.TABLE_TABLE, "where id=?",
				new String[] { data.id + "" });
		dbWrite.close();
	}

	public void update(TableData data) {
		ContentValues values = new ContentValues();
		values.put(FoodchainRApplication.STYLE, data.styleId);
		values.put(FoodchainRApplication.SEATCOUNT, data.seatCount);
		values.put(FoodchainRApplication.STATE, data.state);
		values.put(FoodchainRApplication.TYPE, data.type);
		getWritableDatabase();
		dbWrite.update(FoodchainRApplication.TABLE_TABLE, values, "where "
				+ FoodchainRApplication.ID + "=?",
				new String[] { data.id + "" });
		dbWrite.close();
	}

	public TableData getById(int id) {
		getReadableDatabase();
		Cursor cursor = dbRead.query(FoodchainRApplication.TABLE_TABLE, null,
				"where id=?", new String[] { id + "" }, null, null, null);
		dbRead.close();
		if (cursor.moveToNext()) {
			TableData data = new TableData();
			data.id = id;
			data.state = cursor.getInt(cursor
					.getColumnIndex(FoodchainRApplication.STATE));
			data.type = cursor.getInt(cursor
					.getColumnIndex(FoodchainRApplication.TYPE));
			data.styleId = cursor.getInt(cursor
					.getColumnIndex(FoodchainRApplication.STYLE));
			data.seatCount = cursor.getInt(cursor
					.getColumnIndex(FoodchainRApplication.SEATCOUNT));
			return data;
		} else
			return null;
	}

}
