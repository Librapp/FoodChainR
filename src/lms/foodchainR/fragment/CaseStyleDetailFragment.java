package lms.foodchainR.fragment;

import java.util.List;

import lms.foodchainR.R;
import lms.foodchainR.dao.Case_DBHelper;
import lms.foodchainR.data.CaseData;
import lms.foodchainR.data.CaseStyleData;
import lms.foodchainR.service.MenuService;
import lms.foodchainR.ui.DetailActivity;
import lms.foodchainR.util.ViewHolder;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author 李梦思
 * @description 编辑分类界面
 * @createTime 2013-3-26
 * @version 1.0
 * 
 */
public class CaseStyleDetailFragment extends Fragment implements
		OnClickListener, OnItemLongClickListener, OnItemClickListener {

	private ListView listView;
	private Button edit, create;

	private final int REQUEST_CREATECASE = 1, DETAIL = 1, DELETE = 2;
	private int currentItem = 0;
	private boolean isEdit = false;

	private Case_DBHelper cdb;
	private CaseStyleData csd;
	private MenuAdapter ma;
	private mlistener listener;
	private List<CaseData> list;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.casestyledetail, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initViews();
		initData();
	}

	private void initViews() {
		View v = getView();
		listView = (ListView) v.findViewById(R.id.list);
	}

	private void initData() {
		listener = new mlistener();
		cdb = new Case_DBHelper(getActivity());
		csd = new CaseStyleData();
		csd.styleId = getArguments().getInt("id", 0);
		csd.name = getArguments().getString("name");
		if (!MenuService.getCaseStyleData(csd)) {
			getActivity().setResult(Activity.RESULT_CANCELED);
			getActivity().finish();
		}
		create = new Button(getActivity());
		create.setId(0);
		create.setText(R.string.create);
		create.setBackgroundResource(R.drawable.btn_bg);
		create.setOnClickListener(listener);
		listView.addFooterView(create);
		list = csd.getList();
		ma = new MenuAdapter(getActivity(), list);
		listView.setAdapter(ma);
		// listView.setOnItemLongClickListener(this);
		// listView.setOnCreateContextMenuListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.edit:
			isEdit = !isEdit;
			if (isEdit)
				edit.setText("完成");
			else
				edit.setText("编辑");
			ma.notifyDataSetChanged();
			break;
		default:
			break;
		}
	}

	private void openCreateNewCaseActivity() {
		CaseData.current = new CaseData();
		CaseData.current().isNew = true;
		CaseData.current().styleId = csd.styleId;
		Intent intent = new Intent(getActivity(), DetailActivity.class);
		intent.putExtra("id", CaseData.current.caseId);
		intent.putExtra("name", CaseData.current.name);
		intent.putExtra("title", R.string.casedetail);
		startActivityForResult(intent, REQUEST_CREATECASE);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		currentItem = csd.getList().get(position).caseId;
		return false;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.add(0, DETAIL, 1, "查看");
		menu.add(0, DELETE, 2, "删除");
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case DETAIL:
			CaseData.current = list.get(currentItem);
			Intent intent = new Intent(getActivity(), DetailActivity.class);
			intent.putExtra("id", CaseData.current.caseId);
			intent.putExtra("name", CaseData.current.name);
			intent.putExtra("title", R.string.casedetail);
			startActivity(intent);
			return true;
		case DELETE:
			CaseData c = new CaseData();
			c.caseId = currentItem;
			if (MenuService.deleteCase(c)) {
				// TODO 刷新
				Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT)
						.show();
			} else
				Toast.makeText(getActivity(), "删除失败", Toast.LENGTH_SHORT)
						.show();
			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}

	public class MenuAdapter extends BaseAdapter {
		private Context context;
		private List<CaseData> lc;

		public MenuAdapter(Context context, List<CaseData> lc) {
			this.context = context;
			this.lc = lc;
		}

		@Override
		public int getCount() {
			return lc.size();
		}

		@Override
		public Object getItem(int position) {
			return lc.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			ViewHolder holder;
			CaseData c = lc.get(position);
			if (view == null) {
				view = LayoutInflater.from(context).inflate(
						R.layout.menu_list_item, null);
				holder = new ViewHolder();
				holder.text = (TextView) view.findViewById(R.id.case_name);
				holder.text1 = (TextView) view.findViewById(R.id.case_price);
				holder.text2 = (TextView) view.findViewById(R.id.case_status);
				holder.pic = (ImageView) view.findViewById(R.id.case_pic);
				holder.btn = (Button) view.findViewById(R.id.delete);
				view.setTag(holder);
			} else
				holder = (ViewHolder) view.getTag();
			holder.text1.setText(c.price + "元");
			holder.text.setText(c.name);
			if (isEdit)
				holder.btn.setVisibility(View.VISIBLE);
			else
				holder.btn.setVisibility(View.GONE);
			holder.btn.setOnClickListener(listener);
			switch (c.state) {
			case CaseData.AVILIABLE:
				holder.text2.setText("热卖中");
				break;
			case CaseData.UNAVILIABLE:
				holder.text2.setText("售罄");
				break;
			default:
				break;
			}

			Drawable d = Drawable.createFromPath(c.picPath);
			if (d != null) {
				holder.pic.setImageDrawable(d);
			}
			return view;
		}
	}

	private class mlistener implements OnClickListener {

		@Override
		public void onClick(View v) {
			CaseData c;
			switch (v.getId()) {
			case R.id.delete:
				c = csd.getList().get(currentItem);
				if (MenuService.deleteCase(c)) {
					if (cdb.getCaseStyleData(csd))
						list = csd.getList();
					ma = new MenuAdapter(getActivity(), list);
					listView.setAdapter(ma);
				}
				break;
			case 0:
				openCreateNewCaseActivity();
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		currentItem = arg2;
		CaseData.current = list.get(currentItem);
		Intent intent = new Intent(getActivity(), DetailActivity.class);
		intent.putExtra("id", CaseData.current.caseId);
		intent.putExtra("name", CaseData.current.name);
		intent.putExtra("title", R.string.casedetail);
		startActivity(intent);
	}
}
