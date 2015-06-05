package lms.foodchainR.dao;

import lms.foodchainR.FoodchainRApplication;
import lms.foodchainR.data.MaterialData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author 李梦思
 * @description 材料数据库辅助工具类
 * @createTime 2013-9-2
 */
public class Material_DBHelper extends Base_DBHelper {
	public Material_DBHelper(Context context) {
		super(context, "fcr_material.db", null,
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
		db.execSQL(createMaterialDataTable());
	}

	/** 生成餐桌表 */
	private String createMaterialDataTable() {
		return CREATE + FoodchainRApplication.TABLE_MATERIAL + " ("
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

	public void insert(MaterialData data) {
		ContentValues values = new ContentValues();
		values.put(FoodchainRApplication.ID, data.id);
		values.put(FoodchainRApplication.NAME, data.name);
		values.put(FoodchainRApplication.TYPE, data.type);
		values.put(FoodchainRApplication.UNIT, data.unit);
		values.put(FoodchainRApplication.PRICE, data.price);
		values.put(FoodchainRApplication.SHELFTIME, data.shelf_time);
		values.put(FoodchainRApplication.SHELFCONDATION, data.shelf_condation);
		values.put(FoodchainRApplication.CREATETIME, data.create_time);
		values.put(FoodchainRApplication.COUNT, data.count);
		values.put(FoodchainRApplication.SEASON, data.season);
		getWritableDatabase();
		dbWrite.insert(FoodchainRApplication.TABLE_MATERIAL, null, values);
		dbWrite.close();
	}

	public void delete(MaterialData data) {
		getWritableDatabase();
		dbWrite.delete(FoodchainRApplication.TABLE_MATERIAL, "where id=?",
				new String[] { data.id + "" });
		dbWrite.close();
	}

	public void update(MaterialData data) {
		ContentValues values = new ContentValues();
		values.put(FoodchainRApplication.NAME, data.name);
		values.put(FoodchainRApplication.TYPE, data.type);
		values.put(FoodchainRApplication.UNIT, data.unit);
		values.put(FoodchainRApplication.PRICE, data.price);
		values.put(FoodchainRApplication.SHELFTIME, data.shelf_time);
		values.put(FoodchainRApplication.SHELFCONDATION, data.shelf_condation);
		values.put(FoodchainRApplication.CREATETIME, data.create_time);
		values.put(FoodchainRApplication.COUNT, data.count);
		values.put(FoodchainRApplication.SEASON, data.season);
		getWritableDatabase();
		dbWrite.update(FoodchainRApplication.TABLE_MATERIAL, values, "where "
				+ FoodchainRApplication.ID + "=?",
				new String[] { data.id + "" });
		dbWrite.close();
	}

	public MaterialData getById(int id) {
		getReadableDatabase();
		Cursor cursor = dbRead.query(FoodchainRApplication.TABLE_MATERIAL,
				null, "where id=?", new String[] { id + "" }, null, null, null);
		dbRead.close();
		if (cursor.moveToNext()) {
			MaterialData data = new MaterialData();
			data.id = id;
			// TODO
			return data;
		} else
			return null;
	}
}
