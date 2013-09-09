package lms.foodchainR.fragment;

import java.util.ArrayList;

import lms.foodchainR.R;
import lms.foodchainR.dao.Bill_DBHelper;
import lms.foodchainR.data.CaseData;
import lms.foodchainR.data.EmployeeData;
import lms.foodchainR.widget.BillDetailAdapter;
import lms.foodchainR.widget.CookerListAdapter;
import lms.foodchainR.widget.StepGallery;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * @author 李梦思
 * @version 1.0
 * @createTime 2012-11-18
 * @description 厨房状态
 * @changeLog
 */
public class KitchenFragment extends Fragment implements OnItemClickListener {
	private StepGallery cooker;
	private ListView bill;

	private CookerListAdapter cla;
	private BillDetailAdapter ba;
	private Bill_DBHelper bdb;
	private ArrayList<CaseData> caseList;
	private ArrayList<EmployeeData> cookerList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.kitchen, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		cooker = (StepGallery) getView().findViewById(R.id.kitchen_cooker);
		bill = (ListView) getView().findViewById(R.id.kitchen_bill);
		bill.setOnItemClickListener(this);
		bdb = new Bill_DBHelper(getActivity());
		if (bdb.getOrderList(caseList)) {
			ba = new BillDetailAdapter(getActivity(), bill, caseList);
			bill.setAdapter(ba);
		}
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
	}
}
