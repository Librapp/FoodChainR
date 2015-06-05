package lms.foodchainR.fragment;

import lms.foodchainR.R;
import lms.foodchainR.dao.Case_DBHelper;
import lms.foodchainR.data.CaseStyleData;
import lms.foodchainR.ui.SecondaryActivity;
import lms.foodchainR.widget.MenuAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

/**
 * 
 * @author 梦思
 * @description 菜品分类模块
 * @createTime 2014/1/20
 */
public class CaseStyleFragment extends ListFragment implements OnClickListener {
	private CaseStyleData csd;
	private Case_DBHelper cdb;
	private MenuAdapter ma;
	private Button edit;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		csd = new CaseStyleData();
		if (getArguments() != null) {
			csd.id = getArguments().getInt("styleId");
			csd.name = getArguments().getString("name");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.casestyle, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		cdb = new Case_DBHelper(getActivity());
		edit = new Button(getActivity());
		edit.setText(R.string.edit);
		edit.setBackgroundResource(R.drawable.btn_bg);
		edit.setOnClickListener(this);
		getListView().addFooterView(edit);
		if (cdb.getCaseStyleData(csd) && csd.getList().size() > 0) {
			ma = new MenuAdapter(getActivity(), csd.getList());
			setListAdapter(ma);
		}
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO
	}

	public static CaseStyleFragment newInstance(int id, String name) {
		CaseStyleFragment f = new CaseStyleFragment();
		Bundle args = new Bundle();
		args.putInt("styleId", id);
		args.putString("name", name);
		f.setArguments(args);
		return f;
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(getActivity(), SecondaryActivity.class);
		intent.putExtra("id", csd.id);
		intent.putExtra("name", csd.name);
		intent.putExtra("title", R.string.casestyledetail);
		startActivity(intent);
	}

}
