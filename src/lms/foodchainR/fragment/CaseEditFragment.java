package lms.foodchainR.fragment;

import lms.foodchainR.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class CaseEditFragment extends Fragment implements OnClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		View v = getView();

		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.case_edit, null);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ib_title_left:

			break;
		case R.id.b_title_right:

			break;
		default:
			break;
		}
	}
}
