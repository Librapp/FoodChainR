package lms.foodchainR.activity;

import lms.foodchainR.service.DlnaService;
import lms.foodchainR.service.MenuService;
import lms.foodchainR.service.TableService;
import lms.foodchainR.util.DialogUtil;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;

/**
 * @author 李梦思
 * @version 1.0
 * @createTime 2012-11-18
 * @description 基本界面
 * @changeLog
 */
public class BaseActivity extends Activity {
	private long lastTime = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			long currentTime = System.currentTimeMillis();
			if (currentTime - lastTime >= 0 && currentTime - lastTime <= 2000) {
				stopService(new Intent(this, MenuService.class));
				// startService(new Intent(this, BillService.class));
				stopService(new Intent(this, TableService.class));
				stopService(new Intent(this, DlnaService.class));
				return super.onKeyDown(keyCode, event);
			} else {
				DialogUtil.alertToast(getApplicationContext(), "再按一次退出");
				lastTime = currentTime;
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
