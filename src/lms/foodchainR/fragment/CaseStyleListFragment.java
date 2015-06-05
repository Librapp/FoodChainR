package lms.foodchainR.fragment;

import java.util.List;

import lms.foodchainR.R;
import lms.foodchainR.dao.Case_DBHelper;
import lms.foodchainR.data.CaseStyleData;
import lms.foodchainR.util.ViewHolder;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class CaseStyleListFragment extends ListFragment implements
		OnClickListener, OnItemClickListener {
	private Case_DBHelper cdb;
	private List<CaseStyleData> list;
	private Button create;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.casestyle, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		cdb = new Case_DBHelper(getActivity());
		list = cdb.getCaseStyleList();
		create = new Button(getActivity());
		create.setText(R.string.create);
		create.setOnClickListener(this);
		getListView().addFooterView(create);
		setListAdapter(new CaseStyleListAdapter());
		getListView().setOnItemClickListener(this);
	}

	private class CaseStyleListAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
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
			holder.text.setText(list.get(position).name);
			holder.btn.setOnClickListener(CaseStyleListFragment.this);
			holder.btn1.setOnClickListener(CaseStyleListFragment.this);
			return convertView;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.cli_edit:
			edit();
			break;
		case R.id.cli_delete:
			delete();
			break;
		default:
			break;
		}
	}

	private void delete() {
		// TODO Auto-generated method stub

	}

	private void edit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}
}
