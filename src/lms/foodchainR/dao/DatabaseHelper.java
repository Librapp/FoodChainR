package lms.foodchainR.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import lms.foodchainR.data.BillData;
import lms.foodchainR.data.CaseData;
import lms.foodchainR.data.CaseStyleData;
import lms.foodchainR.data.CustomerData;
import lms.foodchainR.data.ListData;
import lms.foodchainR.data.MaterialData;
import lms.foodchainR.data.OrderData;
import lms.foodchainR.data.SeatData;
import lms.foodchainR.data.TableData;
import lms.foodchainR.data.TableStyleData;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
	private static final String DB_NAME = "fcr.db";
	private Map<String, Dao> daos = new HashMap<String, Dao>();

	private DatabaseHelper(Context context) {
		super(context, DB_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase database,
			ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, CustomerData.class);
			TableUtils.createTable(connectionSource, CaseData.class);
			TableUtils.createTable(connectionSource, CaseStyleData.class);
			TableUtils.createTable(connectionSource, MaterialData.class);
			TableUtils.createTable(connectionSource, TableData.class);
			TableUtils.createTable(connectionSource, TableStyleData.class);
			TableUtils.createTable(connectionSource, SeatData.class);
			TableUtils.createTable(connectionSource, OrderData.class);
			TableUtils.createTable(connectionSource, BillData.class);
			TableUtils.createTable(connectionSource, ListData.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase database,
			ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			TableUtils.dropTable(connectionSource, CustomerData.class, true);
			TableUtils.dropTable(connectionSource, ListData.class, true);
			TableUtils.dropTable(connectionSource, OrderData.class, true);
			TableUtils.dropTable(connectionSource, BillData.class, true);
			TableUtils.dropTable(connectionSource, TableStyleData.class, true);
			TableUtils.dropTable(connectionSource, TableData.class, true);
			TableUtils.dropTable(connectionSource, SeatData.class, true);
			TableUtils.dropTable(connectionSource, CaseStyleData.class, true);
			TableUtils.dropTable(connectionSource, CaseData.class, true);
			TableUtils.dropTable(connectionSource, MaterialData.class, true);
			onCreate(database, connectionSource);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static DatabaseHelper instance;

	/**
	 * 单例获取该Helper
	 * 
	 * @param context
	 * @return
	 */
	public static synchronized DatabaseHelper getHelper(Context context) {
		if (instance == null) {
			synchronized (DatabaseHelper.class) {
				if (instance == null)
					instance = new DatabaseHelper(context);
			}
		}

		return instance;
	}

	public synchronized Dao getDao(Class clazz) throws SQLException {
		Dao dao = null;
		String className = clazz.getSimpleName();

		if (daos.containsKey(className)) {
			dao = daos.get(className);
		}
		if (dao == null) {
			dao = super.getDao(clazz);
			daos.put(className, dao);
		}
		return dao;
	}

	/**
	 * 释放资源
	 */
	@Override
	public void close() {
		super.close();
		for (String key : daos.keySet()) {
			Dao dao = daos.get(key);
			dao = null;
		}
	}
}
