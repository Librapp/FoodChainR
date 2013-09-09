package lms.foodchainR.activity;

import java.util.List;

import lms.foodchainR.R;
import lms.foodchainR.data.BillData;
import lms.foodchainR.data.CaseData;
import lms.foodchainR.widget.MenuAdapter;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class BillDetailActivity extends BaseActivity implements
		OnItemClickListener, OnClickListener {

	private ListView list;

	private TextView id;
	private TextView type;
	private TextView cost;
	private TextView paid;
	private TextView discount;
	private TextView customer;
	private TextView address;
	private TextView createTime;

	private MenuAdapter ma;

	private List<CaseData> bill;

	private final int DETAIL = 1;
	private final int DELETE = 2;

	private int whatItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.billdetail);
		bill = BillData.current.getCaseList();
		initViews();
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (BillData.current.state == 1) {
			paid.setText("已付款");
			discount.setText(BillData.current.discount + "");
			cost.setText(BillData.current.cost + "");
		} else {
			paid.setText("未付款");
		}
	}

	private void initViews() {
		id = (TextView) findViewById(R.id.bd_id);
		id.setText(BillData.current.id);
		createTime = (TextView) findViewById(R.id.bd_createtime);
		createTime.setText(BillData.current.createTime);

		type = (TextView) findViewById(R.id.bd_type);
		cost = (TextView) findViewById(R.id.bd_cost);

		paid = (TextView) findViewById(R.id.bd_paid);
		discount = (TextView) findViewById(R.id.bd_discount);
		customer = (TextView) findViewById(R.id.bd_customer);
		customer.setText(BillData.current.customerName);
		address = (TextView) findViewById(R.id.bd_address);

		list = (ListView) findViewById(R.id.bd_list);
		list.setOnItemClickListener(this);
		list.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

			@Override
			public void onCreateContextMenu(ContextMenu menu, View v,
					ContextMenuInfo menuInfo) {
				menu.add(0, DETAIL, 0, "详情");
				menu.add(0, DELETE, 0, "删除");
			}
		});
		ma = new MenuAdapter(this, bill);
		list.setAdapter(ma);

		switch (BillData.current.type) {
		case CaseData.HERE:
			type.setText("即食");
			if (!BillData.current.seatId.equals("")) {
				address.setText("座位号" + BillData.current.seatId);
			} else if (!BillData.current.tableId.equals("")) {
				address.setText("桌号" + BillData.current.tableId);
			} else {
				address.setVisibility(View.GONE);
			}
			break;
		case CaseData.PACK:
			type.setText("带走");
			if (!BillData.current.seatId.equals("")) {
				address.setText("座位号" + BillData.current.seatId);
			} else if (!BillData.current.tableId.equals("")) {
				address.setText("桌号" + BillData.current.tableId);
			} else {
				address.setVisibility(View.GONE);
			}
			break;
		case CaseData.AWAY:
			type.setText("外卖");
			address.setText(BillData.current.address);
			break;
		default:
			break;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bd_back:
			finish();
			break;
		default:
			break;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		whatItem = arg2;
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case DETAIL:
			// TODO

			break;
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}
}
