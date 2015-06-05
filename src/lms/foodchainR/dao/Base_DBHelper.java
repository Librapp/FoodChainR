package lms.foodchainR.dao;

import lms.foodchainR.util.CommonUtil;
import lms.foodchainR.util.DataBaseUtil;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author 李梦思
 * @description 数据库辅助工具基类
 * @createTime 2013-2-20
 */
public abstract class Base_DBHelper extends SQLiteOpenHelper {
	protected final static String DB_NAME = "fcr.db";
	protected final String CREATE = "CREATE TABLE IF NOT EXISTS ";
	protected final String DROP = "DROP TABLE IF EXISTS ";
	protected final String AUTO_KEY = " integer primary key autoincrement";
	protected final String TEXT = " text";
	protected final String FLOAT = " float";
	protected final String INTEGER = " integer";

	protected String[] selectArgs;
	protected String[] columns;
	protected SQLiteDatabase dbRead;
	protected SQLiteDatabase dbWrite;

	public Base_DBHelper(Context context) {
		super(context, DB_NAME, null, CommonUtil.getVersionCode(context));
	}

	public Base_DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		try {
			for (int i = oldVersion; i < newVersion; i++) {
				switch (i) {
				case 1:
					DataBaseUtil.UpgradedVersion1To2(db);
					break;
				default:
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		super.onDowngrade(db, oldVersion, newVersion);
	}

	@Override
	public synchronized SQLiteDatabase getReadableDatabase() {
		if (null == dbRead)
			dbRead = super.getReadableDatabase();
		return dbRead;
	}

	@Override
	public synchronized SQLiteDatabase getWritableDatabase() {
		if (null == dbWrite)
			dbWrite = super.getWritableDatabase();
		return dbWrite;
	}
}
