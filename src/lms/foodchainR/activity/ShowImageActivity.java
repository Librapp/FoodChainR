package lms.foodchainR.activity;

import lms.foodchainR.R;
import lms.foodchainR.data.OtherData;
import lms.foodchainR.util.BitmapUtils;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * @author ����
 * @version 2.0
 * @description �鿴������ͼƬ
 * @createTime 2012-6-8
 * @changeLog
 */
public class ShowImageActivity extends Activity implements OnClickListener {

	private ImageView img;
	private ImageButton left;
	private ImageButton right;
	private Button back;
	private Button save;
	private Bitmap old;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showimage);

		img = (ImageView) findViewById(R.id.showimg_img);
		left = (ImageButton) findViewById(R.id.showimg_rotateleft);
		left.setOnClickListener(this);
		right = (ImageButton) findViewById(R.id.showimg_img_rotateright);
		right.setOnClickListener(this);
		back = (Button) findViewById(R.id.showimg_title_back);
		back.setOnClickListener(this);
		save = (Button) findViewById(R.id.showimg_title_btn);
		save.setOnClickListener(this);

		// byte[] b = getIntent().getExtras().getByteArray("image");
		// old = BitmapFactory.decodeByteArray(b, 0, b.length);

		old = OtherData.tempBitmap;
		// img.setOnTouchListener(new MultiTouchListener());

		img.setImageBitmap(old);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.showimg_rotateleft:
			old = BitmapUtils.rotateBitmap(old, 270);
			img.setImageBitmap(old);

			break;
		case R.id.showimg_img_rotateright:
			old = BitmapUtils.rotateBitmap(old, 90);
			img.setImageBitmap(old);

			break;
		case R.id.showimg_title_back:
			OtherData.tempBitmap = null;
			finish();
			break;
		case R.id.showimg_title_btn:
			OtherData.tempBitmap = old;
			setResult(RESULT_OK);
			finish();
			break;

		default:
			break;
		}
	}

}
