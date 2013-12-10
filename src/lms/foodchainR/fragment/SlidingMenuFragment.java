package lms.foodchainR.fragment;

import lms.foodchainR.R;
import lms.foodchainR.ui.NewMainActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SlidingMenuFragment extends ListFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.slidingmenulist, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		String[] slidingmenu = getResources().getStringArray(
				R.array.slidingmenu);
		ArrayAdapter<String> colorAdapter = new ArrayAdapter<String>(
				getActivity(), android.R.layout.simple_list_item_1,
				android.R.id.text1, slidingmenu);
		setListAdapter(colorAdapter);
	}

	@Override
	public void onListItemClick(ListView lv, View v, int position, long id) {
		Fragment newContent = new Fragment();
		switch (position) {
		case 0:
			newContent = new ManageFragment();
			break;
		case 1:
			newContent = new MenuFragment();
			break;
		case 2:
			newContent = new HallFragment();
			break;
		default:
			Toast.makeText(getActivity(), "正在开发", Toast.LENGTH_SHORT).show();
			break;
		}
		if (newContent != null)
			switchFragment(newContent);
	}

	// the meat of switching the above fragment
	private void switchFragment(Fragment fragment) {
		if (getActivity() == null)
			return;

		if (getActivity() instanceof NewMainActivity) {
			NewMainActivity ra = (NewMainActivity) getActivity();
			ra.switchContent(fragment);
		}
	}
}
