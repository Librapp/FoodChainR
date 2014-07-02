package lms.foodchainR.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lms.foodchainR.data.CustomerData;
import lms.foodchainR.data.SeatData;
import lms.foodchainR.data.TableData;
import lms.foodchainR.data.TableStyleData;
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

	private static int VERSION = 1;
	private final String TABLESTYLEDATA = "tableStyleData";
	private final String TABLEDATA = "tableData";
	private final String SEATDATA = "seatData";
	private final String LISTDATA = "listData";

	public Table_DBHelper(Context context) {
		super(context, "fcr_table.db", null, VERSION);
		// writeThread.start();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		this.db = db;
		createTable();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.setVersion(newVersion);
	}

	/** 生成表 */
	private void createTable() {
		db.execSQL(createTableDataTable());
		db.execSQL(createTableStyleDataTable());
		db.execSQL(createSeatDataTable());
		db.execSQL(createListDataTable());
	}

	/** 生成餐桌类型表 */
	private String createTableStyleDataTable() {
		return CREATE + TABLESTYLEDATA + " (" + "styleId varchar"
				+ ",seatCount integer" + ",tableCount integer" + ")";
	}

	/** 生成餐桌表 */
	private String createTableDataTable() {
		return CREATE + TABLEDATA + " (" + "tableId varchar"
				+ ",styleId varchar" + ",state integer" + ",seatCount integer"
				+ ",customerId integer" + ",waiterId integer"
				+ ",freeSeat integer" + "bookTime varchar" + ")";
	}

	/** 生成座位表 */
	private String createSeatDataTable() {
		return CREATE + SEATDATA + " (" + "seatId varchar" + ",tableId varchar"
				+ ",styleId varchar" + ",state integer" + ",customerId integer"
				+ ")";
	}

	/** 生成排号表 */
	private String createListDataTable() {
		return CREATE + LISTDATA + " (" + "listId" + AUTO_KEY
				+ ",customerId varchar" + ",number integer"
				+ ",comeTime varchar" + ",styleId varchar" + ")";
	}

	// ------------------------------------------------------ 插入一组对象

	// ------------------------------------------------------ 获取一个对象
	/** 获取餐桌详情 */
	public boolean getTableDataDetail(TableData table) {
		Cursor cursor = null;
		if (db == null)
			db = getReadableDatabase();
		selectArgs = new String[] { table.tableId };
		try {
			cursor = db.query(TABLESTYLEDATA, null, "tableId=?", selectArgs,
					null, null, null);
			if (cursor != null && cursor.moveToNext()) {
				table.tableId = cursor.getString(cursor
						.getColumnIndex("tableId"));
				table.styleId = cursor.getString(cursor
						.getColumnIndex("styleId"));
				table.seatCount = cursor.getInt(cursor
						.getColumnIndex("seatCount"));
				table.state = cursor.getInt(cursor.getColumnIndex("state"));
				CustomerData c = new CustomerData();
				c.id = cursor.getInt(cursor.getColumnIndex("customerId"));
				// TODO 查顾客表
				table.customer = c;
				table.bookTime = cursor.getString(cursor
						.getColumnIndex("bookTime"));
				table.freeSeat = cursor.getInt(cursor
						.getColumnIndex("freeSeat"));
				table.waiterId = cursor.getInt(cursor
						.getColumnIndex("waiterId"));
				getSeatDataByTable(table);
				return true;
			} else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
	}

	/** 获取座位详情 */
	public boolean getSeatDetail(SeatData seat) {
		Cursor cursor = null;
		try {
			db = getReadableDatabase();
			selectArgs = new String[] { seat.seatId };
			cursor = db.query(SEATDATA, null, "seatId=? ", selectArgs, null,
					null, null);

			if (cursor != null) {
				while (cursor.moveToNext()) {
					seat.seatId = cursor.getString(cursor
							.getColumnIndex("seatId"));
					seat.tableId = cursor.getString(cursor
							.getColumnIndex("tableId"));
					seat.state = cursor.getInt(cursor.getColumnIndex("state"));
					CustomerData c = new CustomerData();
					c.id = cursor.getInt(cursor.getColumnIndex("customerId"));
					// TODO 查顾客表
					seat.customer = c;
				}
				return true;
			} else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
	}

	// ------------------------------------------------------------- 获取一组对象
	/** 获取餐桌类型列表 */
	public ArrayList<TableStyleData> getTableStyleDataList() {
		Cursor cursor = null;
		ArrayList<TableStyleData> list = new ArrayList<TableStyleData>();
		try {
			db = getReadableDatabase();
			cursor = db.query(TABLESTYLEDATA, null, null, null, null, null,
					null);

			if (cursor != null) {
				while (cursor.moveToNext()) {
					TableStyleData tableStyle = new TableStyleData(
							cursor.getString(cursor.getColumnIndex("styleId")),
							cursor.getInt(cursor.getColumnIndex("tableCount")),
							cursor.getInt(cursor.getColumnIndex("seatCount")));
					list.add(tableStyle);
				}
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

	/** 获取餐桌类型详情 */
	public boolean getTableStyleDetail(TableStyleData tableStyle) {
		Cursor cursor = null;
		if (db == null)
			db = getReadableDatabase();
		selectArgs = new String[] { tableStyle.styleId };
		try {
			cursor = db.query(TABLESTYLEDATA, null, "styleId=? ", selectArgs,
					null, null, null);
			if (cursor != null && cursor.moveToNext()) {
				tableStyle.styleId = cursor.getString(cursor
						.getColumnIndex("styleId"));
				tableStyle.count = cursor.getInt(cursor
						.getColumnIndex("tableCount"));
				tableStyle.seatCount = cursor.getInt(cursor
						.getColumnIndex("seatCount"));
				getTableDataByTableStyle(tableStyle);
				return true;
			} else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
	}

	/** 根据餐桌类型获取餐桌 */
	public boolean getTableDataByTableStyle(TableStyleData ts) {
		Cursor cursor = null;
		List<TableData> list = new ArrayList<TableData>();
		if (db == null)
			db = getReadableDatabase();
		selectArgs = new String[] { ts.styleId };
		try {
			cursor = db.query(TABLEDATA, null, "styleId=? ", selectArgs, null,
					null, null);
			while (cursor.moveToNext()) {
				TableData table = new TableData();
				table.tableId = cursor.getString(cursor
						.getColumnIndex("tableId"));
				table.styleId = cursor.getString(cursor
						.getColumnIndex("styleId"));
				table.seatCount = cursor.getInt(cursor
						.getColumnIndex("seatCount"));
				table.state = cursor.getInt(cursor.getColumnIndex("state"));
				if (table.state != TableData.AVAILIABLE) {
					CustomerData c = new CustomerData();
					c.id = cursor.getInt(cursor.getColumnIndex("customerId"));
					// TODO 查顾客表
					table.customer = c;
					table.bookTime = cursor.getString(cursor
							.getColumnIndex("bookTime"));
				}
				table.freeSeat = cursor.getInt(cursor
						.getColumnIndex("freeSeat"));
				table.waiterId = cursor.getInt(cursor
						.getColumnIndex("waiterId"));
				getSeatDataByTable(table);
				list.add(table);
			}
			ts.setTable(list);
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

	/** 根据餐桌获取座位 */
	public boolean getSeatDataByTable(TableData table) {
		Cursor cursor = null;
		List<SeatData> list = new ArrayList<SeatData>();
		try {
			selectArgs = new String[] { table.tableId };
			cursor = db.query(SEATDATA, null, "tableId=? ", selectArgs, null,
					null, null);

			if (cursor != null) {
				while (cursor.moveToNext()) {
					SeatData seat = new SeatData();
					seat.seatId = cursor.getString(cursor
							.getColumnIndex("seatId"));
					seat.tableId = cursor.getString(cursor
							.getColumnIndex("tableId"));
					seat.state = cursor.getInt(cursor.getColumnIndex("state"));
					if (seat.state != SeatData.AVAILIABLE) {
						CustomerData c = new CustomerData();
						c.id = cursor.getInt(cursor
								.getColumnIndex("customerId"));
						// TODO 查顾客表
						seat.customer = c;
					}
					list.add(seat);
				}
			}
			table.setSeat(list);
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

	// ------------------------------------------------------------ 插入单个对象
	/** 插入单个座位 */
	private void insertSeatData(SeatData s) {
		if (s == null)
			return;
		try {
			ContentValues values = new ContentValues();
			values.put("seatId", s.seatId);
			values.put("tableId", s.tableId);
			values.put("styleId", s.styleId);
			values.put("state", s.state);
			db.insert(SEATDATA, null, values);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 插入单个餐桌 */
	private boolean insertTableData(TableData s) {
		if (s == null)
			return false;
		try {
			ContentValues values = new ContentValues();
			values.put("tableId", s.tableId);
			values.put("styleId", s.styleId);
			values.put("state", s.state);
			values.put("seatCount", s.seatCount);
			// values.put("customerId", s.customer.id);
			// values.put("bookTime", s.bookTime);
			db.insert(TABLEDATA, null, values);
			List<SeatData> list = new ArrayList<SeatData>();
			for (int i = 0; i < s.seatCount; i++) {
				SeatData t = new SeatData(s, i);
				insertSeatData(t);
				list.add(t);
			}
			s.setSeat(list);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/** 插入单个餐桌类型 */
	public boolean insertTableStyle(TableStyleData s) {
		db = getWritableDatabase();
		try {
			ContentValues values = new ContentValues();

			values.put("styleId", s.styleId);
			values.put("seatCount", s.seatCount);
			values.put("tableCount", s.count);

			List<TableData> list = new ArrayList<TableData>();
			for (int i = 0; i < s.count; i++) {
				TableData t = new TableData(s, i);
				insertTableData(t);
				list.add(t);
			}
			s.setTable(list);
			db.beginTransaction();
			db.insert(TABLESTYLEDATA, null, values);
			db.setTransactionSuccessful();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			db.endTransaction();
		}
	}

	// ------------------------------------------------------------ 修改单个对象
	/** 修改单个座位 */
	public boolean upgradeSeat(SeatData s) {
		db = getWritableDatabase();
		ContentValues values = new ContentValues();
		String[] whereArgs = { s.seatId };
		values.put("seatId", s.seatId);
		values.put("state", s.state);
		values.put("customerId", s.customer.id);
		try {
			db.beginTransaction();
			db.update(SEATDATA, values, "seatId=?", whereArgs);
			db.setTransactionSuccessful();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			db.endTransaction();
		}
	}

	/** 修改单个餐桌 */
	public boolean upgradeTable(TableData t) {
		db = getWritableDatabase();
		ContentValues values = new ContentValues();
		selectArgs = new String[] { t.tableId };
		values.put("customerId", t.customer.id);
		values.put("bookTime", t.bookTime);
		values.put("state", t.state);
		try {
			db.beginTransaction();
			db.update(TABLEDATA, values, "tableId=?", selectArgs);
			db.update(SEATDATA, values, "tableId=?", selectArgs);
			db.setTransactionSuccessful();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			db.endTransaction();
		}
	}

	/** 修改餐桌类型 */
	public boolean upgradeTableStyle(TableStyleData ts) {
		if (deleteTableStyleData(ts) && insertTableStyle(ts))
			return true;
		else
			return false;
	}

	// --------------------------------------------------------- 删除数据
	/** 删除餐桌类型 */
	public boolean deleteTableStyleData(TableStyleData ts) {
		db = getWritableDatabase();
		selectArgs = new String[] { ts.styleId };
		try {
			db.beginTransaction();
			db.delete(SEATDATA, "styleId=?", selectArgs);
			db.delete(TABLEDATA, "styleId=?", selectArgs);
			db.delete(TABLESTYLEDATA, "styleId=?", selectArgs);
			db.setTransactionSuccessful();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			db.endTransaction();
		}
	}

	/** 选座排队 */
	public String setSeat(SeatData seat, CustomerData customer) {
		Cursor cursor = null;
		if (db == null)
			db = getReadableDatabase();
		String sql = "", msg = "";
		if (seat == null) {
			sql = "seatCount>=?";
			selectArgs = new String[] { customer.peopleCount + "" };
			cursor = db.query(TABLESTYLEDATA, null, sql, selectArgs, null,
					null, "seatCount");
			if (cursor.moveToNext()) {
				TableStyleData tsd = new TableStyleData();
				tsd.styleId = cursor
						.getString(cursor.getColumnIndex("styleId"));
				sql = "seatCount>=?,styleId=?";
				selectArgs = new String[] {
						String.valueOf(customer.peopleCount), tsd.styleId };
				cursor = db.query(TABLEDATA, null, sql, selectArgs, "styleId",
						null, "seatCount");
				if (cursor.moveToNext()) {
					// TODO 分配座位
					TableData table = new TableData();
					table.tableId = cursor.getString(cursor
							.getColumnIndex("tableId"));
					table.styleId = cursor.getString(cursor
							.getColumnIndex("styleId"));
					table.seatCount = cursor.getInt(cursor
							.getColumnIndex("seatCount"));
					table.state = cursor.getInt(cursor.getColumnIndex("state"));
					CustomerData c = new CustomerData();
					c.id = cursor.getInt(cursor.getColumnIndex("customerId"));
					// TODO 查顾客表
					table.customer = c;
					table.bookTime = cursor.getString(cursor
							.getColumnIndex("bookTime"));
					table.freeSeat = cursor.getInt(cursor
							.getColumnIndex("freeSeat"));
					table.waiterId = cursor.getInt(cursor
							.getColumnIndex("waiterId"));
					sql = "state=?,tableId=?";
					selectArgs = new String[] {
							String.valueOf(SeatData.AVAILIABLE), table.tableId };
					cursor = db.query(SEATDATA, null, sql, selectArgs,
							"tableId", null, null);
					db = getWritableDatabase();
					db.beginTransaction();
					int i = 0;
					ContentValues values = new ContentValues();
					values.put("state", SeatData.OCCUPY);
					values.put("customerId", customer.id);
					while (cursor.moveToNext() && i < customer.peopleCount) {
						db.update(SEATDATA, values, "seatId=?",
								new String[] { cursor.getString(cursor
										.getColumnIndex("seatId")) });
						i++;
					}
					values = new ContentValues();
					values.put("customerId", customer.id);
					values.put("freeSeat", table.freeSeat - i);
					db.update(TABLEDATA, values, "tableId=?",
							new String[] { table.tableId });
					db.setTransactionSuccessful();
					db.endTransaction();
				} else {
					selectArgs = new String[] { tsd.styleId };
					cursor = db.query(LISTDATA, null, "styleId=?", selectArgs,
							null, null, "listId");
					int number = 0;
					if (cursor.moveToNext())
						number = cursor.getCount();
					ContentValues values = new ContentValues();
					values.put("styleId", tsd.styleId);
					values.put("customerId", customer.id);
					values.put("number", number);
					values.put("comeTime", new Date().toLocaleString());
					db = getWritableDatabase();
					db.beginTransaction();
					db.insert(LISTDATA, null, values);
					db.setTransactionSuccessful();
					db.endTransaction();
					// TODO　返回排号
				}
			}
		} else {
			sql = "tableId=?,state=?";
			selectArgs = new String[] { seat.tableId,
					String.valueOf(SeatData.AVAILIABLE) };
			cursor = db.query(TABLEDATA, null, sql, selectArgs, null, null,
					null);
			if (cursor.moveToNext()) {
				TableData td = new TableData();
				td.tableId = cursor.getString(cursor.getColumnIndex("tableId"));
				db.beginTransaction();
				selectArgs = new String[] { seat.tableId,
						String.valueOf(SeatData.AVAILIABLE) };
				cursor = db.query(SEATDATA, null, "tableId=?,state=?",
						selectArgs, null, null, null);
				ContentValues values = new ContentValues();
				values.put("state", SeatData.OCCUPY);
				values.put("customerId", customer.id);
				while (cursor.moveToNext()) {
					db.update(SEATDATA, values, "seatId=?",
							new String[] { cursor.getString(cursor
									.getColumnIndex("seatId")) });
				}
				ContentValues tvalues = new ContentValues();
				tvalues.put("customerId", customer.id);
				tvalues.put("state", SeatData.OCCUPY);
				db.update(TABLEDATA, tvalues, "tableId=?",
						new String[] { td.tableId });
				db.setTransactionSuccessful();
				db.endTransaction();
				msg = "选座成功";
			} else {
				msg = "座位已被占用";
			}
		}
		return msg;
	}
}
