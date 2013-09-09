package lms.foodchainR.activity;

import java.util.List;

import lms.foodchainR.R;
import lms.foodchainR.data.CustomerData;
import lms.foodchainR.widget.CustomerListAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

/**
 * @author 李梦思
 * @version 1.0
 * @createTime 2013-5-9
 * @description 排号顾客
 * @changeLog
 */
public class WaitActivity extends BaseActivity implements OnClickListener,
		OnItemLongClickListener {

	private ListView customerList;
	private CustomerListAdapter cla;

	private List<CustomerData> customer;

	private int now = 0;
	private final int CUSTOMERDETAIL = 1;
	private final int SENDMESSAGE = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wait);

		initViews();
		initData();

		customerList.setOnItemLongClickListener(this);
		customerList
				.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

					@Override
					public void onCreateContextMenu(ContextMenu menu, View v,
							ContextMenuInfo menuInfo) {
						menu.add(0, CUSTOMERDETAIL, 0, "消费详情");
						menu.add(0, SENDMESSAGE, 0, "发送消息");
					}
				});
	}

	private void initData() {

		cla = new CustomerListAdapter(this, customer);
		customerList.setAdapter(cla);
	}

	private void initViews() {
		customerList = (ListView) findViewById(R.id.customer);
		findViewById(R.id.back).setOnClickListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case CUSTOMERDETAIL:
			Intent iC = new Intent(WaitActivity.this,
					CustomerDetailActivity.class);
			startActivity(iC);
			break;
		case SENDMESSAGE:
			// TODO 发送消息

			break;
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		now = arg2;
		return true;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}