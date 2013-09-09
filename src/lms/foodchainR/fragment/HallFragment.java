package lms.foodchainR.fragment;

import java.util.ArrayList;

import lms.foodchainR.R;
import lms.foodchainR.dao.Table_DBHelper;
import lms.foodchainR.data.TableStyleData;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HallFragment extends Fragment implements OnPageChangeListener {
	private Table_DBHelper tdb;
	private ViewPager pager;
	private ArrayList<TableStyleData> styleList;
	private HallFragAdapter mfa;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.hall, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		initView();
		getData(getActivity());
		super.onActivityCreated(savedInstanceState);
	}

	private void getData(Context context) {
		tdb = new Table_DBHelper(context);
		styleList = tdb.getTableStyleDataList();
		mfa = new HallFragAdapter(getChildFragmentManager());
		pager.setAdapter(mfa);
		pager.setCurrentItem(0);
	}

	private void initView() {
		pager = (ViewPager) getView().findViewById(R.id.pager);
	}

	private class HallFragAdapter extends FragmentPagerAdapter {

		public HallFragAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			TableStyleData csd = styleList.get(arg0);
			return TableStyleFragment.newInstance(csd.id);
		}

		@Override
		public int getCount() {
			return styleList.size();
		}

		@Override
		public int getItemPosition(Object object) {
			return PagerAdapter.POSITION_NONE;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return styleList.get(position).seatCount + "人桌";
		}

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {

	}

}
