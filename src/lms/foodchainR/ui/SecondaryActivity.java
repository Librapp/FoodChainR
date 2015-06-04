package lms.foodchainR.ui;

import lms.foodchainR.R;
import lms.foodchainR.fragment.AboutFragment;
import lms.foodchainR.fragment.CaseStyleDetailFragment;
import lms.foodchainR.fragment.FeedbackFragment;
import lms.foodchainR.fragment.SendCommentFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * 
 * @author 梦思
 * @description 二级界面
 * @createTime 2013/12/19
 */
public class SecondaryActivity extends FragmentActivity implements
		OnClickListener {
	private TextView title;
	private Fragment mContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.secondary);
		super.onCreate(savedInstanceState);
		findViewById(R.id.back).setOnClickListener(this);
		title = (TextView) findViewById(R.id.title);
		int t = getIntent().getIntExtra("title", R.string.about);
		title.setText(t);
		switch (t) {
		case R.string.dianping:
			mContent = new SendCommentFragment();
			break;
		case R.string.about:
			mContent = new AboutFragment();
			break;
		case R.string.login:
			// TODO
			break;
		case R.string.feedback:
			mContent = new FeedbackFragment();
			break;
		case R.string.editstyle:

			break;
		case R.string.casestyledetail:
			title.setText(getIntent().getStringExtra("name"));
			mContent = new CaseStyleDetailFragment();
			break;
		default:
			break;
		}
		mContent.setArguments(getIntent().getExtras());
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
