package lms.foodchainR.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

public class DataBaseUtil {
	public static void UpgradedVersion1To2(SQLiteDatabase db) {

		try {
			db.execSQL("alter table user rename to temp_user");

			db.execSQL("drop table if exists user");

			db.execSQL("create table if not exists user(id INTEGER PRIMARY KEY AUTOINCREMENT, name varchar(10), remark varchar(50), age varchar(10))");

			db.execSQL("insert into user select id, name, remark, 'age_lala' from temp_user");

			db.execSQL("drop table if exists temp_user");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean CopyDB(Context context) {

		File dbFile = context.getDatabasePath((String) "DBTest.db");

		InputStream myInput;
		try {
			myInput = new FileInputStream(dbFile);

			File file = new File(Environment.getExternalStorageDirectory()
					+ "/aDBTest/");
			if (!file.exists()) {
				file.mkdir();
			}

			OutputStream myOutput = new FileOutputStream(
					Environment.getExternalStorageDirectory()
							+ "/aDBTest/DBTest.db");

			byte[] buffer = new byte[1024];
			int length;
			while ((length = myInput.read(buffer)) > 0) {
				myOutput.write(buffer, 0, length);
			}

			myOutput.flush();
			myOutput.close();
			myInput.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}
