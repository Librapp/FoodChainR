package lms.foodchainR.util;

import lms.foodchainR.data.OtherData;
import lms.foodchainR.data.Self;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharePerformanceUtil {

	public static void getLInfo(Context context) {
		SharedPreferences spl = context.getSharedPreferences(
				OtherData.LOCALSETTING, Context.MODE_PRIVATE);
		// 自定义
		Self.current().name = spl.getString("name", "名字");
		Self.current().phone = spl.getString("tel", "订餐电话");
		Self.current().sms = spl.getString("sms", "订餐短信");
		Self.current().opentime = spl.getString("opentime", "营业时间");
		Self.current().passWord = spl.getString("passWord", "******");
		Self.current().intro = spl.getString("intro", "招财进宝");
		Self.current().address = spl.getString("address", "北京");

	}

	public static void saveLInfo(Context context) {
		SharedPreferences spl = context.getSharedPreferences(
				OtherData.LOCALSETTING, Context.MODE_PRIVATE);
		Editor ed = spl.edit();
		ed.putString("name", Self.current().name);
		ed.putString("tel", Self.current().phone);
		ed.putString("sms", Self.current().sms);
		ed.putString("opentime", Self.current().opentime);
		ed.putString("passWord", Self.current().passWord);
		ed.putString("intro", Self.current().intro);
		ed.putString("address", Self.current().address);
		ed.commit();
	}

	public static void getNInfo(Context context) {
		SharedPreferences spn = context.getSharedPreferences(
				OtherData.ONLINESETTING, Context.MODE_PRIVATE);
		// 系统分配
		Self.current().id = spn.getInt("id", 0);
		Self.current().credit = spn.getInt("credit", 0);
		Self.current().point = spn.getInt("point", 0);
	}

	public static void saveNInfo(Context context) {
		SharedPreferences spn = context.getSharedPreferences(
				OtherData.ONLINESETTING, Context.MODE_PRIVATE);
		Editor ed = spn.edit();
		ed.putInt("id", Self.current().id);
		ed.putInt("credit", Self.current().credit);
		ed.putInt("point", Self.current().point);
		ed.commit();
	}
}
