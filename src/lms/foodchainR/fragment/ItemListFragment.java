package lms.foodchainR.fragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import de.greenrobot.event.EventBus;

public class ItemListFragment extends ListFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Register
		EventBus.getDefault().register(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		// Unregister
		EventBus.getDefault().unregister(this);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		// 发布事件，在后台线程发的事件
		// EventBus.getDefault().post(new ItemListEvent(Item.ITEMS));
	}

	// public void onEventMainThread(ItemListEvent event) {
	// setListAdapter(new ArrayAdapter<Item>(getActivity(),
	// android.R.layout.simple_list_item_activated_1,
	// android.R.id.text1, event.getItems()));
	// }

	@Override
	public void onListItemClick(ListView listView, View view, int position,
			long id) {
		super.onListItemClick(listView, view, position, id);
		EventBus.getDefault().post(getListView().getItemAtPosition(position));
	}

}