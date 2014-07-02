package lms.foodchainR.service;

import java.util.ArrayList;

import lms.foodchainR.dao.Table_DBHelper;
import lms.foodchainR.data.CustomerData;
import lms.foodchainR.data.SeatData;
import lms.foodchainR.data.TableData;
import lms.foodchainR.data.TableStyleData;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

/**
 * @author 李梦思
 * @version 1.0
 * @createTime 2012-11-15
 * @description 桌位service
 * @changeLog
 */
public class TableService extends BaseService {
	private static Table_DBHelper tdb;
	private static Context context;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		context = this.getApplicationContext();
		tdb = new Table_DBHelper(context);
	}

	/** 创建新餐桌类型 */
	public static boolean createTableStyle(TableStyleData ts) {
		return tdb.insertTableStyle(ts);
	}

	/** 修改餐桌类型 */
	public static boolean upgradeTableStyle(TableStyleData ts) {
		return tdb.upgradeTableStyle(ts);
	}

	/** 修改餐桌 */
	public static boolean upgradeTable(TableData t) {
		return tdb.upgradeTable(t);
	}

	/** 修改座位 */
	public static boolean upgradeSeat(SeatData s) {
		return tdb.upgradeSeat(s);
	}

	/** 删除餐桌类型 */
	public static boolean deleteTableStyle(TableStyleData ts) {
		return tdb.deleteTableStyleData(ts);
	}

	/** 获取餐桌类型列表 */
	public static ArrayList<TableStyleData> getTableStyles() {
		return tdb.getTableStyleDataList();
	}

	/** 获取餐桌类型详情 */
	public static boolean getTableStyleDetail(TableStyleData tableStyle) {
		return tdb.getTableStyleDetail(tableStyle);
	}

	/** 选座位或者排号 */
	public static String setSeat(SeatData seat, CustomerData customer) {
		return tdb.setSeat(seat, customer);
	}

}
