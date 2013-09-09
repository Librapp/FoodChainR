package lms.foodchainR.activity;

import java.util.ArrayList;
import java.util.List;

import lms.foodchainR.R;
import lms.foodchainR.data.BillData;
import lms.foodchainR.data.Self;
import lms.foodchainR.widget.BillAdapter;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author 李梦思
 * @version 1.0
 * @createTime 2012-11-18
 * @description 订单列表
 * @changeLog
 */
public class BillActivity extends BaseActivity implements OnClickListener,
		OnItemClickListener {

	private TextView noBillData;
	private LinearLayout billLayout;
	private ListView list;
	private List<BillData> bill;
	private BillAdapter ba;

	private int now;

	private final int DETAIL = 1;
	private final int DELETE = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bill);

		initViews();

	}

	private void initViews() {
		noBillData = (TextView) findViewById(R.id.bill_tip);
		if (Self.current().getBill() != null) {
			noBillData.setVisibility(View.GONE);
			billLayout.setVisibility(View.VISIBLE);
		} else {
			noBillData.setVisibility(View.VISIBLE);
			billLayout.setVisibility(View.GONE);
		}

		list = (ListView) findViewById(R.id.list);
		list.setOnItemClickListener(this);
		list.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

			@Override
			public void onCreateContextMenu(ContextMenu menu, View v,
					ContextMenuInfo menuInfo) {
				menu.add(0, DETAIL, 0, "详情");
				menu.add(0, DELETE, 0, "删除");
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		initData();
		if (bill != null) {
			noBillData.setVisibility(View.GONE);
			billLayout.setVisibility(View.VISIBLE);
			ba = new BillAdapter(this, list, bill);
			list.setAdapter(ba);
		} else {
			noBillData.setVisibility(View.VISIBLE);
			billLayout.setVisibility(View.GONE);
		}
	}

	// 实例化本地数据
	private void initData() {
		bill = new ArrayList<BillData>();
		for (int i = 0; i < 10; ++i) {
			BillData b = new BillData();
			bill.add(b);
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case DETAIL:
			// TODO
			break;
		case DELETE:
			// TODO
			break;
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// case R.id.bill_tip:
		// MainActivity.tabHost.setCurrentTabByTag("restaurantInfo");
		// break;
		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		now = arg2;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			dialog();
			return false;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	protected void dialog() {
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("确定要退出吗?");
		builder.setCancelable(false);
		builder.setIcon(R.drawable.icon);
		builder.setPositiveButton("确定",
				new android.content.DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						finish();
					}
				});
		builder.setNegativeButton("取消",
				new android.content.DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		builder.create().show();
	}
}