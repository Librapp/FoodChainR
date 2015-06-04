package lms.foodchainR.fragment;

import java.util.ArrayList;

import lms.foodchainR.R;
import lms.foodchainR.dao.Case_DBHelper;
import lms.foodchainR.data.CaseStyleData;
import lms.foodchainR.service.MenuService;
import lms.foodchainR.ui.SecondaryActivity;
import lms.foodchainR.util.ViewHolder;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

/**
 * 
 * @author 梦思
 * @description 菜单模块
 * @createTime 2013/12/25
 */
public class MenuFragment extends Fragment implements OnPageChangeListener,
		OnClickListener, OnLongClickListener, OnItemClickListener,
		OnItemSelectedListener, OnTouchListener {
	private Case_DBHelper cdb;
	private LinearLayout title;
	private ViewPager pager;
	private ListView list;
	private Button create;
	private Button edit;
	private ArrayList<CaseStyleData> styleList;
	private MenuFragAdapter mfa;
	private final int DELETE = 1, EDIT = 2;
	private int currentItem = 0;
	private boolean isEdit = false;
	private mlistener listener;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.menu, container, false);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
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
		styleList = cdb.getCaseStyleList();
		RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		rl.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		rl.setMargins(5, 5, 5, 5);
		for (int i = 0; i < styleList.size(); i++) {
			CaseStyleData csd = styleList.get(i);
			Button name = new Button(getActivity());
			name.setId(csd.id);
			name.setLayoutParams(rl);
			name.setText(csd.name);
			name.setBackgroundResource(R.drawable.btn_bg);
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
		listener = new mlistener();
		pager = (ViewPager) getView().findViewById(R.id.pager);
		pager.setOnPageChangeListener(this);
		pager.setOnTouchListener(this);
		list = (ListView) getView().findViewById(R.id.list);
		list.setOnItemSelectedListener(this);
		list.setOnItemClickListener(this);
		create = new Button(getActivity());
		create.setId(0);
		create.setText(R.string.create);
		create.setOnClickListener(listener);
		list.addFooterView(create);
		title = (LinearLayout) getView().findViewById(R.id.title);
		edit = (Button) getView().findViewById(R.id.edit);
		edit.setOnClickListener(this);
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
		case R.id.edit:
			if (isEdit) {
				isEdit = false;
				edit.setText("编辑");
				list.setVisibility(View.GONE);
				pager.setVisibility(View.VISIBLE);
				mfa = new MenuFragAdapter(getChildFragmentManager());
				pager.setAdapter(mfa);
			} else {
				isEdit = true;
				showEdit();
			}
			break;
		default:
			break;
		}
	}

	private void showEdit() {
		edit.setText("完成");
		// getChildFragmentManager().beginTransaction()
		// .replace(R.id.pager, new CaseStyleListFragment()).commit();
		list.setAdapter(new CaseStyleListAdapter());
		list.setVisibility(View.VISIBLE);
		pager.setVisibility(View.GONE);
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
				styleList = cdb.getCaseStyleList();
				mfa = new MenuFragAdapter(getChildFragmentManager());
				pager.setAdapter(mfa);
				title.removeViewAt(currentItem);
			}
			break;
		case EDIT:
			Intent intent = new Intent(getActivity(), SecondaryActivity.class);
			intent.putExtra("id", c.id);
			intent.putExtra("name", c.name);
			intent.putExtra("title", R.string.casestyledetail);
			startActivity(intent);
			break;
		default:
			break;
		}
		return true;
	}

	private class CaseStyleListAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return styleList.size();
		}

		@Override
		public Object getItem(int position) {
			return styleList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = LayoutInflater.from(getActivity()).inflate(
						R.layout.casestyle_list_item, null);
				holder.text = (TextView) convertView
						.findViewById(R.id.cli_name);
				holder.btn = (Button) convertView.findViewById(R.id.cli_edit);
				holder.btn1 = (Button) convertView
						.findViewById(R.id.cli_delete);
				convertView.setTag(holder);
			} else
				holder = (ViewHolder) convertView.getTag();
			holder.text.setText(styleList.get(position).name);
			holder.btn.setOnClickListener(listener);
			holder.btn1.setOnClickListener(listener);
			return convertView;
		}
	}

	private class mlistener implements OnClickListener {

		@Override
		public void onClick(View v) {
			CaseStyleData c;
			switch (v.getId()) {
			case R.id.cli_edit:
				c = styleList.get(currentItem);
				MyDialogFragment.caseStyleInstance(c.name, c.id).show(
						getChildFragmentManager(), "dialog");
				break;
			case R.id.cli_delete:
				c = styleList.get(currentItem);
				if (MenuService.deleteStyle(c)) {
					styleList = cdb.getCaseStyleList();
					// mfa = new MenuFragAdapter(getChildFragmentManager());
					// pager.setAdapter(mfa);
					list.setAdapter(new CaseStyleListAdapter());
					title.removeViewAt(currentItem);
				}
				break;
			case 0:
				createNewCaseStyle();
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		currentItem = arg2;
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		currentItem = 0;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		currentItem = arg2;
		CaseStyleData c = styleList.get(arg2);
		Intent intent = new Intent(getActivity(), SecondaryActivity.class);
		intent.putExtra("id", c.id);
		intent.putExtra("name", c.name);
		intent.putExtra("title", R.string.casestyledetail);
		startActivity(intent);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		return pager.getCurrentItem() != 0;
	}

}
