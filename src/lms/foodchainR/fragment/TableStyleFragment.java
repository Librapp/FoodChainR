package lms.foodchainR.fragment;

import lms.foodchainR.R;
import lms.foodchainR.dao.Table_DBHelper;
import lms.foodchainR.data.TableStyleData;
import lms.foodchainR.widget.TableAdapter;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class TableStyleFragment extends ListFragment {
	private TableStyleData tsd;
	private Table_DBHelper tdb;
	private TableAdapter ta;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.tablestyle, container, false);
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		tsd = new TableStyleData();
		tsd.id = getArguments().getString("styleId");
		tdb = new Table_DBHelper(getActivity());
		if (tdb.getTableStyleDetail(tsd))
			if (tsd.getTable().size() > 0) {
				ta = new TableAdapter(getActivity(), tsd.getTable());
				setListAdapter(ta);
			}
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {

	}

	public static TableStyleFragment newInstance(String id) {
		TableStyleFragment f = new TableStyleFragment();
		Bundle args = new Bundle();
		args.putString("styleId", id);
		f.setArguments(args);
		return f;
	}

}
