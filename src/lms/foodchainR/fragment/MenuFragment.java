package lms.foodchainR.fragment;

import java.util.ArrayList;

import lms.foodchainR.R;
import lms.foodchainR.activity.CaseStyleDetailActivity;
import lms.foodchainR.dao.Case_DBHelper;
import lms.foodchainR.data.CaseStyleData;
import lms.foodchainR.service.MenuService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class MenuFragment extends Fragment implements OnPageChangeListener,
		OnClickListener, OnLongClickListener {
	private Case_DBHelper cdb;
	private LinearLayout title;
	private ViewPager pager;
	private ArrayList<CaseStyleData> styleList;
	private MenuFragAdapter mfa;
	private final int DELETE = 1;
	private final int EDIT = 2;
	private int currentItem = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.menu, container, false);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		initView();
		getData(getActivity());
		super.onActivityCreated(savedInstanceState);
	}

	private void getData(Context context) {
		cdb = new Case_DBHelper(context);
		styleList = cdb.getStyle();
		RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		rl.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		rl.setMargins(5, 5, 5, 5);
		for (int i = 0; i < styleList.size(); i++) {
			CaseStyleData csd = styleList.get(i);
			Button name = new Button(getActivity());
			name.setId(i);
			name.setLayoutParams(rl);
			name.setText(csd.name);
			name.setOnLongClickListener(this);
			name.setOnCreateContextMenuListener(this);
			title.addView(name);
		}
		mfa = new MenuFragAdapter(getChildFragmentManager());
		pager.setAdapter(mfa);
		pager.setCurrentItem(0);
		title.setOnLongClickListener(this);
	}

	private void initView() {
		pager = (ViewPager) getView().findViewById(R.id.pager);
		title = (LinearLayout) getView().findViewById(R.id.title);
		getView().findViewById(R.id.create).setOnClickListener(this);
	}

	private class MenuFragAdapter extends FragmentPagerAdapter {

		public MenuFragAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			CaseStyleData csd = styleList.get(arg0);
			return CaseStyleFragment.newInstance(csd.id, csd.name);
		}

		@Override
		public int getCount() {
			return styleList.size();
		}

		@Override
		public int getItemPosition(Object object) {
			return PagerAdapter.POSITION_NONE;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return styleList.get(position).name;
		}

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		currentItem = arg0;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.create:
			createNewCaseStyle();
			break;
		default:
			break;
		}
	}

	private void createNewCaseStyle() {
		MyDialogFragment.caseStyleInstance().show(getChildFragmentManager(),
				"dialog");
	}

	@Override
	public boolean onLongClick(View v) {
		return false;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.add(0, DELETE, 1, "删除");
		menu.add(0, EDIT, 2, "编辑");
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		CaseStyleData c = styleList.get(currentItem);
		switch (item.getItemId()) {
		case DELETE:
			if (MenuService.deleteStyle(c)) {
				styleList = cdb.getStyle();
				mfa = new MenuFragAdapter(getChildFragmentManager());
				pager.setAdapter(mfa);
			}
			break;
		case EDIT:
			Intent intent = new Intent(getActivity(),
					CaseStyleDetailActivity.class);

			intent.putExtra("id", c.id);

			intent.putExtra("name", c.name);
			startActivity(intent);
			break;
		default:
			break;
		}
		return true;
	}

}
