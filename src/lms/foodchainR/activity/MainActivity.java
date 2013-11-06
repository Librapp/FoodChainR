package lms.foodchainR.activity;

import lms.foodchainR.R;
import lms.foodchainR.fragment.HallFragment;
import lms.foodchainR.fragment.ManageFragment;
import lms.foodchainR.fragment.MenuFragment;
import lms.foodchainR.service.DlnaService;
import lms.foodchainR.service.MenuService;
import lms.foodchainR.service.TableService;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockFragmentActivity;

/**
 * @author 李梦思
 * @version 1.0
 * @description 主界面
 * @createTime 2013-3-28
 */
public class MainActivity extends SherlockFragmentActivity implements
		TabListener {
	private long lastTime = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final ActionBar bar = getSupportActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
		bar.addTab(bar.newTab().setText(R.string.hall).setTabListener(this));
		bar.addTab(bar.newTab().setText(R.string.kitchen).setTabListener(this));
		bar.addTab(bar.newTab().setText(R.string.manage).setTabListener(this));
		bar.addTab(bar.newTab().setText(R.string.menu).setTabListener(this));
		bar.addTab(bar.newTab().setText(R.string.message).setTabListener(this));
		bar.getTabAt(2).select();
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		if (tab.getText().equals(getResources().getString(R.string.hall))) {
			Fragment frag = getSupportFragmentManager().findFragmentByTag(
					getResources().getString(R.string.hall));
			if (frag == null) {
				frag = new HallFragment();
				ft.replace(R.id.frame, new HallFragment(), getResources()
						.getString(R.string.hall));
			} else
				ft.attach(frag);
		} else if (tab.getText().equals(
				getResources().getString(R.string.kitchen))) {
			Toast.makeText(this, "正在开发", Toast.LENGTH_SHORT).show();
			// Fragment frag = getSupportFragmentManager().findFragmentByTag(
			// getResources().getString(R.string.kitchen));
			// if (frag == null) {
			// frag = new KitchenFragment();
			// ft.replace(R.id.frame, frag,
			// getResources().getString(R.string.kitchen));
			// } else
			// ft.attach(frag);
		} else if (tab.getText().equals(
				getResources().getString(R.string.manage))) {
			Fragment frag = getSupportFragmentManager().findFragmentByTag(
					getResources().getString(R.string.manage));
			if (frag == null) {
				frag = new ManageFragment();
				ft.replace(R.id.frame, frag,
						getResources().getString(R.string.manage));
			} else
				ft.attach(frag);
		} else if (tab.getText()
				.equals(getResources().getString(R.string.menu))) {
			Fragment frag = getSupportFragmentManager().findFragmentByTag(
					getResources().getString(R.string.menu));
			if (frag == null) {
				frag = new MenuFragment();
				ft.replace(R.id.frame, frag,
						getResources().getString(R.string.menu));
			} else
				ft.attach(frag);
		} else if (tab.getText().equals(
				getResources().getString(R.string.message))) {
			Toast.makeText(this, "正在开发", Toast.LENGTH_SHORT).show();
			// Fragment frag = getSupportFragmentManager().findFragmentByTag(
			// getResources().getString(R.string.message));
			// if (frag == null) {
			// frag = new MessageFragment();
			// ft.replace(R.id.frame, frag,
			// getResources().getString(R.string.message));
			// } else
			// ft.attach(frag);
		}
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

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
				Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
				lastTime = currentTime;
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}