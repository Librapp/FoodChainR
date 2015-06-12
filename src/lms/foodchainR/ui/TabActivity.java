package lms.foodchainR.ui;

import lms.foodchainR.R;
import lms.foodchainR.fragment.EmployeeFragment;
import lms.foodchainR.fragment.HallFragment;
import lms.foodchainR.fragment.ManageFragment;
import lms.foodchainR.fragment.MenuFragment;
import lms.foodchainR.fragment.MessageFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.Toast;

/**
 * 
 * @author 梦思
 * @description 新版主界面
 * @creatTime 2014/1/15
 */
public class TabActivity extends FragmentActivity implements
		OnTabChangeListener {
	private FragmentTabHost mTabHost;
	private long lastTime = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.tabmain);
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		mTabHost.addTab(
				mTabHost.newTabSpec("manage").setIndicator(
						getResources().getString(R.string.manage)),
				ManageFragment.class, null);
		mTabHost.addTab(
				mTabHost.newTabSpec("menu").setIndicator(
						getResources().getString(R.string.menu)),
				MenuFragment.class, null);
		mTabHost.addTab(
				mTabHost.newTabSpec("hall").setIndicator(
						getResources().getString(R.string.hall)),
				HallFragment.class, null);
		mTabHost.addTab(
				mTabHost.newTabSpec("kitchen").setIndicator(
						getResources().getString(R.string.kitchen)),
				EmployeeFragment.class, null);
		mTabHost.addTab(
				mTabHost.newTabSpec("message").setIndicator(
						getResources().getString(R.string.message)),
				MessageFragment.class, null);
		mTabHost.setOnTabChangedListener(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			long currentTime = System.currentTimeMillis();
			if (currentTime - lastTime >= 0 && currentTime - lastTime <= 2000) {
				return super.onKeyDown(keyCode, event);
			} else {
				Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
				lastTime = currentTime;
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	public void selectFragment(int i) {
		mTabHost.setCurrentTab(i);
	}

	@Override
	public void onTabChanged(String tabId) {

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}
}
