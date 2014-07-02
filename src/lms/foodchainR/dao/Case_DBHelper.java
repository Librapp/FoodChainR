package lms.foodchainR.dao;

import java.util.ArrayList;

import lms.foodchainR.data.CaseData;
import lms.foodchainR.data.CaseStyleData;
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
	private final String CASEDATA = "caseData";
	private final String STYLEDATA = "caseStyleData";

	public Case_DBHelper(Context context) {
		super(context, "fcr_case.db", null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		this.db = db;
		createTable();
	}

	/** 生成表 */
	private void createTable() {
		db.execSQL(createCaseDataTable());
		db.execSQL(createStyleDataTable());

	}

	/** 生成菜肴表 */
	private String createCaseDataTable() {
		return CREATE + CASEDATA + " (" + "caseId" + AUTO_KEY + ",name varchar"
				+ ",price float" + ",special float" + ",mark float"
				+ ",state integer" + ",picPath varchar" + ",intro varchar"
				+ ",cookTime integer" + ",styleId integer" + ")";
	}

	/** 生成自定义类型表 */
	private String createStyleDataTable() {
		return CREATE + STYLEDATA + " (" + "styleId" + AUTO_KEY
				+ ",name varchar" + ")";
	}

	/** 获取详情 */
	public boolean getCaseDetail(CaseData c) {
		Cursor cursor = null;
		try {
			db = getReadableDatabase();
			selectArgs = new String[] { String.valueOf(c.caseId), c.name };
			cursor = db.query(CASEDATA, null, "caseId=?||name=?", selectArgs,
					null, null, null);
			if (cursor.moveToNext() && !c.isNew) {
				c.caseId = cursor.getInt(cursor.getColumnIndex("caseId"));
				c.name = cursor.getString(cursor.getColumnIndex("name"));
				c.price = cursor.getFloat(cursor.getColumnIndex("price"));
				c.picPath = cursor.getString(cursor.getColumnIndex("picPath"));
				c.intro = cursor.getString(cursor.getColumnIndex("intro"));
				c.cookTime = cursor.getInt(cursor.getColumnIndex("cookTime"));
				c.styleId = cursor.getInt(cursor.getColumnIndex("styleId"));
				c.special = cursor.getFloat(cursor.getColumnIndex("special"));
				c.mark = cursor.getFloat(cursor.getColumnIndex("mark"));
				c.state = cursor.getInt(cursor.getColumnIndex("state"));
				c.isNew = false;
			} else
				c.isNew = true;
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

	/** 获取类型 */
	public ArrayList<CaseStyleData> getCaseStyleList() {
		ArrayList<CaseStyleData> list = new ArrayList<CaseStyleData>();
		Cursor cursor = null;
		if (db == null)
			db = getReadableDatabase();
		try {
			cursor = db.query(STYLEDATA, null, null, null, null, null,
					"styleId");
			while (cursor.moveToNext()) {
				CaseStyleData c = new CaseStyleData(cursor.getInt(0),
						cursor.getString(1));
				if (!getCaseStyleData(c))
					c.setList(new ArrayList<CaseData>());
				list.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		return list;
	}

	/** 按类型获取菜单 */
	public boolean getCaseStyleData(CaseStyleData csd) {
		Cursor cursor = null;
		if (db == null)
			db = getReadableDatabase();
		selectArgs = new String[] { csd.styleId + "" };
		try {
			cursor = db.query(CASEDATA, null, "styleId=?", selectArgs, null,
					null, null);
			ArrayList<CaseData> caseList = new ArrayList<CaseData>();
			while (cursor.moveToNext()) {
				CaseData c = new CaseData();
				c.caseId = cursor.getInt(cursor.getColumnIndex("caseId"));
				c.name = cursor.getString(cursor.getColumnIndex("name"));
				c.price = cursor.getFloat(cursor.getColumnIndex("price"));
				c.picPath = cursor.getString(cursor.getColumnIndex("picPath"));
				c.intro = cursor.getString(cursor.getColumnIndex("intro"));
				c.cookTime = cursor.getInt(cursor.getColumnIndex("cookTime"));
				c.styleId = cursor.getInt(cursor.getColumnIndex("styleId"));
				c.special = cursor.getFloat(cursor.getColumnIndex("special"));
				c.mark = cursor.getFloat(cursor.getColumnIndex("mark"));
				c.state = cursor.getInt(cursor.getColumnIndex("state"));
				caseList.add(c);
			}
			csd.setList(caseList);
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

	/** 按名字获取类型 */
	public boolean getCaseStyleDataByName(CaseStyleData csd) {
		Cursor cursor = null;
		if (db == null)
			db = getReadableDatabase();
		selectArgs = new String[] { csd.name };
		try {
			cursor = db.query(STYLEDATA, null, "name=?", selectArgs, null,
					null, null);
			if (cursor.moveToNext()) {
				csd.styleId = cursor.getInt(cursor.getColumnIndex("styleId"));
				csd.name = cursor.getString(cursor.getColumnIndex("name"));
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

	/** 创建菜品 */
	public boolean createCase(CaseData c) {
		db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("caseId", c.caseId);
		values.put("name", c.name);
		values.put("price", c.price);
		values.put("picPath", c.picPath);
		values.put("intro", c.intro);
		values.put("cookTime", c.cookTime);
		values.put("styleId", c.styleId);
		values.put("mark", c.mark);
		values.put("state", c.state);
		try {
			db.beginTransaction();
			db.insert(CASEDATA, null, values);
			db.setTransactionSuccessful();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			db.endTransaction();
		}
	}

	/** 创建菜品类型 */
	public boolean createCaseStyle(CaseStyleData c) {
		db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", c.name);
		try {
			db.beginTransaction();
			db.insert(STYLEDATA, null, values);
			db.setTransactionSuccessful();
			getCaseStyleDataByName(c);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			db.endTransaction();
		}
	}

	/** 修改菜品类型数据 */
	public boolean upgradeCaseStyle(CaseStyleData c) {
		db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", c.name);
		selectArgs = new String[] { c.styleId + "" };
		try {
			db.beginTransaction();
			db.update(STYLEDATA, values, "styleId=?", selectArgs);
			db.setTransactionSuccessful();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			db.endTransaction();
		}
	}

	/** 修改菜品数据 */
	public boolean upgradeCase(CaseData c) {
		db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("state", c.state);
		values.put("name", c.name);
		values.put("price", c.price);
		values.put("picPath", c.picPath);
		values.put("intro", c.intro);
		values.put("mark", c.mark);
		values.put("cookTime", c.cookTime);
		values.put("styleId", c.styleId);
		selectArgs = new String[] { c.caseId + "" };
		try {
			db.beginTransaction();
			db.update(CASEDATA, values, "caseId=?", selectArgs);
			db.setTransactionSuccessful();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			db.endTransaction();
		}
	}

	/** 删除菜品 */
	public boolean deleteCase(CaseData c) {
		db = getWritableDatabase();
		selectArgs = new String[] { c.caseId + "" };
		try {
			db.beginTransaction();
			db.delete(CASEDATA, "caseId=?", selectArgs);
			db.setTransactionSuccessful();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			db.endTransaction();
		}
	}

	/** 删除类型 */
	public boolean deleteCaseStyle(CaseStyleData c) {
		db = getWritableDatabase();
		selectArgs = new String[] { c.styleId + "" };
		try {
			db.beginTransaction();
			db.delete(STYLEDATA, "styleId=?", selectArgs);
			db.delete(CASEDATA, "styleId=?", selectArgs);
			db.setTransactionSuccessful();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			db.endTransaction();
		}
	}

	/** 更新数据库 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
}
