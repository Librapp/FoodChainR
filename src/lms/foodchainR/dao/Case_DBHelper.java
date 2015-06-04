package lms.foodchainR.dao;

import lms.foodchainR.FoodchainRApplication;
import lms.foodchainR.data.CaseData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author 李梦思
 * @description 菜单数据库辅助工具类
 * @createTime 2013-2-20
 */
public class Case_DBHelper extends Base_DBHelper {

	private static int VERSION = 1;
	private static Case_DBHelper instance;

	public Case_DBHelper(Context context) {
		super(context, "fcr_case.db", null, VERSION);
	}

	public static Case_DBHelper getInstance(Context context) {
		if (null == instance)
			instance = new Case_DBHelper(context);
		return instance;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		createTable(db);
	}

	/** 生成表 */
	private void createTable(SQLiteDatabase db) {
		db.execSQL(createCaseDataTable());
	}

	/** 生成菜肴表 */
	private String createCaseDataTable() {
		return CREATE + FoodchainRApplication.TABLE_CASE + " ("
				+ FoodchainRApplication.ID + AUTO_KEY + ","
				+ FoodchainRApplication.NAME + TEXT + ","
				+ FoodchainRApplication.PRICE + FLOAT + ","
				+ FoodchainRApplication.PIC + TEXT + ","
				+ FoodchainRApplication.MARK + FLOAT + ","
				+ FoodchainRApplication.POINT + INTEGER + ","
				+ FoodchainRApplication.STATE + INTEGER + ","
				+ FoodchainRApplication.TIME + INTEGER + ","
				+ FoodchainRApplication.INFO + TEXT + ","
				+ FoodchainRApplication.STYLE + INTEGER + ")";
	}

	public void insert(CaseData data) {
		ContentValues values = new ContentValues();
		values.put(FoodchainRApplication.NAME, data.name);
		values.put(FoodchainRApplication.PIC, data.pic);
		values.put(FoodchainRApplication.PRICE, data.price);
		values.put(FoodchainRApplication.INFO, data.intro);
		values.put(FoodchainRApplication.STYLE, data.style);
		values.put(FoodchainRApplication.TYPE, data.type);
		values.put(FoodchainRApplication.MARK, data.mark);
		values.put(FoodchainRApplication.POINT, data.point);
		values.put(FoodchainRApplication.TIME, data.cookTime);
		values.put(FoodchainRApplication.STATE, data.state);
		getWritableDatabase();
		dbWrite.insert(FoodchainRApplication.TABLE_CASE, null, values);
		dbWrite.close();
	}

	public void delete(CaseData data) {
		getWritableDatabase();
		dbWrite.delete(FoodchainRApplication.TABLE_CASE, "where "
				+ FoodchainRApplication.ID + "=?",
				new String[] { data.id + "" });
		dbWrite.close();
	}

	public void update(CaseData data) {
		ContentValues values = new ContentValues();
		values.put(FoodchainRApplication.NAME, data.name);
		values.put(FoodchainRApplication.PIC, data.pic);
		values.put(FoodchainRApplication.PRICE, data.price);
		values.put(FoodchainRApplication.INFO, data.intro);
		values.put(FoodchainRApplication.STYLE, data.style);
		values.put(FoodchainRApplication.TYPE, data.type);
		values.put(FoodchainRApplication.MARK, data.mark);
		values.put(FoodchainRApplication.POINT, data.point);
		values.put(FoodchainRApplication.TIME, data.cookTime);
		values.put(FoodchainRApplication.STATE, data.state);
		getWritableDatabase();
		dbWrite.update(FoodchainRApplication.TABLE_CASE, values, "where "
				+ FoodchainRApplication.ID + "=?",
				new String[] { data.id + "" });
		dbWrite.close();
	}

	public CaseData getById(int id) {
		dbRead = getReadableDatabase();
		Cursor cursor = dbRead.query(FoodchainRApplication.TABLE_CASE, null,
				"where " + FoodchainRApplication.ID + "=?", new String[] { id
						+ "" }, null, null, null);
		dbRead.close();
		if (cursor.moveToNext()) {
			CaseData data = new CaseData();
			data.id = id;
			data.name = cursor.getString(cursor
					.getColumnIndex(FoodchainRApplication.NAME));
			data.pic = cursor.getString(cursor
					.getColumnIndex(FoodchainRApplication.PIC));
			data.price = cursor.getFloat(cursor
					.getColumnIndex(FoodchainRApplication.PRICE));
			data.intro = cursor.getString(cursor
					.getColumnIndex(FoodchainRApplication.INFO));
			data.cookTime = cursor.getInt(cursor
					.getColumnIndex(FoodchainRApplication.TIME));
			data.state = cursor.getInt(cursor
					.getColumnIndex(FoodchainRApplication.STATE));
			data.type = cursor.getInt(cursor
					.getColumnIndex(FoodchainRApplication.TYPE));
			data.style = cursor.getInt(cursor
					.getColumnIndex(FoodchainRApplication.STYLE));
			return data;
		} else
			return null;
	}
}
