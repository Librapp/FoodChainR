package lms.foodchainR.dao;

import lms.foodchainR.FoodchainRApplication;
import lms.foodchainR.data.CaseStyleData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CaseStyle_DBHelper extends Base_DBHelper {

	public CaseStyle_DBHelper(Context context) {
		super(context, "fcr_caseStyle.db", null,
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
		db.execSQL(createStyleDataTable());
	}

	/** 生成自定义类型表 */
	private String createStyleDataTable() {
		return CREATE + FoodchainRApplication.TABLE_CASESTYLE + " ("
				+ FoodchainRApplication.ID + AUTO_KEY + ","
				+ FoodchainRApplication.NAME + TEXT + ")";
	}

	public void insert(CaseStyleData data) {
		ContentValues values = new ContentValues();
		values.put(FoodchainRApplication.NAME, data.name);
		getWritableDatabase();
		dbWrite.insert(FoodchainRApplication.TABLE_CASESTYLE, null, values);
		dbWrite.close();
	}

	public void delete(CaseStyleData data) {
		getWritableDatabase();
		dbWrite.delete(FoodchainRApplication.TABLE_CASESTYLE, "where "
				+ FoodchainRApplication.ID + "=?",
				new String[] { data.id + "" });
		dbWrite.close();
	}

	public void update(CaseStyleData data) {
		ContentValues values = new ContentValues();
		values.put(FoodchainRApplication.NAME, data.name);
		getWritableDatabase();
		dbWrite.update(FoodchainRApplication.TABLE_CASESTYLE, values, "where "
				+ FoodchainRApplication.ID + "=?",
				new String[] { data.id + "" });
		dbWrite.close();
	}

	public CaseStyleData getById(int id) {
		dbRead = getReadableDatabase();
		Cursor cursor = dbRead.query(FoodchainRApplication.TABLE_CASESTYLE,
				null, "where " + FoodchainRApplication.ID + "=?",
				new String[] { id + "" }, null, null, null);
		dbRead.close();
		if (cursor.moveToNext()) {
			CaseStyleData data = new CaseStyleData();
			data.id = id;
			data.name = cursor.getString(cursor
					.getColumnIndex(FoodchainRApplication.NAME));
			return data;
		} else
			return null;
	}
}
