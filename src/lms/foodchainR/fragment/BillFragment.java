package lms.foodchainR.fragment;

import java.util.ArrayList;

import lms.foodchainR.R;
import lms.foodchainR.data.BillData;
import lms.foodchainR.data.RestaurantData;
import lms.foodchainR.net.JSONRequest;
import lms.foodchainR.net.NetUtil;
import lms.foodchainR.widget.BillAdapter;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BillFragment extends Fragment {
	private BillAdapter ba;
	private UploadBillTask uploadBillTask;
	private Context context;
	private ArrayList<BillData> billList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.hall, container, false);
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		context = getActivity();
		refresh();
	}

	private void refresh() {
		if (uploadBillTask != null) {
			uploadBillTask.cancel(true);
			uploadBillTask = null;
		}
		uploadBillTask = new UploadBillTask();
		uploadBillTask.execute();
	}

	private class UploadBillTask extends
			AsyncTask<Object, JSONRequest, Boolean> {
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Object... params) {

			String result = NetUtil.executePost((Context) params[0],
					(String) params[1],
					RestaurantData.current().device.getLocation());
			billList = new ArrayList<BillData>();
			return null;
		}

		protected void onPostExecute(Boolean result) {
			// TODO
		};

	}
}
