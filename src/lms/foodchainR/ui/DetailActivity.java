package lms.foodchainR.ui;

import lms.foodchainR.R;
import lms.foodchainR.fragment.AboutFragment;
import lms.foodchainR.fragment.CaseDetailFragment;
import lms.foodchainR.fragment.LoginFragment;
import lms.foodchainR.fragment.WebBrowserFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * 
 * @author 梦思
 * @description 详情界面
 * @createTime 2013/12/17
 */
public class DetailActivity extends FragmentActivity implements OnClickListener {
	private TextView title;
	private Fragment mContent;
	public final static int GETIMAGE_BYSDCARD = 0;
	public final static int GETIMAGE_BYCAMERA = 1;
	public final static int PHOTORESOULT = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.detail);

		super.onCreate(savedInstanceState);
		findViewById(R.id.back).setOnClickListener(this);
		title = (TextView) findViewById(R.id.title);
		int t = getIntent().getIntExtra("title", R.string.about);
		title.setText(t);
		Bundle b = new Bundle();
		switch (t) {
		case R.string.mycomment:
			mContent = new AboutFragment();
			break;
		case R.string.message:
			mContent = new LoginFragment();
			break;
		case R.string.webbrowser:
			String url = getIntent().getStringExtra("url");
			b.putString("url", url);
			mContent = new WebBrowserFragment();
			break;
		case R.string.casedetail:
			mContent = new CaseDetailFragment();
			break;
		default:
			break;
		}
		mContent.setArguments(b);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.frame, mContent).commit();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
		default:
			break;
		}
	}

	public void switchContent(final Fragment fragment) {
		mContent = fragment;
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.frame, mContent).commit();
	}
}
