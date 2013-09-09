package lms.foodchainR.activity;

import java.util.List;

import lms.foodchainR.R;
import lms.foodchainR.data.EmployeeData;
import lms.foodchainR.data.Self;
import lms.foodchainR.widget.EmployeeAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

/**
 * @author 李梦思
 * @version 1.0
 * @createTime 2012-11-18
 * @description 雇员列表
 * @changeLog
 */
public class EmployeeActivity extends BaseActivity implements OnClickListener,
		OnItemClickListener {

	private Button cooker;
	private Button waiter;
	private Button cleaner;
	private Button code;
	private Button name;
	private ImageButton clean;
	private EditText searchEd;
	private ListView list;
	private EmployeeAdapter ea;
	// 雇员职位
	private final int WAITER = 0;
	private final int COOKER = 1;
	private final int CLEANER = 2;
	private final int SEARCH = 3;
	private int type = WAITER;
	// 搜索条件
	private final int NAME = 0;
	private final int CODE = 1;
	private int condition = NAME;
	// 搜索结果
	private List<EmployeeData> result;
	// 当前列表数据
	private List<EmployeeData> employee;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.employee);
		initViews();
	}

	@Override
	protected void onResume() {
		super.onResume();
		initData();
	}

	private void initData() {
		switch (type) {
		case WAITER:
			employee = Self.current().getWaiter();
			break;
		case COOKER:
			employee = Self.current().getCooker();
			break;
		case CLEANER:
			employee = Self.current().getCleaner();
			break;
		case SEARCH:
			employee = result;
			break;
		default:
			break;
		}
		ea = new EmployeeAdapter(this, employee);
		list.setAdapter(ea);
	}

	private void initViews() {
		cooker = (Button) findViewById(R.id.employee_cooker);
		cooker.setOnClickListener(this);
		waiter = (Button) findViewById(R.id.employee_waiter);
		waiter.setOnClickListener(this);
		cleaner = (Button) findViewById(R.id.employee_cleaner);
		cleaner.setOnClickListener(this);
		code = (Button) findViewById(R.id.employee_code);
		code.setOnClickListener(this);
		name = (Button) findViewById(R.id.employee_name);
		name.setOnClickListener(this);
		clean = (ImageButton) findViewById(R.id.employee_search_clean);
		clean.setOnClickListener(this);
		searchEd = (EditText) findViewById(R.id.employee_search_edit);
		findViewById(R.id.employee_search_btn).setOnClickListener(this);
		findViewById(R.id.employee_back).setOnClickListener(this);
		list = (ListView) findViewById(R.id.employee_list);
		list.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		EmployeeData.current = employee.get(position);
		startActivity(new Intent(this, EmployeeDetailActivity.class));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.employee_cooker:
			type = COOKER;

			break;
		case R.id.employee_waiter:
			type = WAITER;

			break;
		case R.id.employee_cleaner:
			type = CLEANER;

			break;
		case R.id.employee_code:
			condition = CODE;

			break;
		case R.id.employee_name:
			condition = NAME;

			break;
		case R.id.employee_search_clean:
			switch (condition) {
			case NAME:
				searchEd.setHint("请输入员工姓名");
				break;
			case CODE:
				searchEd.setHint("请输入员工编号");
				break;
			default:
				break;
			}
			searchEd.setText("");
			break;
		case R.id.employee_search_btn:

			break;
		case R.id.employee_back:
			finish();
			break;
		default:
			break;
		}
	}
}
