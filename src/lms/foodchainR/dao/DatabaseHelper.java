package lms.foodchainR.dao;

import java.sql.SQLException;

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
	private Dao<CustomerData, Integer> customerDao;
	private Dao<CaseData, Integer> caseDao;
	private Dao<ListData, Integer> listDao;
	private Dao<BillData, Integer> billDao;
	private Dao<CaseStyleData, Integer> caseStyleDao;
	private Dao<TableData, Integer> tableDao;
	private Dao<SeatData, Integer> seatDao;
	private Dao<MaterialData, Integer> materialDao;
	private Dao<TableStyleData, Integer> tableStyleDao;
	private Dao<OrderData, Integer> orderDao;

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

	/**
	 * 获得customerDao
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Dao<CustomerData, Integer> getCustomerDao() throws SQLException {
		if (customerDao == null) {
			customerDao = getDao(CustomerData.class);
		}
		return customerDao;
	}

	/**
	 * 获得caseDao
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Dao<CaseData, Integer> getCaseDao() throws SQLException {
		if (caseDao == null) {
			caseDao = getDao(CaseData.class);
		}
		return caseDao;
	}

	/**
	 * 获得caseStyleDao
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Dao<CaseStyleData, Integer> getCaseStyleDao() throws SQLException {
		if (caseStyleDao == null) {
			caseStyleDao = getDao(CaseStyleData.class);
		}
		return caseStyleDao;
	}

	/**
	 * 获得materialDao
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Dao<MaterialData, Integer> getMaterialDao() throws SQLException {
		if (materialDao == null) {
			materialDao = getDao(MaterialData.class);
		}
		return materialDao;
	}

	/**
	 * 获得tableStyleDao
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Dao<TableStyleData, Integer> getTableStyleDao() throws SQLException {
		if (tableStyleDao == null) {
			tableStyleDao = getDao(TableStyleData.class);
		}
		return tableStyleDao;
	}

	/**
	 * 获得tableDao
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Dao<TableData, Integer> getTableDao() throws SQLException {
		if (tableDao == null) {
			tableDao = getDao(TableData.class);
		}
		return tableDao;
	}

	/**
	 * 获得seatDao
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Dao<SeatData, Integer> getSeatDao() throws SQLException {
		if (seatDao == null) {
			seatDao = getDao(SeatData.class);
		}
		return seatDao;
	}

	/**
	 * 获得billDao
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Dao<BillData, Integer> getBillDao() throws SQLException {
		if (billDao == null) {
			billDao = getDao(BillData.class);
		}
		return billDao;
	}

	/**
	 * 获得orderDao
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Dao<OrderData, Integer> getOrderDao() throws SQLException {
		if (orderDao == null) {
			orderDao = getDao(OrderData.class);
		}
		return orderDao;
	}

	/**
	 * 获得listDao
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Dao<ListData, Integer> getListDao() throws SQLException {
		if (listDao == null) {
			listDao = getDao(ListData.class);
		}
		return listDao;
	}

	/**
	 * 释放资源
	 */
	@Override
	public void close() {
		super.close();
		customerDao = null;
	}
}
