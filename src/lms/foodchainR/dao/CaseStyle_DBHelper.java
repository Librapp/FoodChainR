package lms.foodchainR.dao;

import java.sql.SQLException;

import lms.foodchainR.FoodchainRApplication;
import lms.foodchainR.data.CaseStyleData;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class CaseStyle_DBHelper extends OrmLiteSqliteOpenHelper {

	public CaseStyle_DBHelper(Context context) {
		super(context, "fcr_caseStyle.db", null,
				FoodchainRApplication.DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0, ConnectionSource arg1) {
		try {
			TableUtils.createTableIfNotExists(arg1, CaseStyleData.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, ConnectionSource arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub

	}
}
